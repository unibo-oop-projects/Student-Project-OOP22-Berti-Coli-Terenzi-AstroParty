package it.unibo.AstroParty.model.api;

/**
 * enum that manages the type of entities in the game
 */
public enum EntityType {

    /**
     * player spaceship type
     */
    SPACESHIP,
    
    /**
     * pickable object type
     */
    PICKABLE,
    
    /**
     * solid object type (moving entities like {@link Spaceship} and {@link Projectile} can't go through)
     * can be harming or not depending on the implementation
     */
    OBSTACLE,
    
    /**
     * projectile type
     */
    PROJECTILE;
    
}
