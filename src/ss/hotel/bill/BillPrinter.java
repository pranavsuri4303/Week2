package ss.hotel.bill;

/**
 * Interface for printing bill lines with text and price.
 * Provides default formatting with 2 decimal places and comma separator.
 */
public interface BillPrinter {

    /**
     * Formats a bill line with text and price.
     *
     * @param text the description text
     * @param price the price value
     * @return formatted string ending with newline
     */
    default String format(String text, double price) {
        String formatted = String.format("%-20s %10.2f\n", text, price);
        return formatted.replace('.', ',');
    }

    /**
     * Prints a line with text and price to the output.
     *
     * @param text the description text
     * @param price the price value
     */
    void printLine(String text, double price);
}