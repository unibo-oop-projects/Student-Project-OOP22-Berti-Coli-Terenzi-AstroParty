package it.unibo.AstroParty.model.api;

import it.unibo.AstroParty.common.Position;

/**
 * Interface modeling a hitbox of circular shape.
 */
public interface CircleHitBox extends HitBox {

    /**
     * @return the radius of the circle
     */
    double getRadius();

    /**
     * @return the circle center {@link Position}
     */
    Position getCenter();
}
