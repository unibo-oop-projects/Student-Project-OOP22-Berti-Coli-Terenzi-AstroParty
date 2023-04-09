package it.unibo.astroparty.game.projectile.impl;

import it.unibo.astroparty.common.Direction;
import it.unibo.astroparty.common.Position;
import it.unibo.astroparty.game.EntityType;
import it.unibo.astroparty.game.projectile.api.Projectile;
import it.unibo.astroparty.game.projectile.api.ProjectileFactory;

/**
 * class that implements the ProjectileFactory interface with the following method.
 * 
 * @author dario
 *
 */
public class ProjectileFactoryImpl implements ProjectileFactory {
    private static final double P_SPEED = 0.3;
    /**
     * {@inheritDoc}
     */
    @Override
    public Projectile createProjectile(final Position pos, final Direction dir) {
        return new ProjectileImpl(pos, dir, EntityType.PROJECTILE, P_SPEED);
    }
}
