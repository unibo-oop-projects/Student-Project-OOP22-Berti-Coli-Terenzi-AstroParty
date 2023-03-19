package it.unibo.AstroParty.model.impl;

import java.util.Optional;

import it.unibo.AstroParty.model.api.Entity;
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Event hitEvent(Projectile projectile, Optional<Entity> target) {
        return state -> {
            state.removeEntity(projectile);
            if (target.isPresent() && target.get().hit()) {
                state.removeEntity(target.get());
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Event colliedEvent(Spaceship spaceship, Optional<Entity> target) {
        if (target.isPresent()) {   // if the target isn't present means that the spaceship have collied with world boundarie
            final Entity entity = target.get();

            if (entity instanceof PowerUp) {
                return pickupEvent(spaceship, (PowerUp) entity);
            } else if (entity instanceof Obstacle) {
                return obstacleCollisionEvent(spaceship, (Obstacle) entity);
            }
        }
        return state -> spaceship.resetPosition();
    }


    private Event pickupEvent(Spaceship spaceship, PowerUp pickable) {
        return state -> {
            state.removeEntity(pickable);
            spaceship.equipPowerUp(pickable);
        };
    }

    private Event obstacleCollisionEvent(Spaceship spaceship, Obstacle obstacle) {
        return state -> {
            spaceship.resetPosition();
            if (obstacle.isHarmful()) {
                state.removeEntity(obstacle);
            }
        };
    }
    
}
