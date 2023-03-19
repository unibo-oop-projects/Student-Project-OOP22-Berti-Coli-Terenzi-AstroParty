package it.unibo.AstroParty.model.api;

public interface Event {
    
    /**
     * this method's called for manage the event occurred
     * @param state the GameState
     */
    public void manage(GameState state);
}
