package it.unibo.AstroParty.model.Projectile.impl;

import it.unibo.AstroParty.common.Direction;
import it.unibo.AstroParty.common.Position;
import it.unibo.AstroParty.model.Projectile.api.ProjectileFactory;
import it.unibo.AstroParty.model.api.EntityType;
import it.unibo.AstroParty.model.api.Projectile;

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
