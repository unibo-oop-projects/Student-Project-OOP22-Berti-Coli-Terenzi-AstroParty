package it.unibo.AstroParty.model.api;

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
