package it.unibo.AstroParty.model.impl;

import it.unibo.AstroParty.model.api.Event;
import it.unibo.AstroParty.model.api.EventFactory;
import it.unibo.AstroParty.model.api.Obstacle;
import it.unibo.AstroParty.model.api.PowerUp;
import it.unibo.AstroParty.model.api.Projectile;
import it.unibo.AstroParty.model.api.Spaceship;

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
