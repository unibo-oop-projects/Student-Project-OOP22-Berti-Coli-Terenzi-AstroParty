package it.unibo.AstroParty.model.api;

import it.unibo.AstroParty.common.Position;

public interface CircleHitBox extends HitBox {

    /**
     * 
     * @return the radius of the circle
     */
    public double getRadius();

    /**
     * 
     * @return the circle center {@link Position}
     */
    public Position getCenter();
}
