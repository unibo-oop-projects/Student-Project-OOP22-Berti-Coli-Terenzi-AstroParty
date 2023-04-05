package it.unibo.astroparty.game.logics.api;

import it.unibo.astroparty.game.obstacle.api.Obstacle;
import it.unibo.astroparty.game.powerup.api.PowerUp;
import it.unibo.astroparty.game.projectile.api.Projectile;
import it.unibo.astroparty.game.spaceship.api.Spaceship;

/**
 * Factory interface from simple factory pattern that handles events creation.
 */
public interface EventFactory {

    /**
     * Create a new event for a spaceship collision.
     * @param spaceship that collied
     * @return the created event
     */
    Event spaceshipColliedEvent(Spaceship spaceship);

    /**
     * Creates a new event for a projectile collision.
     * @param projectile that hit
     * @return the created event
     */
    Event projectileHitEvent(Projectile projectile);

    /**
     * Create a new event for an obstacle hitted.
     * @param obstacle that was hit
     * @return the created event
     */
    Event obstacleHittedEvent(Obstacle obstacle);

    /**
     * Create a new event for a spaceship hitted.
     * @param spaceship that was hit
     * @return the created event
     */
    Event spaceshipHittedEvent(Spaceship spaceship);

    /**
     * Create a new event for a spaceship equipping a power-up.
     * @param powerUp that was equipped
     * @param spaceship that equipped the power-up
     * @return the created event
     */
    Event powerUpEquipEvent(PowerUp powerUp, Spaceship spaceship);
}
