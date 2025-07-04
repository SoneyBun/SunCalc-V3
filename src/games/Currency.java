package games;

import java.util.HashMap;
import java.util.Map;

public class Currency {
    private static final Map<String, String> currencySymbols = new HashMap<>();
    private static final Map<String, String> currencyPlurals = new HashMap<>();

    private String currencyName;
    private String currencySymbol;
    private String plural;

    static {
        currencySymbols.put("dollar", "$");
        currencySymbols.put("cent", "¢");
        currencySymbols.put("euro", "€");
        currencySymbols.put("pound", "£");
        currencySymbols.put("yen", "¥");
        currencySymbols.put("won", "₩");
        currencySymbols.put("ruble", "₽");
        currencySymbols.put("indrupee", "₹");
        currencySymbols.put("peso", "₱");
        currencySymbols.put("naira", "₦");
        currencySymbols.put("tugrik", "₮");
        currencySymbols.put("benrupee", "৲");
        currencySymbols.put("mainrupee", "Rs");
        currencySymbols.put("rupee", "Rs");
        currencySymbols.put("tamrupee", "௹");
        currencySymbols.put("baht", "฿");
        currencySymbols.put("riel", "៛");
        currencySymbols.put("shekel", "₪");
        currencySymbols.put("dong", "₫");
        currencySymbols.put("kip", "₭");
        currencySymbols.put("guarani", "₲");
        currencySymbols.put("hryvnia", "₴");
        currencySymbols.put("cedi", "₵");
        currencySymbols.put("rial", "﷼");
        currencySymbols.put("balboa", "B/.");
        currencyPlurals.put("yen", "");
        currencyPlurals.put("naira", "");
        currencyPlurals.put("tugrik", "");
        currencyPlurals.put("baht", "");
        currencyPlurals.put("dong", "");
        currencyPlurals.put("guarani", "es");
        // Default plural is 's' (handled below)
    }

    public Currency(String currencyName) {
        this.currencyName = normalizeName(currencyName);
        this.currencySymbol = currencySymbols.getOrDefault(this.currencyName, "¤");
        this.plural = currencyPlurals.getOrDefault(this.currencyName, "s");
    }

    private String normalizeName(String name) {
        String lower = name.toLowerCase().trim();

        // Remove trailing 's' for plurals unless it's a currency with special plural
        if (lower.endsWith("s") && !currencyPlurals.containsKey(lower)) {
            lower = lower.substring(0, lower.length() - 1);
        }

        // Normalize rupee variations to "rupee"
        if (lower.equals("indrupee") || lower.equals("benrupee") ||
                lower.equals("mainrupee") || lower.equals("tamrupee")) {
            lower = "rupee";
        }

        return lower;
    }

    // Use getCurrencyType() for backward compatibility with your SunGames class
    public String getCurrencyType() {
        return currencySymbol;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public String getPlural() {
        return plural;
    }
}
