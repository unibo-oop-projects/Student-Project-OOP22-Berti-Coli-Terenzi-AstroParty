package it.unibo.astroparty.game.projectile.api;

import it.unibo.astroparty.game.api.Entity;
import it.unibo.astroparty.game.hitbox.api.CircleHitBox;

/**
 * interface for projectiles with the following methods
 * 
 * @author dario
 *
 */
public interface Projectile extends Entity {
    static final double radius = 1.3;
    
    /**
     * {@inheritDoc}}
     */
    @Override
    public CircleHitBox getHitBox();
    
}
