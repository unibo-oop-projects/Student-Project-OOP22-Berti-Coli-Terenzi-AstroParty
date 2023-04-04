package it.unibo.astroparty.core.api;

/**
 * interface of GameEngine that has the following methods
 * @author dario
 *
 */
public interface GameEngine {
    /**
     * setups the whole game
     */
    public void init();
    
    /**
     * it is the gameLoop that handles real time changes
     */
    public void mainLoop();
}
