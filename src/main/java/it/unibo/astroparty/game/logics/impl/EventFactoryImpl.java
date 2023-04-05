package it.unibo.astroparty.game.logics.impl;

import it.unibo.astroparty.game.logics.api.Event;
import it.unibo.astroparty.game.logics.api.EventFactory;
import it.unibo.astroparty.game.obstacle.api.Obstacle;
import it.unibo.astroparty.game.powerup.api.PowerUp;
import it.unibo.astroparty.game.projectile.api.Projectile;
import it.unibo.astroparty.game.spaceship.api.Spaceship;

/**
 * EventFactory implementation.
 */
public class EventFactoryImpl implements EventFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public Event spaceshipColliedEvent(final Spaceship spaceship) {
        return state -> spaceship.resetPosition();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Event projectileHitEvent(final Projectile projectile) {
        return state -> state.removeProjectile(projectile);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Event obstacleHittedEvent(final Obstacle obstacle) {
        return state -> {
            if (obstacle.hit()) {
                state.removeObstacle(obstacle);
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Event spaceshipHittedEvent(final Spaceship spaceship) {
        return state -> {
            if (spaceship.hit()) {
                state.removeSpaceship(spaceship);
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Event powerUpEquipEvent(final PowerUp powerUp, final Spaceship spaceship) {
        return state -> {
            if (spaceship.equipPowerUp(powerUp)) {
                state.removePowerUp(powerUp);
            }
        };
    } 
}
