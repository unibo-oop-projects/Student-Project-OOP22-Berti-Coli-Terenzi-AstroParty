package it.unibo.AstroParty.model.api;

import it.unibo.AstroParty.common.Direction;
import it.unibo.AstroParty.common.Position;

/**
 * interface for projectiles with the following methods
 * 
 * @author dario
 *
 */
public interface Projectile extends Entity {
	static final double radius = 1;
    
	/**
	 * {@inheritDoc}}
	 */
    @Override
    public CircleHitBox getHitBox();
    
}
