package ss.hotel.password;

/**
 * Representation of a password.
 */
public class BasicPassword {

    /**
     * The standard initial password.
     */
    public static final String INITIAL = "initial";
    /**
     * The current password.
     */
    private String password;

    /**
     * Constructs a Password with the initial word provided in INITIAL.
     */
    public BasicPassword() {
        this.password = INITIAL;
    }

    /**
     * Test if a given string is an acceptable password.
     * <p>
     * Not acceptable:
     * <ul>
     *     <li>A word with less than 6 characters</li>
     *     <li>A word that contains a space</li>
     * </ul>
     *
     * @param suggestion Word that should be tested
     * @return true If suggestion is acceptable
     */
    public boolean acceptable(String suggestion) {
        return suggestion != null && suggestion.length() >= 6 && !suggestion.contains(" ");
    }

    /**
     * Tests if a given word is equal to the current password.
     *
     * @param test Word that should be tested
     * @return true If test is equal to the current password
     */
    public boolean testWord(String test) {
        return password.equals(test);
    }

    /**
     * Changes this password.
     *
     * @param oldpass The current password
     * @param newpass The new password
     * @return true If oldPass is equal to the current password
     *         and newpass is an acceptable password
     */
    public boolean setWord(String oldpass, String newpass) {
        if (testWord(oldpass) && acceptable(newpass)) {
            this.password = newpass;
            return true;
        }
        return false;
    }
}
