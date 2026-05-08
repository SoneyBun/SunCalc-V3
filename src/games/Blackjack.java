package games;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Blackjack {
    private static final Scanner scan = new Scanner(System.in);
    private final ArrayList<String> deck = new ArrayList<>();

    // ── Deck ────────────────────────────────────────────────────────────────

    private void buildDeck() {
        deck.clear();
        String[] suits = {"S", "H", "D", "C"};
        String[] ranks = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
        for (String suit : suits)
            for (String rank : ranks)
                deck.add(rank + suit);
        Collections.shuffle(deck);
    }

    private String deal() { return deck.remove(0); }

    // ── Hand helpers ────────────────────────────────────────────────────────

    /** Point value of a single card (Ace = 11, faces = 10). */
    private int cardPoints(String card) {
        String rank = card.replaceAll("[SHDC]", "");
        return switch (rank) {
            case "A"           -> 11;
            case "J","Q","K"   -> 10;
            default            -> Integer.parseInt(rank);
        };
    }

    /** Soft hand total (reduces aces to avoid bust). */
    private int total(ArrayList<String> hand) {
        int sum  = 0;
        int aces = 0;
        for (String c : hand) {
            int v = cardPoints(c);
            if (v == 11) aces++;
            sum += v;
        }
        while (sum > 21 && aces > 0) { sum -= 10; aces--; }
        return sum;
    }

    private String handStr(ArrayList<String> hand) {
        return String.join("  ", hand) + "   [" + total(hand) + "]";
    }

    // ── Core game ───────────────────────────────────────────────────────────

    /**
     * Play one hand of blackjack.
     *
     * @param bet        the initial bet
     * @param playerMoney total money the player currently has (used for double-down check)
     * @return net change in money (positive = win, negative = loss, 0 = push)
     */
    public int play(int bet, int playerMoney) {
        buildDeck();

        ArrayList<String> playerHand = new ArrayList<>();
        ArrayList<String> dealerHand = new ArrayList<>();

        playerHand.add(deal()); dealerHand.add(deal());
        playerHand.add(deal()); dealerHand.add(deal());

        System.out.println("\n--- Blackjack ---");
        System.out.println("Your hand  : " + handStr(playerHand));
        System.out.println("Dealer show: " + dealerHand.get(0) + "  [?]");

        // ── Natural blackjack check ─────────────────────────────────────────
        if (total(playerHand) == 21) {
            System.out.println("Dealer hand: " + handStr(dealerHand));
            if (total(dealerHand) == 21) {
                System.out.println("Both Blackjack → Push.");
                return 0;
            }
            int pay = (int) Math.floor(bet * 1.5);
            System.out.println("Blackjack! You win " + pay + ".");
            return pay;
        }

        // ── Player turn ─────────────────────────────────────────────────────
        boolean doubled = false;
        while (true) {
            boolean canDouble = (playerMoney >= bet * 2) && (playerHand.size() == 2);
            System.out.print("(H)it  (S)tand" + (canDouble ? "  (D)ouble down" : "") + " > ");
            String in = scan.nextLine().trim().toLowerCase();

            if (in.equals("d") && canDouble) {
                playerHand.add(deal());
                bet     *= 2;
                doubled  = true;
                System.out.println("Doubled down! Your hand: " + handStr(playerHand));
                break;                          // forced stand after double
            } else if (in.equals("h") || in.equals("hit")) {
                playerHand.add(deal());
                System.out.println("Your hand  : " + handStr(playerHand));
                if (total(playerHand) > 21) {
                    System.out.println("Bust! You lost " + bet + ".");
                    return -bet;
                }
                if (total(playerHand) == 21) break;
            } else if (in.equals("s") || in.equals("stand")) {
                break;
            } else {
                System.out.println("Invalid input.");
            }
        }

        // ── Dealer turn ─────────────────────────────────────────────────────
        System.out.println("Dealer hand: " + handStr(dealerHand));
        while (total(dealerHand) < 17) {
            String card = deal();
            dealerHand.add(card);
            System.out.println("Dealer hits: " + card + "   → " + handStr(dealerHand));
        }

        // ── Settle ──────────────────────────────────────────────────────────
        int p = total(playerHand);
        int d = total(dealerHand);

        if (d > 21) {
            System.out.println("Dealer busts! You win " + bet + ".");
            return bet;
        } else if (p > d) {
            System.out.println("You win " + bet + "!");
            return bet;
        } else if (d > p) {
            System.out.println("Dealer wins. You lost " + bet + ".");
            return -bet;
        } else {
            System.out.println("Push. No change.");
            return 0;
        }
    }
}