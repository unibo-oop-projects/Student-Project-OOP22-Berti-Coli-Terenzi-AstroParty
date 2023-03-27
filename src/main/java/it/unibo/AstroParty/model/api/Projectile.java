package it.unibo.AstroParty.model.api;

import it.unibo.AstroParty.common.Direction;
import it.unibo.AstroParty.common.Position;

public interface Projectile extends Entity {
	static final double radius = 1;
    
    @Override
    public CircleHitBox getHitBox();
    
}
