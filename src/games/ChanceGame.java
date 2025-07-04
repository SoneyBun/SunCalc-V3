package games;

import java.util.Scanner;

public class ChanceGame {
    private static final Scanner scan = new Scanner(System.in);

    /**
     * Play the classic chance game.
     * 3/7 chance to win 3x bet, 3/7 chance to lose bet, 1/7 chance to push (no gain or loss).
     *
     * @param bet the bet amount
     * @return the amount won (positive), lost (negative), or 0 for push
     */
    public int play(int bet) {
        while (true) {
            System.out.print("Type 'play' to roll the chance game or 'quit' to return: ");
            String input = scan.nextLine().trim();
            if (input.equalsIgnoreCase("quit")) {
                System.out.println("Returning to game selection.");
                return 0;
            }
            if (input.equalsIgnoreCase("play")) {
                break;
            }
            System.out.println("Invalid input. Please type 'play' or 'quit'.");
        }

        int chance = (int) (Math.random() * 7);
        if (chance <= 2) {
            int winAmount = 3 * bet;
            System.out.println("Congrats! You won " + winAmount + ".");
            return winAmount;
        } else if (chance <= 5) {
            System.out.println("Sorry, you lost your bet of " + bet + ".");
            return -bet;
        } else {
            System.out.println("It's a push! No money won or lost.");
            return 0;
        }
    }
}
