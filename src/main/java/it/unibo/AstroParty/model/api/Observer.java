package it.unibo.AstroParty.model.api;

/**
 * Observer interface from Observer pattern.
 */
public interface Observer {

    /**
     * Notify the observer of the new event.
     * @param event has occurred
     */
    void notify(Event event);
}
