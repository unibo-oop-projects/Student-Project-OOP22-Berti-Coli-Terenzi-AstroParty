package it.unibo.astroparty.core.api;

import java.util.List;

/**
 * interface of GameEngine that has the following methods
 * @author dario
 *
 */
public interface GameEngine {
    /**
     * setups the whole game
     */
    public void init(List<String> players, boolean obstacle, boolean powerup, int rounds);
    
    /**
     * it is the gameLoop that handles real time changes
     */
    public void mainLoop();
}
