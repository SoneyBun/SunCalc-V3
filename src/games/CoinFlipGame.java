package games;

import java.util.Scanner;

public class CoinFlipGame {
    private static final Scanner scan = new Scanner(System.in);

    /**
     * Play the coin flip game.
     * User guesses Heads (H) or Tails (T).
     * Wins 1x bet if correct, loses bet if wrong.
     *
     * @param bet the bet amount
     * @return the amount won (positive) or lost (negative)
     */

    public int play(int bet) {
        String guess = "";
        while (true) {
            System.out.print("Guess the coin flip (H/T) or type 'quit' to return: ");
            String input = scan.nextLine().trim();
            if (input.equalsIgnoreCase("quit")) {
                System.out.println("Returning to game selection.");
                return 0;
            }
            if (input.equalsIgnoreCase("H") || input.equalsIgnoreCase("Heads")) {
                guess = "Heads";
                break;
            } else if (input.equalsIgnoreCase("T") || input.equalsIgnoreCase("Tails")) {
                guess = "Tails";
                break;
            } else {
                System.out.println("Invalid input. Please enter 'H' for Heads, 'T' for Tails, or 'quit'.");
            }
        }

        int flip = (int) (Math.random() * 2);
        String flipResult = (flip == 0) ? "Heads" : "Tails";
        System.out.println("Coin flipped: " + flipResult);

        if (guess.equals(flipResult)) {
            System.out.println("Congrats! You won " + bet + ".");
            return bet;
        } else {
            System.out.println("Sorry, you lost your bet of " + bet + ".");
            return -bet;
        }
    }
}
