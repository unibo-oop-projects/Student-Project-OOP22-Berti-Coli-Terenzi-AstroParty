package it.unibo.AstroParty.model.Projectile.impl;

import it.unibo.AstroParty.common.Direction;
import it.unibo.AstroParty.common.Position;
import it.unibo.AstroParty.model.Projectile.api.ProjectileFactory;
import it.unibo.AstroParty.model.api.EntityType;
import it.unibo.AstroParty.model.api.Projectile;

public class ProjectileFactoryImpl implements ProjectileFactory {
	public Projectile createProjectile(Position pos, Direction dir) {
		return new ProjectileImpl(pos, dir, EntityType.PROJECTILE);
	}
}
