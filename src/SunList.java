import java.util.ArrayList;
import java.util.HashMap;

public class SunList {
    private ArrayList<Point> points;
    private boolean numbers; // true if Y is always 0 (numbers-only mode)
    private static HashMap<String, SunList> lists = new HashMap<>();

    public SunList(boolean numbers, ArrayList<Point> points) {
        this.numbers = numbers;
        this.points = points;
    }

    // Save this list with a name
    public void save(String name) {
        lists.put(name.toLowerCase(), this);
    }

    // Get a saved SunList by name
    public static SunList getList(String name) {
        return lists.get(name.toLowerCase());
    }

    // Remove a saved SunList by name
    public static boolean removeList(String name) {
        return lists.remove(name.toLowerCase()) != null;
    }

    // List all saved SunList names
    public static ArrayList<String> getListNames() {
        return new ArrayList<>(lists.keySet());
    }

    // Add a point
    public void addPoint(Point p) {
        points.add(p);
    }

    // Remove a point by index (0-based)
    public boolean removePoint(int index) {
        if (index >= 0 && index < points.size()) {
            points.remove(index);
            return true;
        }
        return false;
    }

    // Get number of points
    public int size() {
        return points.size();
    }

    // Get a point by index
    public Point getPoint(int index) {
        if(index >= 0 && index < points.size()) {
            return points.get(index);
        }
        return null;
    }

    // Average point calculation
    public Point getAverage() {
        double sumX = 0;
        double sumY = 0;
        for(Point p : points) {
            sumX += p.getX();
            sumY += p.getY();
        }
        if(points.size() == 0) return new Point(0,0);
        return new Point(sumX / points.size(), sumY / points.size());
    }

