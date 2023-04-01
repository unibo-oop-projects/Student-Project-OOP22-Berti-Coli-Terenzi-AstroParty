package it.unibo.AstroParty.model.api;

/** 
 * 
 * @author Alessandro Coli
 *
 * a Spawner of {@link PowerUp} inside an AstroParty game
 */
public interface PowerUpSpawner {
	
	/**
	 * the game has ended so stop spawning
	 */
    public void stop();
    
    /**
     *  start spawning, and put the new Power ups inside the correct {@link GameState}
     * @param world
     */
    public void start(GameState world);
}
