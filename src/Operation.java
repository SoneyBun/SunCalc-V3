import java.math.BigInteger;
import java.util.ArrayList;

public class Operation {
    // Variables

    private final String operator;
    private final double number1;
    private double number2;

    // Modes

    private final boolean angularMode = Main.config.getAngularMode();

    // Operation Errors

    static final Error unknownOperator  = new Error(61, "Unknown Operator");
    static final Error divideByZero     = new Error(62, "Divide By Zero");
    static final Error negativeBase     = new Error(63, "Logarithm With A Negative Base");
    static final Error invalidFactorial = new Error(64, "Factorial input must be a non-negative integer");
    static final Error invalidDomain    = new Error(65, "Input is outside the valid domain for this operation");
    static final Error invalidInteger   = new Error(66, "Operation requires non-negative integer input(s)");

    public Operation(String o, ArrayList<Double> n) {
        operator = o;
        number1 = n.getFirst();
        if (n.size() == 2) {
            number2 = n.getLast();
        }
    }

    public String executeOperation() {
        double result;
        return switch (operator) {

            // ── Basic Arithmetic ────────────────────────────────────────────

            case "+", "add", "plus" ->
                    number1 + " + " + number2 + " = " + (number1 + number2);

            case "-", "subtract", "minus" ->
                    number1 + " - " + number2 + " = " + (number1 - number2);

            case "*", "multiply", "times" ->
                    number1 + " * " + number2 + " = " + (number1 * number2);

            case "/", "divide", "over" -> {
                if (number2 != 0) yield number1 + " / " + number2 + " = " + (number1 / number2);
                yield divideByZero.toString();
            }

            case "%", "mod", "modulus" ->
                    number1 + " % " + number2 + " = " + (number1 % number2);

            case "||", "abval", "absval", "abs" ->
                    "|" + number1 + "| = " + Math.abs(number1);

            // ── Number Manipulation ─────────────────────────────────────────

            case "rnd", "round"       -> ((int) (number1 + .5)) + "";
            case "trunc", "truncate"  -> ((int) number1) + "";
            case "floor"              -> "floor(" + number1 + ") = " + (long) Math.floor(number1);
            case "ceil", "ceiling"    -> "ceil(" + number1 + ") = "  + (long) Math.ceil(number1);
            case "sgn", "sign"        -> "sgn(" + number1 + ") = "   + (int) Math.signum(number1);

            case "sq", "square" ->
                    number1 + "^2 = " + (number1 * number1);

            case "cube" ->
                    number1 + "^3 = " + (number1 * number1 * number1);

            case "sqrt", "squareroot" -> {
                if (number1 < 0) yield invalidDomain.toString();
                yield "√" + number1 + " = " + Math.sqrt(number1);
            }

            case "cbrt", "cuberoot" ->
                    "∛" + number1 + " = " + Math.cbrt(number1);

            case "inv", "reciprocal" -> {
                if (number1 == 0) yield divideByZero.toString();
                yield "1 / " + number1 + " = " + (1.0 / number1);
            }

            case "avg", "average", "mean" ->
                    "avg(" + number1 + ", " + number2 + ") = " + ((number1 + number2) / 2.0);

            case "dist" ->
                    "|" + number1 + " - " + number2 + "| = " + Math.abs(number1 - number2);

            case "hyp", "hypot" ->
                    "hypot(" + number1 + ", " + number2 + ") = " + Math.hypot(number1, number2);

            // ── Comparison ──────────────────────────────────────────────────

            case ">"  -> number1 + " > "  + number2 + " --> " + (number1 >  number2);
            case "<"  -> number1 + " < "  + number2 + " --> " + (number1 <  number2);
            case ">=" -> number1 + " >= " + number2 + " --> " + (number1 >= number2);
            case "<=" -> number1 + " <= " + number2 + " --> " + (number1 <= number2);
            case "=", "==" -> number1 + " " + operator + " " + number2 + " --> " + (number1 == number2);
            case "!=" -> number1 + " != " + number2 + " --> " + (number1 != number2);

            case "max", "maximum" -> Math.max(number1, number2) + "";
            case "min", "minimum" -> Math.min(number1, number2) + "";

            // ── Angle Conversion ────────────────────────────────────────────

            case "deg", "todegrees" ->
                    number1 + " rad = " + toDegrees(number1) + "°";

            case "rad", "toradians" ->
                    number1 + "° = " + toRadians(number1) + " rad";

            // ── Trigonometry ────────────────────────────────────────────────

            case "sin", "sine" ->
                    "sin(" + number1 + ") = " + Math.sin(angularMode ? number1 : toRadians(number1));

            case "cos", "cosine" ->
                    "cos(" + number1 + ") = " + Math.cos(angularMode ? number1 : toRadians(number1));

            case "tan", "tangent" ->
                    "tan(" + number1 + ") = " + Math.tan(angularMode ? number1 : toRadians(number1));

            // ── Inverse Trigonometry ────────────────────────────────────────

            case "asin", "arcsin", "arcsine" -> {
                result = Math.asin(number1);
                yield "asin(" + number1 + ") = " + (angularMode ? result : toDegrees(result));
            }
            case "acos", "arccos", "arccosine" -> {
                result = Math.acos(number1);
                yield "acos(" + number1 + ") = " + (angularMode ? result : toDegrees(result));
            }
            case "atan", "arctan", "arctangent" -> {
                result = Math.atan(number1);
                yield "atan(" + number1 + ") = " + (angularMode ? result : toDegrees(result));
            }
            case "atan2" -> {
                result = Math.atan2(number1, number2);
                yield "atan2(" + number1 + ", " + number2 + ") = " + (angularMode ? result : toDegrees(result));
            }

            // ── Hyperbolic Functions ────────────────────────────────────────

            case "sinh" -> "sinh(" + number1 + ") = " + Math.sinh(number1);
            case "cosh" -> "cosh(" + number1 + ") = " + Math.cosh(number1);
            case "tanh" -> "tanh(" + number1 + ") = " + Math.tanh(number1);

            // ── Inverse Hyperbolic ──────────────────────────────────────────

            case "asinh", "arcsinh" -> {
                result = Math.log(number1 + Math.sqrt(number1 * number1 + 1));
                yield "asinh(" + number1 + ") = " + result;
            }
            case "acosh", "arccosh" -> {
                if (number1 < 1) yield invalidDomain.toString();
                result = Math.log(number1 + Math.sqrt(number1 * number1 - 1));
                yield "acosh(" + number1 + ") = " + result;
            }
            case "atanh", "arctanh" -> {
                if (number1 <= -1 || number1 >= 1) yield invalidDomain.toString();
                result = 0.5 * Math.log((1 + number1) / (1 - number1));
                yield "atanh(" + number1 + ") = " + result;
            }

            // ── Exponents & Logarithms ──────────────────────────────────────

            case "^", "exp", "exponent", "pwr", "pow", "power" ->
                    number1 + "^" + number2 + " = " + Math.pow(number1, number2);

            case "rt", "root" ->
                    number1 + "^(1/" + number2 + ") = " + Math.pow(number1, 1.0 / number2);

            case "log", "logarithm" -> {
                if (number2 == 0 || number2 == 10) {
                    yield "log(" + number1 + ") = " + Math.log(number1) / Math.log(10);
                } else if (number2 == Math.E) {
                    yield "ln(" + number1 + ") = " + Math.log(number1);
                } else if (number2 == 2) {
                    yield "log2(" + number1 + ") = " + (Math.log(number1) / Math.log(2));
                } else if (number2 > 0) {
                    yield "log" + number2 + "(" + number1 + ") = " + Math.log(number1) / Math.log(number2);
                }
                yield negativeBase.toString();
            }

            case "ln" ->
                    "ln(" + number1 + ") = " + Math.log(number1);

            case "log2" ->
                    "log2(" + number1 + ") = " + (Math.log(number1) / Math.log(2));

            case "log10" ->
                    "log10(" + number1 + ") = " + Math.log10(number1);

            // ── Factorial ───────────────────────────────────────────────────

            case "!", "factorial" -> {
                if (number1 < 0 || number1 != Math.floor(number1)) yield invalidFactorial.toString();
                yield (int) number1 + "! = " + factorialOf((int) number1);
            }

            // ── Combinatorics ───────────────────────────────────────────────

            case "perm", "npr", "permutation" -> {
                int n = (int) number1, r = (int) number2;
                if (n < 0 || r < 0 || r > n || number1 != Math.floor(number1) || number2 != Math.floor(number2))
                    yield invalidInteger.toString();
                BigInteger result2 = factorialOf(n).divide(factorialOf(n - r));
                yield "P(" + n + ", " + r + ") = " + result2;
            }

            case "comb", "ncr", "combination", "choose" -> {
                int n = (int) number1, r = (int) number2;
                if (n < 0 || r < 0 || r > n || number1 != Math.floor(number1) || number2 != Math.floor(number2))
                    yield invalidInteger.toString();
                BigInteger result2 = factorialOf(n)
                        .divide(factorialOf(r))
                        .divide(factorialOf(n - r));
                yield "C(" + n + ", " + r + ") = " + result2;
            }

            case "gcd" -> {
                if (number1 != Math.floor(number1) || number2 != Math.floor(number2))
                    yield invalidInteger.toString();
                yield "gcd(" + (long) number1 + ", " + (long) number2 + ") = "
                        + gcd(Math.abs((long) number1), Math.abs((long) number2));
            }

            case "lcm" -> {
                if (number1 != Math.floor(number1) || number2 != Math.floor(number2))
                    yield invalidInteger.toString();
                long a = Math.abs((long) number1), b = Math.abs((long) number2);
                if (a == 0 || b == 0) yield "lcm(" + a + ", " + b + ") = 0";
                yield "lcm(" + a + ", " + b + ") = " + (a / gcd(a, b) * b);
            }

            // ── Default ─────────────────────────────────────────────────────

            default -> unknownOperator.toString();
        };
    }

    // ── Helper Methods ───────────────────────────────────────────────────────

    private double toRadians(double n) { return n * Math.PI / 180; }
    private double toDegrees(double n) { return n * 180 / Math.PI; }

    private static BigInteger factorialOf(int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) result = result.multiply(BigInteger.valueOf(i));
        return result;
    }

    private static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}