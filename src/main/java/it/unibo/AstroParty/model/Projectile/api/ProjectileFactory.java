package it.unibo.AstroParty.model.Projectile.api;

import it.unibo.AstroParty.common.Direction;
import it.unibo.AstroParty.common.Position;
import it.unibo.AstroParty.model.api.Projectile;

/**
 * interface for the Factory of the Projectiles with the following method
 * 
 * @author dario
 *
 */
public interface ProjectileFactory {
    /**
     * creates the Projectile given the position and direction of the Spaceship
     * 
     * @param pos the {@link Position} of the Spaceship
     * @param dir the {@link Direction} of the SpaceShip
     * @return
     */
    public Projectile createProjectile(Position pos, Direction dir);
}
