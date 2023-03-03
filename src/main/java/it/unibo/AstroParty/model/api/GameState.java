package it.unibo.AstroParty.model.api;

import java.util.Collection;

public interface GameState {
	public static final double height = 10.0 ;
	public static final double length = 20.0 ;
    public Collection<Entity> getWorld();
    public void update(double time);
    public boolean isOver();
    public void addSpaceship(Spaceship s);
    public void addObstacle(Obstacle o);
    public void addPowerUp(PowerUp pUp);
    public void addProjectile(Projectile p);
}
