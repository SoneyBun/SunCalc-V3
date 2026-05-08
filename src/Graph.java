import java.util.Arrays;

public class Graph {

    private static final int WIDTH  = 62;   // columns of the plot area
    private static final int HEIGHT = 22;   // rows of the plot area

    // ── Grid helpers ────────────────────────────────────────────────────────

    private static char[][] createGrid() {
        char[][] g = new char[HEIGHT][WIDTH];
        for (char[] row : g) Arrays.fill(row, ' ');
        return g;
    }

    private static int xToCol(double x, double xMin, double xMax) {
        if (xMax == xMin) return WIDTH / 2;
        int col = (int) Math.round((x - xMin) / (xMax - xMin) * (WIDTH - 1));
        return Math.max(0, Math.min(WIDTH - 1, col));
    }

    private static int yToRow(double y, double yMin, double yMax) {
        if (yMax == yMin) return HEIGHT / 2;
        int row = (int) Math.round((yMax - y) / (yMax - yMin) * (HEIGHT - 1));
        return Math.max(0, Math.min(HEIGHT - 1, row));
    }

    private static void drawAxes(char[][] g, double xMin, double xMax,
                                 double yMin, double yMax) {
        // Y-axis
        if (xMin <= 0 && 0 <= xMax) {
            int col = xToCol(0, xMin, xMax);
            for (int r = 0; r < HEIGHT; r++)
                if (g[r][col] == ' ') g[r][col] = '|';
        }
        // X-axis
        if (yMin <= 0 && 0 <= yMax) {
            int row = yToRow(0, yMin, yMax);
            for (int c = 0; c < WIDTH; c++)
                if (g[row][c] == ' ') g[row][c] = '-';
        }
        // Origin
        if (xMin <= 0 && 0 <= xMax && yMin <= 0 && 0 <= yMax) {
            g[yToRow(0, yMin, yMax)][xToCol(0, xMin, xMax)] = '+';
        }
    }

    // ── Rendering ───────────────────────────────────────────────────────────

    private static void render(char[][] g, String title,
                               double xMin, double xMax,
                               double yMin, double yMax) {
        System.out.println("\n" + title);

        // Border top
        System.out.printf("%10s +", "");
        for (int i = 0; i < WIDTH; i++) System.out.print("-");
        System.out.println("+");

        int zeroRow = (yMin <= 0 && 0 <= yMax) ? yToRow(0, yMin, yMax) : -1;

        for (int row = 0; row < HEIGHT; row++) {
            // Left label
            if (row == 0)              System.out.printf("%10.4f |", yMax);
            else if (row == HEIGHT - 1) System.out.printf("%10.4f |", yMin);
            else if (row == zeroRow)    System.out.printf("%10.4f |", 0.0);
            else                        System.out.printf("%10s |", "");

            for (char c : g[row]) System.out.print(c);
            System.out.println("|");
        }

        // Border bottom
        System.out.printf("%10s +", "");
        for (int i = 0; i < WIDTH; i++) System.out.print("-");
        System.out.println("+");

        // X-axis labels
        String left  = String.format("%.4f", xMin);
        String right = String.format("%.4f", xMax);
        int gap = WIDTH - left.length() - right.length();
        if (gap < 1) gap = 1;
        System.out.printf("%12s%s%" + gap + "s%s%n%n", "", left, "", right);
    }

    // ── Public: plot a named function ────────────────────────────────────────

    public static void plotFunction(String func, double xMin, double xMax) {
        double[] ys    = new double[WIDTH];
        double   yMin  = Double.MAX_VALUE;
        double   yMax  = -Double.MAX_VALUE;

        for (int col = 0; col < WIDTH; col++) {
            double x = xMin + (xMax - xMin) * col / (WIDTH - 1);
            double y = evaluate(func, x);
            ys[col] = y;
            if (Double.isFinite(y)) {
                if (y < yMin) yMin = y;
                if (y > yMax) yMax = y;
            }
        }

        if (!Double.isFinite(yMin)) {
            System.out.println("No finite values found in this range for: " + func);
            return;
        }

        // Pad range slightly so points aren't flush against the border
        double yRange = yMax - yMin;
        double pad    = yRange < 1e-9 ? 1.0 : yRange * 0.08;
        yMin -= pad;
        yMax += pad;

        char[][] g = createGrid();
        drawAxes(g, xMin, xMax, yMin, yMax);

        for (int col = 0; col < WIDTH; col++) {
            if (Double.isFinite(ys[col])) {
                int row = yToRow(ys[col], yMin, yMax);
                g[row][col] = '*';
            }
        }

        render(g,
                "f(x) = " + func + "   x \u2208 [" +
                        String.format("%.4f", xMin) + ", " + String.format("%.4f", xMax) + "]"
                        + "  (* = function)",
                xMin, xMax, yMin, yMax);
    }

    // ── Public: plot a SunList ───────────────────────────────────────────────

