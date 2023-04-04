package it.unibo.astroparty.game.projectile.impl;

import it.unibo.astroparty.common.Direction;
import it.unibo.astroparty.common.Position;
import it.unibo.astroparty.game.api.EntityType;
import it.unibo.astroparty.game.projectile.api.Projectile;
import it.unibo.astroparty.game.projectile.api.ProjectileFactory;

/**
 * class that implements the ProjectileFactory interface with the following method
 * 
 * @author dario
 *
 */
public class ProjectileFactoryImpl implements ProjectileFactory {
    /**
     * {@inheritDoc}
     */
    public Projectile createProjectile(Position pos, Direction dir) {
        return new ProjectileImpl(pos, dir, EntityType.PROJECTILE, 0.3);
    }
}
