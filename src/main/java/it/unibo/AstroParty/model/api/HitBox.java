package it.unibo.AstroParty.model.api;

/**
 * Rapresent the boundaries of an {@link Entity}
 */
public interface HitBox {

    /**
     * used to get to know if the {@link Entity} modelled by this hitbox and another (circle-shaped) have collied
     * @param hBox the {@link HitBox} of the other {@link Entity}
     * @return true if the two entities have collied
     */
    public boolean checkCircleCollision(CircleHitBox hBox);     // at the moment we have to manage only collisions with circle-shaped hitbox
    
    /**
     * @return the height
     */
    public double getHeight();

    /**
     * @return the width
     */
    public double getWidth();

    
}
