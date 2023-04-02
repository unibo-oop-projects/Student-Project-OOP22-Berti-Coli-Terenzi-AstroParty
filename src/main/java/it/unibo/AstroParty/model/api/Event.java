package it.unibo.AstroParty.model.api;

/**
 * Interface that models a game event.
 */
public interface Event {

    /**
     * Called for manage the event occurred.
     * @param state
     */
    void manage(GameState state);

}
