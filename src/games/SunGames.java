package games;

import java.util.Scanner;

public class SunGames {
    private static final Scanner scan = new Scanner(System.in);
    private static Currency userCurrency = null;
    private static int money = 0;

    public static void start() {
        System.out.println("Welcome to SunGames!");

        if (userCurrency == null || money == 0) {
            System.out.print("Enter your currency (e.g., Dollar, Euro): ");
            String currencyInput = scan.nextLine().trim();
            userCurrency = new Currency(currencyInput);
            money = 100;
        }

        while (true) {
            System.out.println("\nYou have "
                    + userCurrency.getCurrencyType() + money + " "
                    + userCurrency.getCurrencyName() + userCurrency.getPlural() + ".");
            System.out.println("Select a game:");
            System.out.println("↳ (1) Dice Game");
            System.out.println("↳ (2) Coin Flip");
            System.out.println("↳ (3) Chance Game");
            System.out.println("↳ (4) Blackjack");
            System.out.println("↳ (5) Exit SunGames");
            System.out.print("Choice: ");

            String input = scan.nextLine().trim();
            if (input.equalsIgnoreCase("quit") || input.equals("5")) {
                System.out.println("Exiting SunGames. Returning to main menu.");
                break;
            }

            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid choice.");
                continue;
            }

            if (choice < 1 || choice > 5) {
                System.out.println("Invalid choice. Try again.");
                continue;
            }

            // Gather bet
            int bet = 0;
            while (true) {
                System.out.print("You have "
                        + userCurrency.getCurrencyType() + money
                        + ". Enter your bet (or 'quit' to return): ");
                String betInput = scan.nextLine().trim();
                if (betInput.equalsIgnoreCase("quit")) break;
                try {
                    bet = Integer.parseInt(betInput);
                    if (bet <= 0 || bet > money) {
                        System.out.println("Invalid bet. Must be > 0 and <= your balance.");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Enter a number or 'quit'.");
                }
            }
            if (bet == 0) continue;

            int result = switch (choice) {
                case 1 -> new DiceGame().play(bet);
                case 2 -> new CoinFlipGame().play(bet);
                case 3 -> new ChanceGame().play(bet);
                case 4 -> new Blackjack().play(bet, money);
                default -> 0;
            };

            money += result;

            if (money <= 0) {
                System.out.println("You have no money left. Resetting to 100.");
                money = 100;
            }
        }
    }
}