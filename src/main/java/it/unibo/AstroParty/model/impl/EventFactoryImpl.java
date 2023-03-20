package it.unibo.AstroParty.model.impl;

import it.unibo.AstroParty.model.api.Event;
import it.unibo.AstroParty.model.api.EventFactory;
import it.unibo.AstroParty.model.api.Obstacle;
import it.unibo.AstroParty.model.api.PowerUp;
import it.unibo.AstroParty.model.api.Projectile;
import it.unibo.AstroParty.model.api.Spaceship;

/**
 * implementation of {@link EventFactory}
 */
public class EventFactoryImpl implements EventFactory {

    @Override
    public Event SpaceshipColliedEvent(Spaceship spaceship) {
        return state -> spaceship.resetPosition();
    }

    @Override
    public Event projectileHitEvent(Projectile projectile) {
        return state -> state.removeProjectile(projectile);
    }

    @Override
    public Event obstacleHittedEvent(Obstacle obstacle) {
        return state -> {
            if (obstacle.hit()) {
                state.removeObstacle(obstacle);
            }
        };
    }

    @Override
    public Event spaceshipHittedEvent(Spaceship spaceship) {
        return state -> {
            if (spaceship.hit()) {
                state.removeSpaceship(spaceship);
            }
        };
    }

    @Override
    public Event powerUpEquipEvent(PowerUp powerUp, Spaceship spaceship) {
        return state -> {
            powerUp.pickUp(spaceship);
            spaceship.equipPowerUp(powerUp);
        };
    }

    
}
