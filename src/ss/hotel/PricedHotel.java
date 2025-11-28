package ss.hotel;

import ss.hotel.bill.Bill;
import ss.hotel.bill.BillPrinter;

/**
 * A Hotel with priced rooms and the ability to generate bills for guests.
 * Extends Hotel with billing functionality.
 */
public class PricedHotel extends Hotel {

    /** Price per night for a room */
    public static final double ROOM_PRICE = 100.0;

    /** Price for the safe per stay */
    public static final double SAFE_PRICE = 10.0;

    /**
     * Creates a PricedHotel with the given name.
     * The first room (101) is a PricedRoom, the second (102) is a regular Room.
     *
     * @param name the hotel name
     */
    public PricedHotel(String name) {
        super(name);
        // Replace room1 with a PricedRoom
        this.room1 = new PricedRoom(101, ROOM_PRICE, SAFE_PRICE);
    }

    /**
     * Generates a bill for a guest.
     * Returns null if the guest is not found or not in a PricedRoom.
     *
     * @param guestName the name of the guest
     * @param numberOfNights the number of nights the guest stayed
     * @param printer the BillPrinter to use for formatting
     * @return a Bill object, or null if the guest is not in a PricedRoom
     */
    public Bill getBill(String guestName, int numberOfNights, BillPrinter printer) {
        Room room = getRoom(guestName);

        // Return null if guest not found or not in a PricedRoom
        if (room == null || !(room instanceof PricedRoom)) {
            return null;
        }

        PricedRoom pricedRoom = (PricedRoom) room;
        Bill bill = new Bill(printer);

        // Add an item for each night
        for (int i = 0; i < numberOfNights; i++) {
            bill.addItem(pricedRoom);
        }

        // Add safe cost if it's a PricedSafe and is active
        Safe safe = room.getSafe();
        if (safe instanceof PricedSafe && safe.isActive()) {
            bill.addItem((PricedSafe) safe);
        }

        bill.close();
        return bill;
    }
}