package ss.hotel;

/**
 * Hotel guest with a name and possibly a hotel room.
 * A Guest may rent at most one Room at a time.
 */
public class Guest {

    /** the name of this Guest */
    private final String name;

    /** the Room currently rented by this Guest; null if none */
    private Room room;

    /**
     * Creates a Guest with the given name and without Room.
     *
     * @param name name of the new Guest
     */
    public Guest(String name) {
        this.name = name;
        this.room = null;
    }

    /**
     * Returns the name of this Guest.
     *
     * @return name of this Guest
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the Room rented by this Guest.
     *
     * @return Room rented by this Guest,
     *         or {@code null} if this Guest does not rent a Room
     */
    public Room getRoom() {
        return room;
    }

    /**
     * Rents a Room to this Guest.
     * This is only possible if:
     * <ul>
     *     <li>this Guest does not already have a Room</li>
     *     <li>the given Room is not already rented</li>
     * </ul>
     * If the checkin succeeds, both sides of the association are updated:
     * <ul>
     *     <li>this Guest gets the Room</li>
     *     <li>the Room gets this Guest</li>
     * </ul>
     *
     * @param room Room to be rented to this Guest
     * @return {@code true} if the checkin succeeded,
     *         {@code false} if this Guest already had a Room
     *         or the Room already had a Guest
     */
    public boolean checkin(Room room) {
        if (this.room != null) {
            return false; // guest already has a room
        }
        if (room.getGuest() != null) {
            return false; // room already occupied
        }

        this.room = room;
        room.setGuest(this);
        return true;
    }

    /**
     * Checks this Guest out of the Room.
     * The Guest will no longer rent a Room,
     * and the Room will no longer refer to this Guest.
     *
     * @return {@code true} if the checkout succeeded,
     *         {@code false} if this Guest did not rent a Room
     */
    public boolean checkout() {
        if (this.room == null) {
            return false; // no room to check out from
        }

        Room temp = this.room;
        this.room = null;
        temp.setGuest(null);
        return true;
    }

    /**
     * Returns the name of this Guest.
     *
     * @return the name of this Guest
     */
    @Override
    public String toString() {
        return name;
    }
}
