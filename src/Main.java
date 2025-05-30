import java.util.Scanner;
//import java.util.ArrayList;

public class Main {
    static Configuration config = new Configuration();
    static Scanner scan = new Scanner(System.in);

    // Main Errors

    static Error invalid = new Error(01, "Invalid Input");

    public static void main(String[] args) {
        System.out.println("SunCalc V3\n");
        int choice = getChoice("Menu");
        switch(choice) {
            case 1 :
                configure();
                getChoice("Menu");
            case 2 :
                getChoice("Math");
                break;
            case 3 :
                System.out.println("Health is coming soon");
                break;
        }
    }

    public static int getChoice(String type) {
        boolean valid = false;
        int input;
        switch (type) {
            case "Menu" :
                while(!valid) {
                    System.out.println("Select Choice:\n↳ (1) ~ Configure\n↳ (2) ~ Math\n↳ (3) ~ Health");
                    try {
                        input = Integer.parseInt(scan.nextLine());
                        if(input > 0 && input < 4) {
                            valid = true;
                            return input;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(invalid);
                    }
                }
                break;
            case "Configure" :
                while(!valid) {
                    System.out.println("Select Choice:\n↳ (1) ~ Notation\n↳ (2) ~ Angular Mode");
                    try {
                        input = Integer.parseInt(scan.nextLine());
                        if(input > 0 && input < 3) {
                            valid = true;
                            return input;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(invalid);
                    }
                }
                break;
            case "Notation" :
                while(!valid) {
                    System.out.println("Select Choice:\n↳ (1) ~ Prefix\n↳ (2) ~ Infix\n↳ (3) ~ Postfix");
                    try {
                        input = Integer.parseInt(scan.nextLine());
                        if (input > 0 && input < 4) {
                            valid = true;
                            return input;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(invalid);
                    }
                }
                break;
            case "Angular Mode" :
                while(!valid) {
                    System.out.println("Select Choice:\n↳ (1) ~ Degrees\n↳ (2) ~ Radians");
                    try {
                        input = Integer.parseInt(scan.nextLine());
                        if(input > 0 && input < 3) {
                            valid = true;
                            return input;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(invalid);
                    }
                }
                break;
        }
        return -1;
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