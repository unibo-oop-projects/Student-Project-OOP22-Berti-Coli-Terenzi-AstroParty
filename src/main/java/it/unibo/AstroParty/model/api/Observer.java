package it.unibo.AstroParty.model.api;

/**
 * Observer interface from Observer pattern
 */
public interface Observer {

    /**
     * notify the observer of the new event
     * @param event has occurred
     */
    public void notify(Event event);
}
