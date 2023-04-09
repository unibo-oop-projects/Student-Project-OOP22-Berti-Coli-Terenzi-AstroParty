package it.unibo.astroparty.core.api;

import java.util.List;

/**
 * interface of GameEngine that has the following methods.
 * @author dario
 *
 */
public interface GameEngine {
    /**
     * saves all the necessary information.
     * @param players
     * @param obstacle
     * @param powerup
     * @param rounds
     */
    void init(List<String> players, boolean obstacle, boolean powerup, int rounds);

    /**
     * creates every round with its map.
     */
    void mainLoop();
}
