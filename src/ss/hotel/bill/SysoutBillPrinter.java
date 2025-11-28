package ss.hotel.bill;

/**
 * BillPrinter implementation that prints directly to standard output.
 */
public class SysoutBillPrinter implements BillPrinter {

    /**
     * Prints a formatted line to standard output.
     *
     * @param text the description text
     * @param price the price value
     */
    @Override
    public void printLine(String text, double price) {
        System.out.print(format(text, price));
    }

    /**
     * Main method demonstrating the SysoutBillPrinter functionality.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        BillPrinter p = new SysoutBillPrinter();
        p.printLine("Text1", 1.0);
        p.printLine("Other text", -12.1212);
        p.printLine("Something", 0.2);
    }
}