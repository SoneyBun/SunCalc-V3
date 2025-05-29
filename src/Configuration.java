public class Configuration {
    /*
        Notation
        --------
        1 --> Prefix (+ A B)
        2 --> Infix (A + B)
        3 --> Postfix (A B +)
     */

    private int notation;

    /*
        Angular Mode
        ------------
        false --> Degrees
        true --> Radians
     */

    private boolean angularMode;

    /*
        Default
        -------
        Notation --> 2 (Infix)
        Angular Mode --> false (Degrees)
     */

    public Configuration() {
        notation = 2;
        angularMode = false;
    }

    // View Configuration

    public String toString() {
        String n = (notation == 1) ? "Prefix" : (notation == 2) ? "Infix" : "Postfix";
        String aM = (angularMode) ? "Radians" : "Degrees";
        return "Notation: " +  n + "\nAngular Mode: " + aM;
    }

    // Getter Methods

    public int getNotation() {
        return notation;
    }

    public boolean getAngularMode() {
        return angularMode;
    }

    // Setter Methods

    public void setNotation(int n) {
        notation = n;
    }

    public void setAngularMode(boolean aM) {
        angularMode = aM;
    }
}