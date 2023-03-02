package it.unibo.AstroParty.model.api;

import java.util.Collection;

public interface GameState {
    public Collection<Entity> getWorld();
    public void update(double time);
    public boolean isOver();
    public void addSpaceship(Spaceship s);
    public void addObstacle(Obstacle o);
    public void addPowerUp(PowerUp pUp);
    public void addProjectile(Projectile p);
}
