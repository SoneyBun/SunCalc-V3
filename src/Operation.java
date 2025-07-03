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

    static final Error unknownOperator = new Error(61, "Unknown Operator");
    static final Error divideByZero = new Error(62, "Divide By Zero");
    static final Error negativeBase = new Error(63, "Logarithm With A Negative Base");
    static final Error invalidFactorialInput = new Error(64, "Factorial input must be a non-negative integer");

    public Operation(String o, ArrayList<Double> n) {
        operator = o;
        number1 = n.getFirst();
        if(n.size() == 2) {
            number2 = n.getLast();
        }
    }

    public String executeOperation() {
        double result;
        return switch (operator) {
            // Basic Arithmetic

            case "+", "add", "plus" : yield number1 + " + " + number2 + " = " + (number1 + number2);
            case "-", "subtract", "minus" : yield number1 + " - " + number2 + " = " + (number1 - number2);
            case "*", "multiply", "times" : yield number1 + " * " + number2 + " = " + (number1 * number2);
            case "/", "divide", "over" :
                if(number2 != 0) {
                    yield number1 + " / " + number2 + " = " + (number1 / number2);
                }
                yield divideByZero.toString();
            case "%", "mod", "modulus" : yield number1 + " % " + number2 + " = " + (number1 % number2);
            case "||", "abval", "absval" : yield "|" + number1 + "| = " + Math.abs(number1);

            // Comparison

            case ">" : yield number1 + " > " + number2 + " --> " + (number1 > number2);
            case "<" : yield number1 + " < " + number2 + " --> " + (number1 < number2);
            case ">=" : yield number1 + " >= " + number2 + " --> " + (number1 >= number2);
            case "<=" : yield number1 + " <= " + number2 + " --> " + (number1 <= number2);
            case "=", "==" : yield number1 + " " + operator + " " + number2 + " --> " + (number1 == number2);
            case "!=" : yield number1 + " != " + number2 + " --> " + (number1 != number2);
            case "max", "maximum" : yield Math.max(number1, number2) + "";
            case "min", "minimum" : yield Math.min(number1, number2) + "";

            // Number Manipulation

            case "rnd", "round" : yield ((int) (number1 + .5)) + "";
            case "trunc", "truncate" : yield ((int) number1) + "";

            // Trigonometry

            case "sin", "sine" : yield "sin(" + number1 + ") = " + Math.sin(angularMode ? number1 : toRadians(number1));
            case "cos", "cosine" : yield "cos(" + number1 + ") = " + Math.cos(angularMode ? number1 : toRadians(number1));
            case "tan", "tangent" : yield "tan(" + number1 + ") = " + Math.tan(angularMode ? number1 : toRadians(number1));

            // Factorial

            case "!", "factorial" :
                if (number1 < 0 || number1 != Math.floor(number1)) {
                    yield invalidFactorialInput.toString();
                } else {
                    BigInteger fact = factorialOf((int) number1);
                    yield (int) number1 + "! = " + fact;
                }

            // Exponents And Logarithms

            case "^", "exp", "exponent", "pwr", "pow", "power" : yield number1 + "^" + number2 + " = " + Math.pow(number1, number2);
            case "rt", "root" : yield number1 + "^ 1/" + number2 + " = " + Math.pow(number1, 1 / number2);
            case "log", "logarithm" :
                if(number2 == 0 || number2 == 10) {
                    yield "log(" + number1 + ") = " + Math.log(number1) / Math.log(10);
                } else if(number2 == Math.E) {
                    yield "ln(" + number1 + ") = " + Math.log(number1);
                } else if(number2 > 0) {
                    yield "log" + number2 + "(" + number1 + ") = " + Math.log(number1) / Math.log(number2);
                }
                yield negativeBase.toString();
            case "ln" :
                yield "ln(" + number1 + ") = " + Math.log(number1);

            // Inverse Trigonometry

            case "asin", "arcsin", "arcsine" :
                result = Math.asin(number1);
                yield "asin" + number1 + ") = " + (angularMode ? result : toDegrees(result));
            case "acos", "arccos", "arccosine" :
                result = Math.acos(number1);
                yield "asin" + number1 + ") = " + (angularMode ? result : toDegrees(result));
            case "atan", "arctan", "arctangent" :
                result = Math.atan(number1);
                yield "atan(" + number1 + ") = " + (angularMode ? result : toDegrees(result));

            // Default

            default : yield unknownOperator.toString();
        };
    }

    // Helper Methods

    private double toRadians(double n) {
        return n * Math.PI / 180;
    }

    private double toDegrees(double n) {
        return n * 180 / Math.PI;
    }

    private static BigInteger factorialOf(int n) {
        BigInteger result = BigInteger.ONE;
        for(int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }
}