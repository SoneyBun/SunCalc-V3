import java.util.ArrayList;

public class Operation {
    // Variables

    private final String operator;
    private double number1;
    private double number2;

    // Modes

    private final boolean angularMode = Main.config.getAngularMode();

    // Operation Errors

    static final Error unknownOperator = new Error(51, "Unknown Operator");
    static final Error divideByZero = new Error(52, "Divide By Zero");

    public Operation(String o, ArrayList<Double> n) {
        operator = o;
        number1 = n.getFirst();
        if(n.size() == 2) {
            number2 = n.getLast();
        }
    }

    private double toRadians(double n) {
        return n * Math.PI / 180;
    }

    private double toDegrees(double n) {
        return n * 180 / Math.PI;
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
            case "%", "mod" : yield number1 + " % " + number2 + " = " + (number1 % number2);

            // Boolean Algebra

            case ">" : yield number1 + " > " + number2 + " --> " + (number1 > number2);
            case "<" : yield number1 + " < " + number2 + " --> " + (number1 < number2);
            case ">=" : yield number1 + " >= " + number2 + " --> " + (number1 >= number2);
            case "<=" : yield number1 + " <= " + number2 + " --> " + (number1 <= number2);
            case "=", "==" : yield number1 + " " + operator + " " + number2 + " --> " + (number1 == number2);

            // Trigonometry

            case "sin", "sine" : yield "sin(" + number1 + ") = " + Math.sin(angularMode ? number1 : toRadians(number1));
            case "cos", "cosine" : yield "cos(" + number1 + ") = " + Math.cos(angularMode ? number1 : toRadians(number1));
            case "tan", "tangent" : yield "tan(" + number1 + ") = " + Math.tan(angularMode ? number1 : toRadians(number1));

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
}