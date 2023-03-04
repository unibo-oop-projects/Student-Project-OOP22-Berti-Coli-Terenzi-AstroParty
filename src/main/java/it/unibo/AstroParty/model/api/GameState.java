package it.unibo.AstroParty.model.api;

import java.util.Collection;

public interface GameState {

	public static final double height = 10.0 ;
	public static final double length = 20.0 ;

    /**
     * 
     * @return a collection of all the entities in the game
     */
    public Collection<Entity> getEntities();

    /**
     * this method update the position of all the entities in the map
     * and manage their interactions
     * @param time the time elapsed from the past update
     */
    public void update(double time);

    /**
     * 
     * @return true if the game is over
     */
    public boolean isOver();

    public void addSpaceship(Spaceship s);

    public void addObstacle(Obstacle o);

    public void addPowerUp(PowerUp pUp);
    
    public void addProjectile(Projectile p);

    /**
     * 
     * @return the height of the world map
     */
    public double getWorldHeight();

    /**
     * 
     * @return the width of the world map
     */
    public double getWorldWidth();
}
