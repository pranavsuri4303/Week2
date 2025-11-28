package ss.hotel;

import ss.hotel.bill.SysoutBillPrinter;

import java.util.Scanner;

/**
 * Textual User Interface (TUI) for interacting with a PricedHotel.
 * Extends the basic hotel TUI with billing functionality and
 * password-protected safe activation.
 */
public class PricedHotelTUI {

    /** command constants */
    private static final String IN = "in";
    private static final String OUT = "out";
    private static final String ROOM = "room";
    private static final String ACTIVATE = "activate";
    private static final String BILL = "bill";
    private static final String PRINT = "print";
    private static final String HELP = "help";
    private static final String EXIT = "exit";

    /** the priced hotel to operate on */
    private final PricedHotel hotel;

    /**
     * Creates a TUI for the given priced hotel.
     * @param hotel priced hotel to control
     */
    public PricedHotelTUI(PricedHotel hotel) {
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
            String param1 = (split.length > 1) ? split[1] : null;
            String param2 = (split.length > 2) ? split[2] : null;

            switch (cmd) {
                case IN -> doIn(param1);
                case OUT -> doOut(param1);
                case ROOM -> doRoom(param1);
                case ACTIVATE -> doActivate(param1, param2);
                case BILL -> doBill(param1, param2);
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
            System.out.println("Guest " + name + " is checked into room " + room.getNumber());
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
        } else {
            System.out.println("Guest " + name + " successfully checked out.");
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
    private void doActivate(String name, String password) {
        if (name == null) {
            System.out.println("No guest name provided.");
            return;
        }
        Room room = hotel.getRoom(name);
        if (room == null) {
            System.out.println("Guest " + name + " doesn't have a room.");
            return;
        }

        Safe safe = room.getSafe();

        // Check if it's a PricedSafe
        if (safe instanceof PricedSafe) {
            if (password == null || password.isEmpty()) {
                System.out.println("Wrong params at activation (password required)");
                return;
            }
            ((PricedSafe) safe).activate(password);
            if (safe.isActive()) {
                System.out.println("Safe in room " + room.getNumber() + " of guest " + name + " has been activated.");
            } else {
                System.out.println("Failed to activate safe (incorrect password).");
            }
        } else {
            // Regular safe - no password needed
            safe.activate();
            System.out.println("Safe of guest " + name + " is activated.");
        }
    }

    /** Bill command helper */
    private void doBill(String name, String nightsStr) {
        if (name == null) {
            System.out.println("No guest name provided.");
            return;
        }
        if (nightsStr == null) {
            System.out.println("No number of nights provided.");
            return;
        }

        try {
            int nights = Integer.parseInt(nightsStr);
            if (nights <= 0) {
                System.out.println("Number of nights must be positive.");
                return;
            }

            SysoutBillPrinter printer = new SysoutBillPrinter();
            var bill = hotel.getBill(name, nights, printer);

            if (bill == null) {
                System.out.println("Cannot generate bill for guest " + name + " (not in a priced room).");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number of nights: " + nightsStr);
        }
    }

    /** Prints the menu */
    private void printMenu() {
        System.out.println("Welcome to the Hotel booking system of the \"" + hotel.getName() + "\"");
        System.out.println("Commands:");
        System.out.println("in name ................. checkin guest with name");
        System.out.println("out name ................ checkout guest with name");
        System.out.println("room name ............... request room of guest");
        System.out.println("activate name password .. activate safe, password required for PricedSafe");
        System.out.println("bill name nights......... print bill for guest (name) and number of nights");
        System.out.println("help .................... help (this menu)");
        System.out.println("print ................... print state of the hotel");
        System.out.println("exit .................... exit");
    }

    /**
     * Main method: creates priced hotel + TUI and starts program.
     */
    public static void main(String[] args) {
        PricedHotel hotel = new PricedHotel("Hotel Twente");
        PricedHotelTUI tui = new PricedHotelTUI(hotel);
        tui.run();
    }
}