    // Display all points as a formatted string
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(numbers ? "Numbers List:\n" : "Points List:\n");
        for (int i = 0; i < points.size(); i++) {
            sb.append(i).append(": ").append(points.get(i)).append("\n");
        }
        return sb.toString();
    }

    // Statistical & Regression Methods

    public double meanX() {
        if(points.isEmpty()) return 0;
        double sum = 0;
        for(Point p : points) sum += p.getX();
        return sum / points.size();
    }

    public double meanY() {
        if(points.isEmpty()) return 0;
        double sum = 0;
        for(Point p : points) sum += p.getY();
        return sum / points.size();
    }

    public double stdDevX() {
        if(points.size() <= 1) return 0;
        double mean = meanX();
        double sumSq = 0;
        for(Point p : points) {
            double diff = p.getX() - mean;
            sumSq += diff * diff;
        }
        return Math.sqrt(sumSq / (points.size() - 1));
    }

    public double stdDevY() {
        if(points.size() <= 1) return 0;
        double mean = meanY();
        double sumSq = 0;
        for(Point p : points) {
            double diff = p.getY() - mean;
            sumSq += diff * diff;
        }
        return Math.sqrt(sumSq / (points.size() - 1));
    }

    public double correlationCoefficient() {
        if(points.size() <= 1) return 0;
        double meanX = meanX();
        double meanY = meanY();
        double numerator = 0;
        double denomX = 0;
        double denomY = 0;
        for(Point p : points) {
            double dx = p.getX() - meanX;
            double dy = p.getY() - meanY;
            numerator += dx * dy;
            denomX += dx * dx;
            denomY += dy * dy;
        }
        if(denomX == 0 || denomY == 0) return 0;
        return numerator / Math.sqrt(denomX * denomY);
    }

    // Linear regression y = slope * x + intercept
    // Returns double array [slope, intercept]
    public double[] linearRegression() {
        int n = points.size();
        if(n == 0) return new double[]{0,0};
        double meanX = meanX();
        double meanY = meanY();

        double numerator = 0;
        double denominator = 0;

        for(Point p : points) {
            numerator += (p.getX() - meanX) * (p.getY() - meanY);
            denominator += (p.getX() - meanX) * (p.getX() - meanX);
        }

        double slope = (denominator == 0) ? 0 : numerator / denominator;
        double intercept = meanY - slope * meanX;

        return new double[]{slope, intercept};
    }

    // Polynomial regression degree 2: y = a*x^2 + b*x + c
    // Returns double array [a, b, c]
    public double[] polynomialRegression() {
        int n = points.size();
        if(n == 0) return new double[]{0,0,0};

        // sums
        double sumX = 0, sumX2 = 0, sumX3 = 0, sumX4 = 0;
        double sumY = 0, sumXY = 0, sumX2Y = 0;

        for(Point p : points) {
            double x = p.getX();
            double y = p.getY();
            double x2 = x*x;

            sumX += x;
            sumX2 += x2;
            sumX3 += x2 * x;
            sumX4 += x2 * x2;

            sumY += y;
            sumXY += x * y;
            sumX2Y += x2 * y;
        }

        // Construct system of equations:
        // | n       sumX    sumX2  |   | c |   | sumY   |
        // | sumX    sumX2   sumX3  | * | b | = | sumXY  |
        // | sumX2   sumX3   sumX4  |   | a |   | sumX2Y |

        double[][] matrix = {
                {n, sumX, sumX2},
                {sumX, sumX2, sumX3},
                {sumX2, sumX3, sumX4}
        };

        double[] rhs = {sumY, sumXY, sumX2Y};

        double[] coeffs = gaussianElimination(matrix, rhs); // [c, b, a]

        // Return [a, b, c]
        return new double[]{coeffs[2], coeffs[1], coeffs[0]};
    }

    // Sum of squares total (SST)
    public double sumSquaresTotal() {
        double meanY = meanY();
        double sst = 0;
        for(Point p : points) {
            double diff = p.getY() - meanY;
            sst += diff * diff;
        }
        return sst;
    }

    // Sum of squares regression (SSR)
    public double sumSquaresRegression() {
        double[] lr = linearRegression();
        double slope = lr[0];
        double intercept = lr[1];
        double ssr = 0;
        for(Point p : points) {
            double predicted = slope * p.getX() + intercept;
            double diff = predicted - meanY();
            ssr += diff * diff;
        }
        return ssr;
    }

    // Sum of squares error (SSE)
    public double sumSquaresError() {
        double[] lr = linearRegression();
        double slope = lr[0];
        double intercept = lr[1];
        double sse = 0;
        for(Point p : points) {
            double predicted = slope * p.getX() + intercept;
            double diff = p.getY() - predicted;
            sse += diff * diff;
        }
        return sse;
    }

    // --- Helper for polynomial regression: Gaussian elimination for 3x3 system
    private double[] gaussianElimination(double[][] matrix, double[] rhs) {
        int n = 3;
        for (int i = 0; i < n; i++) {
            // Partial pivot
            int max = i;
            for (int k = i+1; k < n; k++) {
                if (Math.abs(matrix[k][i]) > Math.abs(matrix[max][i])) max = k;
            }
            double[] temp = matrix[i];
            matrix[i] = matrix[max];
            matrix[max] = temp;

            double t = rhs[i];
            rhs[i] = rhs[max];
            rhs[max] = t;

            // Make pivot = 1
            double pivot = matrix[i][i];
            if (Math.abs(pivot) < 1e-10) continue;
            for (int j = i; j < n; j++) {
                matrix[i][j] /= pivot;
            }
            rhs[i] /= pivot;

            // Eliminate below
            for (int k = i+1; k < n; k++) {
                double factor = matrix[k][i];
                for (int j = i; j < n; j++) {
                    matrix[k][j] -= factor * matrix[i][j];
                }
                rhs[k] -= factor * rhs[i];
            }
        }

        // Back substitution
        double[] x = new double[n];
        for (int i = n-1; i >= 0; i--) {
            x[i] = rhs[i];
            for (int j = i+1; j < n; j++) {
                x[i] -= matrix[i][j] * x[j];
            }
        }
        return x;
    }

    public boolean isNumbers() {
        return numbers;
    }
}