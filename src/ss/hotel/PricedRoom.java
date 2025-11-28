package ss.hotel;

import ss.hotel.bill.Bill;

/**
 * A Room that costs money per night and includes a PricedSafe.
 * Extends Room and implements Bill.Item.
 */
public class PricedRoom extends Room implements Bill.Item {

    private final double pricePerNight;

    /**
     * Creates a PricedRoom with the given room number, price per night, and safe cost.
     * The room is created with a new PricedSafe.
     *
     * @param number the room number
     * @param pricePerNight the price per night for this room
     * @param safeCost the cost of the safe in this room
     */
    public PricedRoom(int number, double pricePerNight, double safeCost) {
        super(number, new PricedSafe(safeCost));
        this.pricePerNight = pricePerNight;
    }

    /**
     * Returns the price per night of this room.
     * @return the price per night
     */
    @Override
    public double getPrice() {
        return pricePerNight;
    }

    /**
     * Returns a string representation including the room number and price per night.
     * @return string with room number and price information
     */
    @Override
    public String toString() {
        return String.format("Room %d (%.1f/night)", getNumber(), pricePerNight);
    }
}