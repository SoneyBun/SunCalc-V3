package games;

import java.util.Scanner;

public class DiceGame {
    private static final Scanner scan = new Scanner(System.in);

    /**
     * Play the dice game.
     * User guesses a number from 1 to 6.
     * If guess matches dice roll, wins 3x bet; else loses bet.
     *
     * @param bet the bet amount
     * @return the amount won (positive) or lost (negative)
     */
    public int play(int bet) {
        int guess = 0;
        while (true) {
            System.out.print("Guess the dice roll (1-6) or type 'quit' to return: ");
            String input = scan.nextLine().trim();
            if (input.equalsIgnoreCase("quit")) {
                System.out.println("Returning to game selection.");
                return 0;
            }
            try {
                guess = Integer.parseInt(input);
                if (guess < 1 || guess > 6) {
                    System.out.println("Please enter a number between 1 and 6.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 6 or 'quit'.");
            }
        }

        int diceRoll = (int) (Math.random() * 6) + 1;
        System.out.println("Dice rolled: " + diceRoll);

        if (guess == diceRoll) {
            int winnings = bet * 3;
            System.out.println("Congrats! You won " + winnings + ".");
            return winnings;
        } else {
            System.out.println("Sorry, you lost your bet of " + bet + ".");
            return -bet;
        }
    }
}
