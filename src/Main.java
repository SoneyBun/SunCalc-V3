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
                adjustConfiguration();
                break;
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
                    System.out.println("Select Choice: \n↳ (1) ~ Configure\n↳ (2) ~ Math\n↳ (3) ~ Health");
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
                valid = false;
                break;
            case "Math" :
                while(!valid) {
                    System.out.println("Select Choice: \n↳ (1) ~ Single Operator\n↳ (2) ~ Unary\n↳ (3) ~ Variadic");
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
                valid = false;
                break;
        }
        return -1;
    }

    public static void adjustConfiguration() {
        System.out.println(config);
        // add config
    }
}