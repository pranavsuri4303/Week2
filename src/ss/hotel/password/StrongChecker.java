package ss.hotel.password;

/**
 * Strong password checker that extends BasicChecker with additional rules:
 * <ul>
 *     <li>Must be at least 6 characters long (from BasicChecker)</li>
 *     <li>Must not contain spaces (from BasicChecker)</li>
 *     <li>Must start with a letter</li>
 *     <li>Must end with a digit</li>
 * </ul>
 */
public class StrongChecker extends BasicChecker {
    
    /**
     * Tests if a password meets strong requirements:
     * basic requirements plus must start with a letter and end with a digit.
     *
     * @param suggestion the password string to test
     * @return true if the password meets all strong requirements
     */
    @Override
    public boolean acceptable(String suggestion) {
        // First check basic requirements
        if (!super.acceptable(suggestion)) {
            return false;
        }
        
        // Check if starts with letter
        if (!Character.isLetter(suggestion.charAt(0))) {
            return false;
        }
        
        // Check if ends with digit
        if (!Character.isDigit(suggestion.charAt(suggestion.length() - 1))) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Generates a strong acceptable password.
     *
     * @return a constant password string that meets strong requirements
     */
    @Override
    public String generatePassword() {
        return "Password1";
    }
}