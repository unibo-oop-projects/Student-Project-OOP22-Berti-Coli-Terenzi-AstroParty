package it.unibo.AstroParty.model.api;

import it.unibo.AstroParty.common.Position;

public interface RectangleHitBox extends HitBox {

    /**
     * 
     * @return the up-left corner {@link Position}
     */
    public Position getULCorner();

    /**
     * 
     * @return the down-right corner {@link Position}
     */
    public Position getDRCorner();
}
