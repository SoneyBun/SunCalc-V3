public class Configuration {
    /*
        Notation
        --------
        1 --> Prefix (+ A B) (! A)
        2 --> Infix (A + B) (! A)
        3 --> Postfix (A B +) (A !)
     */

    private int notation;
    private String notationString;

    /*
        Angular Mode
        ------------
        false --> Degrees
        true --> Radians
     */

    private boolean angularMode;
    private String angularModeString;

    /*
        Default
        -------
        Notation --> 2 (Infix)
        Angular Mode --> false (Degrees)
     */

    public Configuration() {
        notation = 2;
        notationString = "Infix";
        angularMode = false;
        angularModeString = "Degrees";
    }

    // View Configuration

    public String toString() {
        return "Notation: " + notationString + "\nAngular Mode: " + angularModeString;
    }

    // Getter Methods

    public int getNotation() {
        return notation;
    }

    public String getNotationString() {
        return notationString;
    }

    public boolean getAngularMode() {
        return angularMode;
    }

    public String getAngularModeString() {
        return angularModeString;
    }

    // Setter Methods

    public void setNotation(int n) {
        notation = n;
        notationString = (n == 1) ? "Prefix" : (n == 2) ? "Infix" : "Postfix";
    }

    public void setAngularMode(boolean aM) {
        angularMode = aM;
        angularModeString = (aM) ? "Radians" : "Degrees";
    }
}