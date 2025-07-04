package games;

public class Strike {
    private final int money;
    private final int bet;
    private int strikes;

    public Strike(int money, int bet, int strikes) {
        this.money = money;
        this.bet = bet;
        this.strikes = strikes;
    }

    // Returns true if bet is invalid (more than money, zero, or negative)
    public boolean isInvalidBet() {
        return bet > money || bet <= 0;
    }

    // Increase strikes count and return the appropriate message
    public String getStrikeMessage() {
        strikes++;
        if (bet > money) {
            return "Strike " + strikes + ": You cannot bet more than what you have!\nReport any problems at https://bit.ly/SunCode";
        } else if (bet == 0) {
            return "Strike " + strikes + ": You cannot bet a value of zero!\nReport any problems at https://bit.ly/SunCode";
        } else if (bet < 0) {
            return "Strike " + strikes + ": You cannot bet a negative value!\nReport any problems at https://bit.ly/SunCode";
        }
        return "";
    }

    public int getStrikes() {
        return strikes;
    }
}
