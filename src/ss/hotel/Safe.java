package ss.hotel;

/**
 * A Safe that can be active/inactive and open/closed.
 */
public class Safe {

    private boolean active;
    private boolean open;

    /**
     * Creates a new Safe, initially inactive and closed.
     */
    public Safe() {
        this.active = false;
        this.open = false;
    }

    /** Activates the safe. */
    public void activate() {
        active = true;
    }

    /** Deactivates the safe and closes it. */
    public void deactivate() {
        open = false;
        active = false;
    }

    /** Opens the safe if it is active. */
    public void open() {
        if (active) open = true;
    }

    /** Closes the safe. */
    public void close() {
        open = false;
    }

    /** @return true if active, false otherwise */
    public boolean isActive() {
        return active;
    }

    /** @return true if open, false otherwise */
    public boolean isOpen() {
        return open;
    }
}
