//package ss.hotel;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import ss.hotel.bill.Bill;
//
//public class PricedSafeTest {
//
//    private PricedSafe safe;
//    private static final double PRICE = 6.36;
//    private static final String PRICE_PATTERN = ".*6[.,]36.*";
//
//    public String CORRECT_PASSWORD;
//    public String WRONG_PASSWORD;
//
//
//    @BeforeEach
//    public void setUp() throws Exception {
//        safe = new PricedSafe(PRICE);
//        CORRECT_PASSWORD = safe.getPassword().getInitPass();
//        WRONG_PASSWORD = CORRECT_PASSWORD + "WRONG";
//        assertFalse(safe.isActive());
//        assertFalse(safe.isOpen());
//    }
//
//    @Test
//    public void testIsBillItem() throws Exception {
//    	assertTrue(safe instanceof Bill.Item,
//    			"safe should be an instance of Bill.Item.");
//        assertEquals(PRICE, safe.getPrice(), 0,
//        		"GetPrice should return the price of the safe.");
//    }
//}
