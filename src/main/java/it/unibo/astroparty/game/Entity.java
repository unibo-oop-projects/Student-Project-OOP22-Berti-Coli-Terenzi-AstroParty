package it.unibo.astroparty.game;

import it.unibo.astroparty.common.Position;
import it.unibo.astroparty.game.hitbox.api.HitBox;
import it.unibo.astroparty.game.obstacle.api.Obstacle;
import it.unibo.astroparty.game.powerup.api.PowerUp;
import it.unibo.astroparty.game.projectile.api.Projectile;
import it.unibo.astroparty.game.spaceship.api.Spaceship;
import it.unibo.astroparty.graphics.api.GraphicEntity;

/**
 * 
 * an interface rappresenting most of the objects in the game, such as 
 * {@link Spaceship}, {@link Obstacle}, {@link PowerUp} and {@link Projectile}.
 *
 */
public interface Entity {
    /**
     * @return the {@link HitBox} of the entity
     */
    HitBox getHitBox();

    /**
     * @return the {@link Position} of the entity
     */
    Position getPosition();

    /**
     * @return true if is killed/destroyed
     */
    boolean hit();

    /**
     * tells the entity how much time has passed since the last update.
     * @param time in milliseconds
     */
    void update(double time);

    /**
     * @return the {@link PowerUpTypes} of this Entity
     */
    EntityType getType();

    /**
     * 
     * @return the {@link GraphicEntity} of this Entity
     */
    GraphicEntity getGraphicComponent();
}
