package ss.hotel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ss.hotel.bill.Bill;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test class for PricedSafe.
 * Tests password protection, activation, opening, and Bill.Item functionality.
 */
public class PricedSafeTest {

    private PricedSafe safe;
    private static final double PRICE = 6.36;
    private static final String PRICE_PATTERN = ".*6[.,]36.*";

    public String CORRECT_PASSWORD;
    public String WRONG_PASSWORD;

    @BeforeEach
    public void setUp() throws Exception {
        safe = new PricedSafe(PRICE);
        CORRECT_PASSWORD = safe.getPassword().getInitPass();
        WRONG_PASSWORD = CORRECT_PASSWORD + "WRONG";
        assertFalse(safe.isActive());
        assertFalse(safe.isOpen());
    }

    @Test
    public void testIsBillItem() throws Exception {
        assertNotNull(safe, "safe should be an instance of Bill.Item.");
        assertEquals(PRICE, safe.getPrice(), 0,
                "GetPrice should return the price of the safe.");
    }
    
    /**
     * Test if method getPrice works properly.
     */
    @Test
    public void testGetPrice() {
        assertEquals(PRICE, safe.getPrice(), 0.001,
                "getPrice should return the correct price");
    }
    
    /**
     * Test if method toString works properly and includes the price.
     */
    @Test
    public void testToString() {
        String result = safe.toString();
        assertTrue(result.matches(PRICE_PATTERN),
                "toString should include the price");
    }
    
    /**
     * Test that a deactivated safe can be activated with the correct password
     * and is activated and closed after that.
     */
    @Test
    public void testActivateWithCorrectPassword() {
        safe.activate(CORRECT_PASSWORD);
        assertTrue(safe.isActive(), "Safe should be activated with correct password");
        assertFalse(safe.isOpen(), "Safe should be closed after activation");
    }
    
    /**
     * Test that a deactivated safe cannot be activated with an incorrect password
     * (remains deactivated and closed).
     */
    @Test
    public void testActivateWithIncorrectPassword() {
        safe.activate(WRONG_PASSWORD);
        assertFalse(safe.isActive(), "Safe should not be activated with incorrect password");
        assertFalse(safe.isOpen(), "Safe should remain closed");
    }
    
    /**
     * Test if after trying to open a deactivated safe with the correct password
     * the safe is indeed deactivated and closed.
     */
    @Test
    public void testOpenDeactivatedSafeWithCorrectPassword() {
        safe.open(CORRECT_PASSWORD);
        assertFalse(safe.isActive(), "Safe should remain deactivated");
        assertFalse(safe.isOpen(), "Safe should remain closed");
    }
    
    /**
     * Test if after trying to open a deactivated safe with an incorrect password
     * the safe is indeed deactivated and closed.
     */
    @Test
    public void testOpenDeactivatedSafeWithIncorrectPassword() {
        safe.open(WRONG_PASSWORD);
        assertFalse(safe.isActive(), "Safe should remain deactivated");
        assertFalse(safe.isOpen(), "Safe should remain closed");
    }
    
    /**
     * Test that after activating a safe with the correct password it cannot be
     * opened with an incorrect password, but after being opened with the correct
     * password it is activated and open.
     */
    @Test
    public void testOpenActivatedSafeWithPasswords() {
        safe.activate(CORRECT_PASSWORD);
        safe.open(WRONG_PASSWORD);
        assertTrue(safe.isActive(), "Safe should remain activated");
        assertFalse(safe.isOpen(), "Safe should not be opened with incorrect password");
        
        safe.open(CORRECT_PASSWORD);
        assertTrue(safe.isActive(), "Safe should remain activated");
        assertTrue(safe.isOpen(), "Safe should be opened with correct password");
    }
    
    /**
     * Test if after activating and opening a safe with the correct password,
     * and closing it, the safe is closed and activated.
     */
    @Test
    public void testCloseAfterActivateAndOpen() {
        safe.activate(CORRECT_PASSWORD);
        safe.open(CORRECT_PASSWORD);
        safe.close();
        assertTrue(safe.isActive(), "Safe should remain activated after close");
        assertFalse(safe.isOpen(), "Safe should be closed");
    }
    
    /**
     * Test if after closing a deactivated safe, it is closed and deactivated.
     */
    @Test
    public void testCloseDeactivatedSafe() {
        safe.close();
        assertFalse(safe.isActive(), "Safe should remain deactivated");
        assertFalse(safe.isOpen(), "Safe should be closed");
    }
}
