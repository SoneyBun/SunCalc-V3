import java.util.ArrayList;

public class Operation {
    // Variables

    private final String operator;
    private final double number1;
    private double number2;

    // Operation Errors

    static Error unknownOperator = new Error(51, "Unknown Operator");

    public Operation(String o, ArrayList<Double> n) {
        operator = o;
        number1 = n.getFirst();
        if(n.size() == 2) {
            number2 = n.getLast();
        }
    }

    public String executeOperation() {
        return switch (operator) {
            // Basic 4 Function

            case "+", "add", "plus" -> number1 + " + " + number2 + " = " + (number1 + number2);
            case "-", "subtract", "minus" -> number1 + " - " + number2 + " = " + (number1 - number2);
            case "*", "multiply", "times" -> number1 + " * " + number2 + " = " + (number1 * number2);
            case "/", "divide", "over" -> number1 + " / " + number2 + " = " + (number1 / number2);
            default -> unknownOperator.toString();
        };
    }
}