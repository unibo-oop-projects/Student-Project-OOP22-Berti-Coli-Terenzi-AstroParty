package it.unibo.AstroParty.model.Projectile.api;

import it.unibo.AstroParty.common.Direction;
import it.unibo.AstroParty.common.Position;
import it.unibo.AstroParty.model.api.Projectile;

public interface ProjectileFactory {
	public Projectile createProjectile(Position pos, Direction dir);
}
