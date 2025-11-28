package ss.hotel.bill;

/**
 * Bill having multiple items.
 */
public class Bill {
    
    /**
     * Abstraction of the items on the bill.
     * Every item has a price and description; the description is available with toString().
     */
    public interface Item {
        /**
         * Returns the price of this Item.
         * @return the price of this Item
         */
        double getPrice();
        
        /**
         * Returns the description of this Item.
         * @return the description string
         */
        String toString();
    }
    
    private final BillPrinter billPrinter;
    private double sum;
    
    /**
     * Constructs a Bill
     * @param billPrinter the printer to send the bill to
     */
    public Bill(BillPrinter billPrinter) {
        this.billPrinter = billPrinter;
        this.sum = 0.0;
    }
    
    /**
     * Adds an item to this Bill.
     * The item is sent to the printer, and the price is added to the sum of the Bill.
     * @param item the new item
     */
    public void addItem(Item item) {
        billPrinter.printLine(item.toString(), item.getPrice());
        sum += item.getPrice();
    }
    
    /**
     * Sends the sum total of the bill to the printer.
     */
    public void close() {
        billPrinter.printLine("Total", sum);
    }
    
    /**
     * Returns the current sum total of the Bill.
     * @return the sum total
     */
    public double getSum() {
        return sum;
    }
}