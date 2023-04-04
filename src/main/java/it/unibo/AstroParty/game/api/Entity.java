package it.unibo.astroparty.game.api;

import it.unibo.astroparty.common.Position;
import it.unibo.astroparty.game.hitbox.api.HitBox;
import it.unibo.astroparty.game.obstacle.api.Obstacle;
import it.unibo.astroparty.game.powerUp.api.PowerUp;
import it.unibo.astroparty.game.projectile.api.Projectile;
import it.unibo.astroparty.game.spaceship.api.Spaceship;
import it.unibo.astroparty.graphics.api.GraphicEntity;

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