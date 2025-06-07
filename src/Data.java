import java.util.ArrayList;

public class Data {
    // Receiving Configuration

    private final int notation = Main.config.getNotation();

    // Data Variables

    private final String input;
    private final ArrayList<String> variables = new ArrayList<>();

    // Return Variables

    private String operator;
    private final ArrayList<Double> numbers = new ArrayList<>();

    public Data(String i) {
        input = i;
        enterVariables();
    }

    private void enterVariables() {
        char[] variableArray = input.toCharArray();
        StringBuilder builder = new StringBuilder();
        for(char ch : variableArray) {
            if(ch == ' ') {
                variables.add(builder.toString());
                builder = new StringBuilder();
            } else {
                builder.append(ch);
            }
        }
        if(!builder.isEmpty()) {
            variables.add(builder.toString());
        }

        switch (notation) {
            case 1 :
                operator = variables.get(0);
                numbers.add(Double.parseDouble(variables.get(1)));
                if(variables.size() == 3) {
                    numbers.add(Double.parseDouble(variables.get(2)));
                }
                break;
            case 2 :
                if(variables.size() == 2) {
                    operator = variables.get(0);
                    numbers.add(Double.parseDouble(variables.get(1)));
                } else if(variables.size() == 3) {
                    operator = variables.get(1);
                    numbers.add(Double.parseDouble(variables.get(0)));
                    numbers.add(Double.parseDouble(variables.get(2)));
                }
                break;
            case 3 :
                operator = variables.getLast();
                numbers.add(Double.parseDouble(variables.get(0)));
                if(variables.size() == 3) {
                    numbers.add(Double.parseDouble(variables.get(1)));
                }
                break;
        }
    }

    // Getter Methods

    public String getOperator() {
        return operator;
    }

    public ArrayList<Double> getNumbers() {
        return numbers;
    }
}