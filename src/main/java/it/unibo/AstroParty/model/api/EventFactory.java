package it.unibo.AstroParty.model.api;

/**
 * Factory interface from Factory method
 */
public interface EventFactory {

    public Event SpaceshipColliedEvent(Spaceship spaceship);

    public Event projectileHitEvent(Projectile projectile);

    public Event obstacleHittedEvent(Obstacle obstacle);

    public Event spaceshipHittedEvent(Spaceship spaceship);

    public Event powerUpEquipEvent(PowerUp powerUp, Spaceship spaceship);
}
