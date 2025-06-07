import java.util.Scanner;
//import java.util.ArrayList;

public class Main {
    // Essential Objects
    public static Configuration config = new Configuration();
    static Scanner scan = new Scanner(System.in);
    static Data store;
    static Operation math;

    // Main Errors
    static Error invalid = new Error(41, "Invalid Input");

    public static void main(String[] args) {
        System.out.println("SunCalc V3\n");
        while(true) {
            int choice = getChoice("Menu");
            switch (choice) {
                case 1:
                    configure();
                case 2:
                    System.out.println("Enter Math:");
                    while(true) {
                        store = new Data(scan.nextLine());
                        math = new Operation(store.getOperator(), store.getNumbers());
                        System.out.println(math.executeOperation());
                    }
                case 3:
                    System.out.println("Health is coming soon");
                    break;
                case 4 :
                    System.out.println("Thank you for using SunCalc V3\nSession Concluded");
                    System.exit(0);
            }
        }
    }

    public static int getChoice(String type) {
        return switch (type) {
            case "Menu" -> getInput("Select Choice:\n↳ (1) ~ Configure\n↳ (2) ~ Math\n↳ (3) ~ Health\n↳ (4) ~ Quit", 4);
            case "Configure" -> getInput("Select Choice:\n↳ (1) ~ Notation\n↳ (2) ~ Angular Mode", 2);
            case "Notation" -> getInput("Select Choice:\n↳ (1) ~ Prefix\n↳ (2) ~ Infix\n↳ (3) ~ Postfix", 3);
            case "Angular Mode" -> getInput("Select Choice:\n↳ (1) ~ Degrees\n↳ (2) ~ Radians", 2);
            default -> -1;
        };
    }

    public static int getInput(String choices, int max) {
        int input;
        while(true) {
            System.out.println(choices);
            try {
                input = Integer.parseInt(scan.nextLine());
                if(input > 0 && input < (max + 1)) {
                    return input;
                }
            } catch (NumberFormatException e) {
                System.out.println(invalid);
            }
        }
    }

    public static void configure() {
        System.out.println(config + "\nWhat would you like to configure?");
        int choice = getChoice("Configure");
        if(choice == 1) {
            config.setNotation(getChoice("Notation"));
            System.out.println("Notation is currently set to " + config.getNotationString());
        } else {
            config.setAngularMode(getChoice("Angular Mode") != 1);
            System.out.println("Angular Mode is currently set to " + config.getAngularModeString());
        }
    }
}