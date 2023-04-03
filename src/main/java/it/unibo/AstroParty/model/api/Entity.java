package it.unibo.AstroParty.model.api;

import it.unibo.AstroParty.common.Position;
import it.unibo.AstroParty.graphics.api.GraphicEntity;

/**
 * 
 * an interface rappresenting most of the objects in the game, such as {@link Spaceship}, {@link Obstacle}, {@link PowerUp} and {@link Projectile}
 *
 */
public interface Entity {
    /**
     * @return the {@link HitBox} of the entity
     */
    public HitBox getHitBox();
    
    /**
     * @return the {@link Position} of the entity
     */
    public Position getPosition();
    
    /**
     * @return true if is killed/destroyed
     */
    public boolean hit();
    
    /**
     * tells the entity how much time has passed since the last update
     * @param time in milliseconds
     */
    public void update(double time);
    
    /**
     * @return the {@link PowerUpTypes} of this Entity
     */
    public EntityType getType();
    
    /**
     * 
     * @return the {@link GraphicEntity} of this Entity
     */
    public GraphicEntity getGraphicComponent() ;
}