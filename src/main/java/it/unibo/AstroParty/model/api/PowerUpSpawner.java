package it.unibo.AstroParty.model.api;

/** 
 * 
 * @author Alessandro Coli
 *
 * a Spawner of {@link PowerUp} inside an AstroParty game
 */
public interface PowerUpSpawner {
	
	/**
	 * the game has ended so stop spawning.
	 */
    void stop();
    
    /**
     *  start spawning, and put the new Power ups inside the correct {@link GameState}.
     * @param world
     */
    void start(GameState world);
}
