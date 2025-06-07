import java.util.ArrayList;

public class Operation {
    // Variables

    private final String operator;
    private final double number1;
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

    public String executeOperation() {
        return switch (operator) {
            // Basic

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

            // Default

            default : yield unknownOperator.toString();
        };
    }
}