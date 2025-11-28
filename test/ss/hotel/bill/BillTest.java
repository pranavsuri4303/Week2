package ss.hotel.bill;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test class for Bill.
 * Tests the functionality of adding items, calculating sum, and closing bills.
 */
public class BillTest {

    private Bill bill;
    private StringBillPrinter printer;

    /**
     * Nested class implementing Bill.Item for testing purposes.
     * Provides a simple stub implementation with text and price.
     */
    private static class Item implements Bill.Item {
        private final String text;
        private final double price;

        /**
         * Creates a test item with text and price.
         * @param text the description text
         * @param price the price
         */
        public Item(String text, double price) {
            this.text = text;
            this.price = price;
        }

        @Override
        public String toString() {
            return text;
        }

        @Override
        public double getPrice() {
            return price;
        }
    }

    /**
     * Sets up a fresh Bill and StringBillPrinter before each test.
     */
    @BeforeEach
    public void setUp() {
        printer = new StringBillPrinter();
        bill = new Bill(printer);
    }

    /**
     * Tests the initial state of a Bill.
     * A new bill should have a sum of 0 and print only the total when closed.
     */
    @Test
    public void testBeginState() {
        assertEquals(0.0, bill.getSum(), 0.001, "Initial sum should be 0");
        bill.close();
        String result = printer.getResult();
        assertTrue(result.contains("Total"), "Result should contain 'Total'");
        assertTrue(result.contains("0"), "Result should contain 0");
    }

    /**
     * Tests adding items to a bill.
     * Verifies that items are added correctly, sum is calculated properly,
     * and items are printed when added and total is printed when closed.
     */
    @Test
    public void testNewItem() {
        Item item1 = new Item("Room", 100.0);
        Item item2 = new Item("Safe", 10.0);

        bill.addItem(item1);
        bill.addItem(item2);

        assertEquals(110.0, bill.getSum(), 0.001, "Sum should be 110.0");

        bill.close();

        String result = printer.getResult();
        assertTrue(result.contains("Room"), "Result should contain 'Room'");
        assertTrue(result.contains("Safe"), "Result should contain 'Safe'");
        assertTrue(result.contains("100"), "Result should contain price 100");
        assertTrue(result.contains("10"), "Result should contain price 10");
        assertTrue(result.contains("Total"), "Result should contain 'Total'");
        assertTrue(result.contains("110"), "Result should contain total 110");
    }
}