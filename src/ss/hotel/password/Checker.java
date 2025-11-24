package ss.hotel.password;

/**
 * Interface for password validation and generation.
 * Implementations of this interface define what constitutes
 * an acceptable password and can generate example passwords.
 */
public interface Checker {
    
    /**
     * Tests if a given string is an acceptable password
     * according to the rules of this Checker.
     *
     * @param suggestion the password string to test
     * @return true if the suggestion is acceptable, false otherwise
     */
    boolean acceptable(String suggestion);
    
    /**
     * Generates an example of an acceptable password.
     * The returned string must satisfy the acceptable() method.
     *
     * @return a valid password string
     */
    String generatePassword();
}