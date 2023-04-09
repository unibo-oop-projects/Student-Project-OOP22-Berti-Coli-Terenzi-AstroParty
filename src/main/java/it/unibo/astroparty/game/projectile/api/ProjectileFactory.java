package it.unibo.astroparty.game.projectile.api;

import it.unibo.astroparty.common.Direction;
import it.unibo.astroparty.common.Position;

/**
 * interface for the Factory of the Projectiles with the following method.
 * @author dario
 *
 */
public interface ProjectileFactory {
    /**
     * creates the Projectile given the position and direction of the Spaceship.
     * 
     * @param pos the {@link Position} of the Spaceship
     * @param dir the {@link Direction} of the SpaceShip
     * @return Projectile the created projectile
     */
    Projectile createProjectile(Position pos, Direction dir);
}
