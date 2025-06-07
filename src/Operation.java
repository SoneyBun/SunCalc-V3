import java.util.ArrayList;

public class Operation {
    // Variables
    private String operator;
    private double number1;
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
        switch (operator) {
            case "+", "add", "plus" : return number1 + " + " + number2 + " = " + (number1 + number2);
            case "-", "subtract", "minus" : return number1 + " - " + number2 + " = " + (number1 - number2);
            case "*", "multiply", "times" : return number1 + " * " + number2 + " = " + (number1 * number2);
            case "/", "divide", "over" : return number1 + " / " + number2 + " = " + (number1 / number2);
        }
        return unknownOperator.toString();
    }
}
