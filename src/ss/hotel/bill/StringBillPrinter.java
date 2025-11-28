package ss.hotel.bill;

/**
 * BillPrinter implementation that collects all printed lines in a String.
 */
public class StringBillPrinter implements BillPrinter {
    private String result;

    /**
     * Creates a new StringBillPrinter with an empty result.
     */
    public StringBillPrinter() {
        this.result = "";
    }

    /**
     * Adds a formatted line to the result string.
     *
     * @param text the description text
     * @param price the price value
     */
    @Override
    public void printLine(String text, double price) {
        result += format(text, price);
    }

    /**
     * Returns the accumulated result string containing all printed lines.
     *
     * @return the result string
     */
    public String getResult() {
        return result;
    }
}