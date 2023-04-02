package it.unibo.AstroParty.model.api;

import it.unibo.AstroParty.common.Position;

/**
 * Interface modeling a rectangular hitbox.
 */
public interface RectangleHitBox extends HitBox {

    /**
     * @return the up-left corner {@link Position}
     */
    Position getULCorner();

    /**
     * @return the down-right corner {@link Position}
     */
    Position getDRCorner();
}
