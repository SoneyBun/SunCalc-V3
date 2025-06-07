public class Error {
    // Error Identification

    private final int identification;

    // Error Reason

    private final String reason;

    // Error Constructor

    public Error(int i, String r) {
        identification = i;
        reason = r;
    }

    // Print Error
    
    public String toString() {
        return "Error #" + identification + " ~ " + reason;
    }
}