    public static void plotPoints(SunList list) {
        if (list == null || list.size() == 0) {
            System.out.println("No points to plot.");
            return;
        }

        double xMin = Double.MAX_VALUE, xMax = -Double.MAX_VALUE;
        double yMin = Double.MAX_VALUE, yMax = -Double.MAX_VALUE;

        for (int i = 0; i < list.size(); i++) {
            Point p = list.getPoint(i);
            xMin = Math.min(xMin, p.getX()); xMax = Math.max(xMax, p.getX());
            yMin = Math.min(yMin, p.getY()); yMax = Math.max(yMax, p.getY());
        }

        double xR = xMax - xMin, yR = yMax - yMin;
        double xPad = xR < 1e-9 ? 1.0 : xR * 0.1;
        double yPad = yR < 1e-9 ? 1.0 : yR * 0.1;
        xMin -= xPad; xMax += xPad;
        yMin -= yPad; yMax += yPad;

        char[][] g = createGrid();
        drawAxes(g, xMin, xMax, yMin, yMax);

        // Draw linear regression line if there are enough points
        boolean hasRegression = list.size() >= 2;
        if (hasRegression) {
            double[] lr = list.linearRegression();
            for (int col = 0; col < WIDTH; col++) {
                double x = xMin + (xMax - xMin) * col / (WIDTH - 1);
                double y = lr[0] * x + lr[1];
                if (Double.isFinite(y)) {
                    int row = yToRow(y, yMin, yMax);
                    if (row >= 0 && row < HEIGHT && g[row][col] == ' ')
                        g[row][col] = '.';
                }
            }
        }

        // Plot data points on top (overwrite regression dots if overlap)
        for (int i = 0; i < list.size(); i++) {
            Point p = list.getPoint(i);
            int col = xToCol(p.getX(), xMin, xMax);
            int row = yToRow(p.getY(), yMin, yMax);
            if (col >= 0 && col < WIDTH && row >= 0 && row < HEIGHT)
                g[row][col] = 'o';
        }

        String legend = "SunList (" + list.size() + " point" + (list.size() == 1 ? "" : "s") + ")"
                + "  [o = data" + (hasRegression ? ", . = linear fit" : "") + "]";
        render(g, legend, xMin, xMax, yMin, yMax);
    }

    // ── Public: list available functions ────────────────────────────────────

    public static void printHelp() {
        System.out.println("  Trig       : sin  cos  tan  asin  acos  atan");
        System.out.println("  Hyperbolic : sinh cosh tanh");
        System.out.println("  Power/Root : sqrt cbrt square cube");
        System.out.println("  Logarithm  : ln   log  log2");
        System.out.println("  Other      : abs  floor ceil sgn inv x");
        System.out.println("  (note: trig input/output respects Angular Mode setting)");
    }

    // ── Evaluate f(x) for a single value ────────────────────────────────────

    static double evaluate(String func, double x) {
        boolean radMode = Main.config.getAngularMode(); // true = radians input
        double  xRad    = radMode ? x : x * Math.PI / 180.0;

        return switch (func.toLowerCase().trim()) {
            // Trig (respects angular mode)
            case "sin",  "sine"      -> Math.sin(xRad);
            case "cos",  "cosine"    -> Math.cos(xRad);
            case "tan",  "tangent"   -> Math.tan(xRad);
            // Inverse trig – always outputs in current angular mode
            case "asin", "arcsin"    -> { double r = Math.asin(x); yield radMode ? r : Math.toDegrees(r); }
            case "acos", "arccos"    -> { double r = Math.acos(x); yield radMode ? r : Math.toDegrees(r); }
            case "atan", "arctan"    -> { double r = Math.atan(x); yield radMode ? r : Math.toDegrees(r); }
            // Hyperbolic (pure real, no angle conversion)
            case "sinh"              -> Math.sinh(x);
            case "cosh"              -> Math.cosh(x);
            case "tanh"              -> Math.tanh(x);
            // Power / root
            case "sqrt", "squareroot"-> x >= 0 ? Math.sqrt(x) : Double.NaN;
            case "cbrt", "cuberoot"  -> Math.cbrt(x);
            case "square", "sq", "x^2" -> x * x;
            case "cube",         "x^3" -> x * x * x;
            // Logarithm
            case "ln"                -> Math.log(x);
            case "log"               -> Math.log10(x);
            case "log2"              -> Math.log(x) / Math.log(2);
            // Misc
            case "abs", "abval"      -> Math.abs(x);
            case "floor"             -> Math.floor(x);
            case "ceil", "ceiling"   -> Math.ceil(x);
            case "sgn",  "sign"      -> Math.signum(x);
            case "inv",  "reciprocal","1/x" -> x != 0 ? 1.0 / x : Double.NaN;
            case "x",    "identity"  -> x;
            default                  -> Double.NaN;
        };
    }
}