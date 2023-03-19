package it.unibo.AstroParty.model.api;

import java.util.Optional;

public interface EventFactory {

    /**
     * 
     * @param projectile that hit something
     * @param target of the projectile
     * @return a hitEvent
     */
    public Event hitEvent(Projectile projectile, Optional<Entity> target);

    /**
     * 
     * @param spaceship that have collied
     * @param target of the spaceship
     * @return colliedEvent
     */
    public Event colliedEvent(Spaceship spaceship, Optional<Entity> target);
}
