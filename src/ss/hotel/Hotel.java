package ss.hotel;

/**
 * A simple hotel with two rooms and guest management.
 * Guests can check in by name, check out, and hotels
 * keep track of rooms, guests, and safes.
 */
public class Hotel {

    /** name of the hotel */
    private final String name;

    /** the rooms in the hotel (fixed size: 2 rooms) */
    private Room room1;
    private Room room2;

    /**
     * Creates a Hotel with the given name and two rooms.
     * Rooms are numbered 101 and 102.
     *
     * @param name the hotel name
     */
    public Hotel(String name) {
        this.name = name;
        this.room1 = new Room(101);
        this.room2 = new Room(102);
    }

    /**
     * Returns the name of the hotel.
     *
     * @return the name of the hotel
     */
    public String getName() {
        return name;
    }

    /**
     * Checks in a guest with the given name.
     * The method:
     * <ul>
     *   <li>returns null if a guest with this name is already checked in</li>
     *   <li>returns null if the hotel is full</li>
     *   <li>otherwise creates a new Guest, checks them into a free room,
     *       and returns that Room</li>
     * </ul>
     *
     * @param name name of the guest
     * @return Room into which the guest was checked in, or null if not possible
     */
    public Room checkIn(String name) {
        // guest already exists?
        if (getRoom(name) != null) {
            return null;
        }

        Room free = getFreeRoom();
        if (free == null) {
            return null;
        }

        Guest g = new Guest(name);
        boolean ok = g.checkin(free);
        return ok ? free : null;
    }

    /**
     * Checks out the guest with the given name.
     * If the guest exists:
     * <ul>
     *     <li>The guest is checked out</li>
     *     <li>The room's safe is deactivated</li>
     * </ul>
     * If the guest does not exist, nothing happens.
     *
     * @param name name of the guest
     */
    public void checkOut(String name) {
        Room room = getRoom(name);
        if (room == null) return;

        Guest g = room.getGuest();
        if (g != null) {
            g.checkout();
        }

        room.getSafe().deactivate();
    }

    /**
     * Returns a free Room, i.e., a room with no guest,
     * or null if no free rooms exist.
     *
     * @return free Room or null
     */
    public Room getFreeRoom() {
        if (room1.getGuest() == null) return room1;
        if (room2.getGuest() == null) return room2;
        return null;
    }

    /**
     * Returns the room in which the guest with the given name stays,
     * or null if no such guest is checked in.
     *
     * @param name name of the guest
     * @return the Room of the guest or null
     */
    public Room getRoom(String name) {
        if (room1.getGuest() != null &&
                room1.getGuest().getName().equals(name)) {
            return room1;
        }
        if (room2.getGuest() != null &&
                room2.getGuest().getName().equals(name)) {
            return room2;
        }
        return null;
    }

    /**
     * Returns a textual description of the hotel and its rooms,
     * including guest names and safe status.
     *
     * @return description string
     */
    @Override
    public String toString() {
        return "Hotel " + name + ":\n" +
                "Room " + room1.getNumber() + ":\n" +
                "  rented by: " + room1.getGuest() + "\n" +
                "  safe active: " + room1.getSafe().isActive() + "\n" +
                "Room " + room2.getNumber() + ":\n" +
                "  rented by: " + room2.getGuest() + "\n" +
                "  safe active: " + room2.getSafe().isActive();
    }
}
