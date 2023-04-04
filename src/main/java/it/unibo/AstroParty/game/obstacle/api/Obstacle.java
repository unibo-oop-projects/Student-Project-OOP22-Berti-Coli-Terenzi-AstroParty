package it.unibo.astroparty.game.obstacle.api;

import it.unibo.astroparty.game.api.Entity;
import it.unibo.astroparty.game.hitbox.api.RectangleHitBox;

/**
 * interface that models the entity obstacle.
 */
public interface Obstacle extends Entity {

    /**
     * the size of the Obstacle.
     */
    double SIZE = 6.0;

    /**
     * 
     * @return true if the obstacle is visible/hittable
     */
    boolean isActive();

    /**
     * 
     * @return true if the obstacle damages the spaceship
     */
    boolean isHarmful();

    /**
     * {@inheritDoc}
     */
    @Override
    RectangleHitBox getHitBox();

}
