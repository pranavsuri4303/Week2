package ss.hotel.password;

/**
 * Basic password checker that validates passwords based on:
 * <ul>
 *     <li>Minimum length of 6 characters</li>
 *     <li>No spaces allowed</li>
 * </ul>
 */
public class BasicChecker implements Checker {
    
    /**
     * Tests if a password meets basic requirements:
     * at least 6 characters long and contains no spaces.
     *
     * @param suggestion the password string to test
     * @return true if the password is at least 6 characters and has no spaces
     */
    @Override
    public boolean acceptable(String suggestion) {
        return suggestion != null &&
               suggestion.length() >= 6 &&
               !suggestion.contains(" ");
    }
    
    /**
     * Generates a basic acceptable password.
     *
     * @return a constant password string that meets basic requirements
     */
    @Override
    public String generatePassword() {
        return "password123";
    }
}