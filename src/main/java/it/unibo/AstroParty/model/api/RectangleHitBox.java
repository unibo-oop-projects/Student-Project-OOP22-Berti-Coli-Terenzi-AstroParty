package it.unibo.AstroParty.model.api;

import it.unibo.AstroParty.common.Position;

public interface RectangleHitBox extends HitBox {

    /**
     * 
     * @return the down-left corner position
     */
    public Position getDLCorner();

    /**
     * 
     * @return the up-right corner position
     */
    public Position getURCorner();
}
