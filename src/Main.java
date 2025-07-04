import java.util.ArrayList;
import java.util.Scanner;
import games.SunGames;

public class Main {
    // Essential Objects
    public static Configuration config = new Configuration();
    static final Scanner scan = new Scanner(System.in);
    static Data store;
    static Operation math;

    // Main Errors
    static final Error invalid = new Error(51, "Invalid Input");

    public static void main(String[] args) {
        System.out.println("SunCalc V3\n");

        while (true) {
            int choice = getChoice("Menu");
            switch (choice) {
                case 1 -> configure();
                case 2 -> {
                    choice = getChoice("Math");
                    switch (choice) {
                        case 1 -> {
                            System.out.println("Enter Math:");
                            while (true) {
                                String term = scan.nextLine();
                                if (term.equalsIgnoreCase("quit")) break;
                                store = new Data(term);
                                math = new Operation(store.getOperator(), store.getNumbers());
                                System.out.println(math.executeOperation());
                            }
                        }
                        case 2 -> sunListAnalysis();
                    }
                }
                case 3 -> {
                    int healthChoice = getChoice("Health");
                    switch (healthChoice) {
                        case 1 -> Health.getMeasurementSystem();
                        case 2 -> Health.calculateBMI();
                        case 3 -> Health.calculateBMR();
                        case 4 -> Health.estimateHeight();
                    }
                }
                case 4 -> SunGames.start(); // Games entry point
                case 5 -> {
                    System.out.println("Thank you for using SunCalc V3\nSession Concluded");
                    System.exit(0);
                }
            }
        }
    }

    public static int getChoice(String type) {
        return switch (type) {
            case "Menu" -> getInput("Select Choice:\n↳ (1) ~ Configure\n↳ (2) ~ Math\n↳ (3) ~ Health\n↳ (4) ~ Games\n↳ (5) ~ Quit", 5);
            case "Configure" -> getInput("Select Choice:\n↳ (1) ~ Notation\n↳ (2) ~ Angular Mode", 2);
            case "Notation" -> getInput("Select Choice:\n↳ (1) ~ Prefix\n↳ (2) ~ Infix\n↳ (3) ~ Postfix", 3);
            case "Angular Mode" -> getInput("Select Choice:\n↳ (1) ~ Degrees\n↳ (2) ~ Radians", 2);
            case "Math" -> getInput("Select Choice:\n↳ (1) ~ Arithmetic\n↳ (2) ~ SunList", 2);
            case "SunList" -> getInput("Select Choice:\n↳ (1) ~ Create SunList\n↳ (2) ~ Modify SunList\n↳ (3) ~ SunList Methods", 3);
            case "Create" -> getInput("Select Choice:\n↳ (1) ~ Numbers\n↳ (2) ~ Points", 2);
            case "Health" -> getInput("Select Choice:\n↳ (1) ~ Set Measurement System\n↳ (2) ~ Calculate BMI\n↳ (3) ~ Calculate BMR\n↳ (4) ~ Estimate Height", 4);
            default -> -1;
        };
    }

    public static int getInput(String choices, int max) {
        int input;
        while (true) {
            System.out.println(choices);
            try {
                input = Integer.parseInt(scan.nextLine());
                if (input > 0 && input <= max) return input;
            } catch (NumberFormatException e) {
                System.out.println(invalid);
            }
        }
    }

    public static ArrayList<Point> acquireSunList(boolean type) {
        ArrayList<Point> list = new ArrayList<>();
        if (!type) {
            System.out.println("Enter numbers (Y = 0 by default). Press Enter on a blank line to finish.");
            while (true) {
                System.out.print("Number: ");
                String input = scan.nextLine().trim();
                if (input.isEmpty()) break;
                try {
                    double x = Double.parseDouble(input);
                    list.add(new Point(x, 0));
                } catch (NumberFormatException e) {
                    System.out.println(invalid);
                }
            }
        } else {
            System.out.println("Enter points. Leave X blank and press Enter to finish.");
            while (true) {
                System.out.print("X: ");
                String xInput = scan.nextLine().trim();
                if (xInput.isEmpty()) break;
                System.out.print("Y: ");
                String yInput = scan.nextLine().trim();
                if (yInput.isEmpty()) {
                    System.out.println("Y value is required if X is entered.");
                    continue;
                }
                try {
                    double x = Double.parseDouble(xInput);
                    double y = Double.parseDouble(yInput);
                    list.add(new Point(x, y));
                } catch (NumberFormatException e) {
                    System.out.println(invalid);
                }
            }
        }
        return list;
    }

    public static void configure() {
        System.out.println(config + "\nWhat would you like to configure?");
        int choice = getChoice("Configure");
        if (choice == 1) {
            config.setNotation(getChoice("Notation"));
            System.out.println("Notation is currently set to " + config.getNotationString());
        } else {
            config.setAngularMode(getChoice("Angular Mode") != 1);
            System.out.println("Angular Mode is currently set to " + config.getAngularModeString());
        }
    }

    public static void sunListAnalysis() {
        int choice = getChoice("SunList");
        switch (choice) {
            case 1 -> {
                int createType = getChoice("Create");
                if (createType == 1) {
                    SunList sList = new SunList(true, acquireSunList(false));
                    System.out.println("List Created.");
                } else if (createType == 2) {
                    SunList sList = new SunList(false, acquireSunList(true));
                    System.out.println("List Created.");
                }
            }
            case 2 -> System.out.println("Modify SunList is under development.");
            case 3 -> System.out.println("SunList Methods are under development.");
        }
    }
}
