package ss.hotel;

public class Room {
    private int number;
    private Guest guest;
    private Safe safe;

    /** Constructor with room number and a Safe. */
    public Room(int number, Safe safe) {
        this.number = number;
        this.safe = safe;
    }

    /** Constructor that creates a new Safe. */
    public Room(int number) {
        this(number, new Safe());
    }

    /**
     * Returns the number of this Room
     */
    public int getNumber() {
    	return number;
    }

    /**
     * Returns the current guest living in this Room
     * @return the guest of this Room, null if not rented
     */
    public Guest getGuest() {
    	return guest;
    }

    /** Returns the Safe in this room. */
    public Safe getSafe() {
        return safe;
    }

    /**
     * Assigns a Guest to this Room.
     * @param guest the new guest renting this Room, if null is given, Room is empty afterwards
     */
    public void setGuest(Guest guest) {
    	this.guest = guest;
    }

    @Override
    public String toString() {
        return "Room " + number;
    }
}
