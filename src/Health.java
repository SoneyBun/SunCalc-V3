import java.util.Scanner;

public class Health {
    private static final Scanner scan = new Scanner(System.in);

    // Error Messages
    static final Error invalidNumber = new Error(71, "Invalid numeric input");
    static final Error invalidPercent = new Error(72, "Body fat % must be a number between 0 and 100");

    // Flags
    private static boolean metric;
    private static boolean male;

    // Main Menu Entry
    public static void start() {
        while (true) {
            int choice = Main.getChoice("Health");
            switch (choice) {
                case 1 -> calculateBMI();
                case 2 -> calculateBMR();
                case 3 -> estimateHeight();
                case 4 -> { return; }
                default -> System.out.println(invalidNumber);
            }
        }
    }

    // Helper Input Functions
    private static double getDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(scan.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(invalidNumber);
            }
        }
    }

    private static int getInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scan.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(invalidNumber);
            }
        }
    }

    private static float getBodyFatPercent() {
        while (true) {
            System.out.print("Body Fat Percentage (%): ");
            try {
                float bf = Float.parseFloat(scan.nextLine().trim());
                if (bf >= 0 && bf <= 100) return bf / 100;
                System.out.println(invalidPercent);
            } catch (NumberFormatException e) {
                System.out.println(invalidPercent);
            }
        }
    }

    // Option Choosers
    private static void askSystem() {
        System.out.print("Enter Measurement System (Metric/Imperial): ");
        metric = scan.nextLine().trim().toLowerCase().startsWith("m");
    }

    private static void askGender() {
        System.out.print("Biological Gender (Male/Female): ");
        male = scan.nextLine().trim().toLowerCase().startsWith("m");
    }

    // BMI Calculation
    public static void calculateBMI() {
        askSystem();
        double height = metric ? getDouble("Height (cm): ") / 100 : getDouble("Height (in): ") * 0.0254;
        double weight = metric ? getDouble("Weight (kg): ") : getDouble("Weight (lbs): ") * 0.453592;

        double bmi = weight / (height * height);
        System.out.printf("BMI: %.2f\n", bmi);
        if (bmi < 18.5) System.out.println("Weight Class: Underweight\n");
        else if (bmi < 25) System.out.println("Weight Class: Normal\n");
        else if (bmi < 30) System.out.println("Weight Class: Overweight\n");
        else System.out.println("Weight Class: Obese\n");
    }

    // BMR Calculation (3 Equations)
    public static void calculateBMR() {
        askSystem();
        askGender();

        double height = metric ? getDouble("Height (cm): ") : getDouble("Height (in): ") * 2.54;
        double weight = metric ? getDouble("Weight (kg): ") : getDouble("Weight (lbs): ") * 0.453592;
        int age = getInt("Age (Years): ");
        if (age < 15 || age > 80) System.out.println("⚠ BMR results may be inaccurate for this age.");
        float bf = getBodyFatPercent();

        double mifflin = (10 * weight) + (6.25 * height) - (5 * age) + (male ? 5 : -161);
        double harris = male ?
                (13.397 * weight) + (4.799 * height) - (5.677 * age) + 88.362 :
                (9.247 * weight) + (3.098 * height) - (4.33 * age) + 447.593;
        double katch = 370 + (21.6 * (1 - bf) * weight);

        System.out.printf("Mifflin-St Jeor: %.2f cal/day\n", mifflin);
        System.out.printf("Harris-Benedict: %.2f cal/day\n", harris);
        System.out.printf("Katch-McArdle: %.2f cal/day\n", katch);
    }

    // Height Estimator
    public static void estimateHeight() {
        askSystem();
        askGender();

        double mother = getDouble("Mother's Height (" + (metric ? "cm" : "in") + "): ");
        double father = getDouble("Father's Height (" + (metric ? "cm" : "in") + "): ");

        int adjustment = male ? (metric ? 13 : 5) : (metric ? -13 : -5);
        double estimated = (mother + father + adjustment) / 2;

        System.out.printf("Estimated Adult Height: %.2f %s\n\n", estimated, metric ? "cm" : "in");
    }
}
