package ss.hotel;

import java.util.Scanner;

/**
 * Textual User Interface (TUI) for interacting with a Hotel.
 * Supports:
 * <ul>
 *     <li>check in guests</li>
 *     <li>check out guests</li>
 *     <li>request rooms of guests</li>
 *     <li>activate safes</li>
 *     <li>print hotel state</li>
 * </ul>
 */
public class HotelTUI {

    /** command constants */
    private static final String IN = "in";
    private static final String OUT = "out";
    private static final String ROOM = "room";
    private static final String ACTIVATE = "activate";
    private static final String PRINT = "print";
    private static final String HELP = "help";
    private static final String EXIT = "exit";

    /** the hotel to operate on */
    private Hotel hotel;

    /**
     * Creates a TUI for the given hotel.
     * @param hotel hotel to control
     */
    public HotelTUI(Hotel hotel) {
        this.hotel = hotel;
    }

    /**
     * Runs the main interactive loop.
     */
    public void run() {
        printMenu();
        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.print("Command: ");
            String line = sc.nextLine();
            String[] split = line.split("\\s+");

            if (split.length == 0) continue;

            String cmd = split[0];
            String param = (split.length > 1) ? split[1] : null;

            switch (cmd) {
                case IN -> doIn(param);
                case OUT -> doOut(param);
                case ROOM -> doRoom(param);
                case ACTIVATE -> doActivate(param);
                case PRINT -> System.out.println(hotel);
                case HELP -> printMenu();
                case EXIT -> exit = true;
                default -> {
                    System.out.println("Unknown command.");
                    printMenu();
                }
            }
        }
    }

    /** Check-in command helper */
    private void doIn(String name) {
        if (name == null) {
            System.out.println("No guest name provided.");
            return;
        }
        Room room = hotel.checkIn(name);
        if (room == null) {
            System.out.println("Check-in failed (hotel full or guest already exists).");
        } else {
            System.out.println("Guest " + name + " gets room " + room.getNumber());
        }
    }

    /** Check-out command helper */
    private void doOut(String name) {
        if (name == null) {
            System.out.println("No guest name provided.");
            return;
        }
        Room room = hotel.getRoom(name);
        hotel.checkOut(name);
        if (room == null) {
            System.out.println("Guest " + name + " not found.");
        }
    }

    /** Room lookup command helper */
    private void doRoom(String name) {
        if (name == null) {
            System.out.println("No guest name provided.");
            return;
        }
        Room room = hotel.getRoom(name);
        if (room == null) {
            System.out.println("Guest " + name + " doesn't have a room.");
        } else {
            System.out.println("Guest " + name + " has room " + room.getNumber());
        }
    }

    /** Activate safe command helper */
    private void doActivate(String name) {
        if (name == null) {
            System.out.println("No guest name provided.");
            return;
        }
        Room room = hotel.getRoom(name);
        if (room == null) {
            System.out.println("Guest " + name + " doesn't have a room.");
        } else {
            room.getSafe().activate();
            System.out.println("Safe of guest " + name + " is activated.");
        }
    }

    /** Prints the menu */
    private void printMenu() {
        System.out.println("Welcome to the Hotel management system of the \"" + hotel.getName() + "\"");
        System.out.println("Commands:");
        System.out.println("in name ................. check in guest with name");
        System.out.println("out name ................ check out guest with name");
        System.out.println("room name ............... request room of guest with name");
        System.out.println("activate name ........... activate safe of guest with name");
        System.out.println("help .................... help (this menu)");
        System.out.println("print ................... print state");
        System.out.println("exit .................... exit");
    }

    /**
     * Main method: creates hotel + TUI and starts program.
     */
    public static void main(String[] args) {
        Hotel hotel = new Hotel("Hotel Twente");
        HotelTUI tui = new HotelTUI(hotel);
        tui.run();
    }
}
