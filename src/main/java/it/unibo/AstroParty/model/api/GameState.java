package it.unibo.AstroParty.model.api;

import java.util.Collection;

public interface GameState {

    /* the logic dimensions of the map are always the same, the effective sizes
     * are elaborated by the graphical part using the window sizes
    */
	public static final double height = 20.0 ;
	public static final double width = 20.0 ;

    /*
     * the coordinates of the sides of the world map
     */
    public static final double lowerSide = 0;
    public static final double leftSide = 0;
    public static final double upperSide = lowerSide + height;
    public static final double rightSide = leftSide + width;


    /**
     * 
     * @return a collection of all the entities in the game
     */
    public Collection<Entity> getEntities();

    /**
     * this method update the position and status of all the entities in the map and manage their interactions
     * @param time the time elapsed from the last update
     */
    public void update(double time);

    /**
     * 
     * @return true if the game is over
     */
    public boolean isOver();

    /**
     * adds a new entity to the world
     * @param entity to be added
     */
    public void addEntity(Entity entity);

    /**
     * removes an entity from the world 
     * @param entity to be removed
     */
    public void removeEntity(Entity entity);

}
