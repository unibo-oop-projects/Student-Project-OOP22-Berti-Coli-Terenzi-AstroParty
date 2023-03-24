package it.unibo.AstroParty.model.api;

/**
 * Subject interface from observer pattern
 */
public interface Observable {

    /**
     * @param observer to be added
     */
    public void registerObserver(Observer observer);

    /**
     * @param observer to be removed
     */
    public void unregisterObserver(Observer observer);

    /**
     * tells to all the observers that an event has occurred
     * @param event that has occurred
     */
    public void notifyObservers(Event event);
    
}
