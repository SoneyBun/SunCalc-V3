import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    // Essential Objects
    public static Configuration config = new Configuration();
    static final Scanner scan = new Scanner(System.in);
    static Data store;
    static Operation math;

    // Main Errors
    static final Error invalid = new Error(51, "Invalid Input");

    // Track active SunList name
    static String activeSunListName = null;

    public static void main(String[] args) {
        System.out.println("SunCalc V3\n");

        while (true) {
            int choice = getChoice("Menu");
            switch (choice) {
                case 1 -> configure();
                case 2 -> {
                    int mathChoice = getChoice("Math");
                    switch (mathChoice) {
                        case 1 -> {
                            System.out.println("Enter Math:");
                            while (true) {
                                String term = scan.nextLine();
                                if (term.equalsIgnoreCase("quit")) {
                                    break;
                                } else {
                                    store = new Data(term);
                                    math = new Operation(store.getOperator(), store.getNumbers());
                                    System.out.println(math.executeOperation());
                                }
                            }
                        }
                        case 2 -> sunListMenu();
                    }
                }
                case 3 -> Health.start();
                case 4 -> {
                    System.out.println("Thank you for using SunCalc V3\nSession Concluded");
                    System.exit(0);
                }
                default -> System.out.println(invalid);
            }
        }
    }

    // SunList menu loop
    public static void sunListMenu() {
        while (true) {
            int choice = getChoice("SunList");
            switch (choice) {
                case 1 -> createSunList();
                case 2 -> modifySunList();
                case 3 -> sunListAnalysis();
                case 4 -> {
                    return; // back to main menu
                }
                default -> System.out.println(invalid);
            }
        }
    }

    // Create SunList
    public static void createSunList() {
        int createChoice = getChoice("Create");
        boolean isNumbers = (createChoice == 1);
        ArrayList<Point> points = acquireSunList(!isNumbers);
        SunList newList = new SunList(isNumbers, points);

        System.out.print("Enter a name for this SunList: ");
        String name = scan.nextLine().trim();
        newList.save(name);
        activeSunListName = name;
        System.out.println("SunList '" + name + "' saved and set as active.");
    }

    // Modify active SunList
    public static void modifySunList() {
        if (activeSunListName == null) {
            System.out.println("No active SunList. Please create or select one first.");
            return;
        }
        SunList activeList = SunList.getList(activeSunListName);
        if (activeList == null) {
            System.out.println("Active SunList not found. Please create or select one.");
            activeSunListName = null;
            return;
        }

        System.out.println("Active SunList: " + activeSunListName);
        System.out.println(activeList);

        System.out.println("Modify Options:\n1) Add Point\n2) Remove Point\n3) Back");
        int modChoice = getInput("Select an option (1-3):", 3);
        switch (modChoice) {
            case 1 -> {
                System.out.print("Enter X: ");
                double x = parseDoubleInput();
                double y = 0;
                if (!activeList.isNumbers()) {
                    System.out.print("Enter Y: ");
                    y = parseDoubleInput();
                }
                activeList.addPoint(new Point(x, y));
                System.out.println("Point added.");
            }
            case 2 -> {
                System.out.print("Enter index of point to remove: ");
                int idx = parseIntInput();
                if (activeList.removePoint(idx)) {
                    System.out.println("Point removed.");
                } else {
                    System.out.println("Invalid index.");
                }
            }
            case 3 -> {
                // Back
            }
            default -> System.out.println(invalid);
        }
    }

    // Perform statistical analysis on active SunList
    public static void sunListAnalysis() {
        if (activeSunListName == null) {
            System.out.println("No active SunList. Please create or select one first.");
            return;
        }

        SunList list = SunList.getList(activeSunListName);
        if (list == null) {
            System.out.println("Active SunList not found. Please create or select one.");
            activeSunListName = null;
            return;
        }

        switch (getChoice("SunListAnalysis")) {
            case 1 -> System.out.printf("Mean X: %.4f, Mean Y: %.4f%n", list.meanX(), list.meanY());
            case 2 -> System.out.printf("Std Dev X: %.4f, Std Dev Y: %.4f%n", list.stdDevX(), list.stdDevY());
            case 3 -> System.out.printf("Correlation Coefficient (r): %.4f%n", list.correlationCoefficient());
            case 4 -> {
                double[] lr = list.linearRegression();
                System.out.printf("Linear Regression: y = %.4fx + %.4f%n", lr[0], lr[1]);
            }
            case 5 -> {
                double[] pr = list.polynomialRegression();
                System.out.printf("Polynomial Regression (degree 2): y = %.4fx² + %.4fx + %.4f%n", pr[0], pr[1], pr[2]);
            }
            case 6 -> System.out.printf("SST: %.4f, SSR: %.4f, SSE: %.4f%n",
                    list.sumSquaresTotal(), list.sumSquaresRegression(), list.sumSquaresError());
            case 7 -> {}
            default -> System.out.println(invalid);
        }
    }


    // Helper to parse integer input safely
    public static int parseIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scan.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(invalid);
            }
        }
    }

    // Helper to parse double input safely
    public static double parseDoubleInput() {
        while (true) {
            try {
                return Double.parseDouble(scan.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(invalid);
            }
        }
    }

    public static int getChoice(String type) {
        return switch (type) {
            case "Menu" -> getInput("Select Choice:\n↳ (1) ~ Configure\n↳ (2) ~ Math\n↳ (3) ~ Health\n↳ (4) ~ Quit", 4);
            case "Configure" -> getInput("Select Choice:\n↳ (1) ~ Notation\n↳ (2) ~ Angular Mode", 2);
            case "Notation" -> getInput("Select Choice:\n↳ (1) ~ Prefix\n↳ (2) ~ Infix\n↳ (3) ~ Postfix", 3);
            case "Angular Mode" -> getInput("Select Choice:\n↳ (1) ~ Degrees\n↳ (2) ~ Radians", 2);
            case "Math" -> getInput("Select Choice:\n↳ (1) ~ Arithmetic\n↳ (2) ~ SunList", 2);
            case "Health" -> getInput("Select Health Option:\n↳ (1) ~ BMI\n↳ (2) ~ BMR\n↳ (3) ~ Height Estimate\n↳ (4) ~ Back", 4);
            case "SunList" -> getInput("Select Choice:\n↳ (1) ~ Create SunList\n↳ (2) ~ Modify SunList\n↳ (3) ~ SunList Methods\n↳ (4) ~ Back", 4);
            case "Create" -> getInput("Select Choice:\n↳ (1) ~ Numbers\n↳ (2) ~ Points", 2);
            case "SunListAnalysis" -> getInput("Select Analysis:\n↳ (1) ~ Mean X, Mean Y\n↳ (2) ~ Std Dev X, Std Dev Y\n↳ (3) ~ Correlation Coefficient\n↳ (4) ~ Linear Regression (slope & intercept)\n↳ (5) ~ Polynomial Regression (degree 2)\n↳ (6) ~ Sum Squares Total, Regression, Error\n↳ (7) ~ Back", 7);
            default -> -1;
        };
    }

    public static int getInput(String choices, int max) {
        int input;
        while (true) {
            System.out.println(choices);
            try {
                input = Integer.parseInt(scan.nextLine());
                if (input > 0 && input <= max) {
                    return input;
                }
            } catch (NumberFormatException e) {
                System.out.println(invalid);
            }
        }
    }

    public static ArrayList<Point> acquireSunList(boolean type) {
        ArrayList<Point> list = new ArrayList<>();

        if (!type) {
            // Numbers → Y = 0
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
            // Points → Ask for x and y
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
}