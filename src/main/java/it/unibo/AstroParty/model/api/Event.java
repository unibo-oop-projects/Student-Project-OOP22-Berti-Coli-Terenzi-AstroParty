package it.unibo.AstroParty.model.api;

/**
 * simple event interface
 */
public interface Event {
    
    /**
     * this method's called for manage the event occurred
     * @param state the GameState
     */
    public void manage(GameState state);
}
