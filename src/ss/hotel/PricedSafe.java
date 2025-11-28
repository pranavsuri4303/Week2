package ss.hotel;

import ss.hotel.bill.Bill;
import ss.hotel.password.Password;

/**
 * A password-protected Safe that costs money.
 * Extends Safe and implements Bill.Item.
 * Can only be activated and opened with the correct password.
 */
public class PricedSafe extends Safe implements Bill.Item {

    private final double price;
    private final Password password;

    /**
     * Creates a PricedSafe with the given price.
     * @param price the price of the safe (must be non-negative)
     */
    public PricedSafe(double price) {
        super();
        assert price >= 0 : "Price must be non-negative";
        this.price = price;
        this.password = new Password();
    }

    /**
     * Activates the safe if the given password is correct.
     * @param password the password to check (must not be null or empty)
     */
    public void activate(String password) {
        assert password != null : "Password must not be null";
        assert !password.isEmpty() : "Password must not be empty";

        if (this.password.testWord(password)) {
            super.activate();
        }
    }

    /**
     * Overrides the parent activate method.
     * Gives a warning and does not activate the safe.
     */
    @Override
    public void activate() {
        System.out.println("Warning: PricedSafe requires a password to activate");
    }

    /**
     * Deactivates the safe and closes it.
     */
    @Override
    public void deactivate() {
        super.deactivate();
    }

    /**
     * Opens the safe if it is active and the password is correct.
     * @param password the password to check (must not be null or empty)
     */
    public void open(String password) {
        assert password != null : "Password must not be null";
        assert !password.isEmpty() : "Password must not be empty";

        if (isActive() && this.password.testWord(password)) {
            super.open();
        }
    }

    /**
     * Overrides the parent open method.
     * Does not change the state of the safe.
     */
    @Override
    public void open() {
        // Does nothing - password required
    }

    /**
     * Closes the safe without changing activation status.
     */
    @Override
    public void close() {
        super.close();
    }

    /**
     * Returns the Password object.
     * @return the password object
     */
    public Password getPassword() {
        return password;
    }

    /**
     * Returns the price of this safe.
     * @return the price
     */
    @Override
    public double getPrice() {
        return price;
    }

    /**
     * Returns a string representation including the price.
     * @return string with price information
     */
    @Override
    public String toString() {
        return String.format("Safe for %.2f", price);
    }

    /**
     * Main method for testing assertions.
     * Violates precondition by passing negative price.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // This should trigger an AssertionError when assertions are enabled with -ea
        PricedSafe safe = new PricedSafe(-10.0);
        System.out.println("Safe created: " + safe);
    }
}