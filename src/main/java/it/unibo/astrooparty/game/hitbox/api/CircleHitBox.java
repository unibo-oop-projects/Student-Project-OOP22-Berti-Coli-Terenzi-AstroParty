package it.unibo.astroparty.game.hitbox.api;

import it.unibo.astroparty.common.Position;

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
