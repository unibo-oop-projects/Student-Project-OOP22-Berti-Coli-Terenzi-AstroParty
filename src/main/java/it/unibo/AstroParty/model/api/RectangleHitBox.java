package it.unibo.AstroParty.model.api;

import it.unibo.AstroParty.common.Position;

public interface RectangleHitBox extends HitBox {

    /**
     * 
     * @return the down-left corner {@link Position}
     */
    public Position getDLCorner();

    /**
     * 
     * @return the up-right corner {@link Position}
     */
    public Position getURCorner();
}
