package it.unibo.AstroParty.model.impl;

import it.unibo.AstroParty.common.Position;
import it.unibo.AstroParty.model.api.RectangleHitBox;

public class RectangleHitBoxImpl implements RectangleHitBox {

    private Position DLCorner, URCorner;

    /**
     * 
     * @param DLCorner the down-left corner position
     * @param URCorner the up-right corner position
     */
    public RectangleHitBoxImpl(Position DLCorner, Position URCorner) {
        this.DLCorner = DLCorner;
        this.URCorner = URCorner;
    }

    /**
     * 
     * @param DLCorner the down-left corner position
     * @param width the rectangle width
     * @param height the rectangle height
     */
    public RectangleHitBoxImpl(Position DLCorner, double width, double height) {
        this.DLCorner = DLCorner;
        URCorner = new Position(DLCorner.getX()+width, DLCorner.getY()+height);
    }

    @Override
    public boolean isHitted(Position pos, double radius) {
        return pos.getDistanceFrom(getPerimeterPosition(pos)) < radius;
    }

    /**
     * this method is used to have the position of the perimeter point 
     * closest to the position given in input
     * @param pos the position of the external entity
     * @return the nearest point on the perimeter
     */
    private Position getPerimeterPosition(Position pos) {
        double x,y;
        if (pos.getX() < DLCorner.getX()) {
            x = DLCorner.getX();
        } else if (pos.getX() > URCorner.getX()) {
            x = URCorner.getX();
        } else {
            x = pos.getX();
        }

        if (pos.getY() < DLCorner.getY()) {
            y = DLCorner.getY();
        } else if (pos.getY() > URCorner.getY()) {
            y = URCorner.getY();
        } else {
            y = pos.getY();
        }

        return new Position(x, y);
    }

    @Override
    public Position getDLCorner() {
        return DLCorner;
    }

    @Override
    public Position getURCorner() {
        return URCorner;
    }
    
}
