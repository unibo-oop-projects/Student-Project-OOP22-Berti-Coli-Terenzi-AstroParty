package it.unibo.astroparty.game.api;

/**
 * Subject interface from observer pattern.
 */
public interface Observable {

    /**
     * @param observer to be added
     */
    void registerObserver(Observer observer);

    /**
     * @param observer to be removed
     */
    void unregisterObserver(Observer observer);

    /**
     * Tells to all the observers that an event has occurred.
     * @param event that has occurred
     */
    void notifyObservers(Event event);
}
