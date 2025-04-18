import java.util.Scanner;
//import java.util.ArrayList;

public class Main {
    static Configuration config = new Configuration();
    static Scanner scan = new Scanner(System.in);

    // Main Errors

    static Error invalid = new Error(01, "Invalid Input");

    public static void main(String[] args) {
        System.out.println("SunCalc V3\n\n");
        getChoice();
        adjustConfiguration();
    }

    public static int getChoice() {
        boolean valid = false;
        int input;
        while(!valid) {
            System.out.println("Select Choice: \n↳ (1) ~ Configure\n↳ (2) ~ Math\n↳ (3) ~ Health");
            try {
                input = Integer.parseInt(scan.nextLine());
                System.out.println(input);
                if(input > 0 && input < 4) {
                    valid = true;
                    return input;
                }
            } catch (NumberFormatException e) {
                System.out.println(invalid);
            }
        }
        return -1;
    }

    public static void adjustConfiguration() {
        System.out.println(config);
    }
}