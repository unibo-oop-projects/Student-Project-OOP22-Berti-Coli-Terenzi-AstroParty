package it.unibo.AstroParty.model.impl;

import it.unibo.AstroParty.common.Position;
import it.unibo.AstroParty.model.api.CircleHitBox;
import it.unibo.AstroParty.model.api.HitBox;
import it.unibo.AstroParty.model.api.RectangleHitBox;

public class RectangleHitBoxImpl implements RectangleHitBox {

    private Position DLCorner, URCorner;

    /**
     * 
     * @param DLCorner the down-left corner {@link Position}
     * @param URCorner the up-right corner {@link Position}
     */
    public RectangleHitBoxImpl(Position DLCorner, Position URCorner) {
        this.DLCorner = DLCorner;
        this.URCorner = URCorner;
    }

    /**
     * 
     * @param DLCorner the down-left corner {@link Position}
     * @param width the rectangle width
     * @param height the rectangle height
     */
    public RectangleHitBoxImpl(Position DLCorner, double width, double height) {
        this.DLCorner = DLCorner;
        URCorner = new Position(DLCorner.getX()+width, DLCorner.getY()+height);
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public boolean isHittedBy(HitBox hBox) throws IllegalArgumentException {
        if (hBox instanceof CircleHitBox) {     // we currently have only circle entities moving, this is for future updates
            Position center = ((CircleHitBox) hBox).getCenter();
            return center.getDistanceFrom(clampOnRectangle(center)) < ((CircleHitBox) hBox).getRadius();
        }
        throw new IllegalArgumentException();
    }

    // method found in "2D Game Collision Detection" by Thomas Schwarzl
    private Position clampOnRectangle(Position pos) {
        double x,y;
        x = clampOnRange(pos.getX(), DLCorner.getX(), URCorner.getX());
        y = clampOnRange(pos.getY(), DLCorner.getY(), URCorner.getY());
        return new Position(x,y);
    }

    // method found in "2D Game Collision Detection" by Thomas Schwarzl
    private double clampOnRange(double x, double min, double max) {
        if (x < min) {
            return min;
        } else if (x > max) {
            return max;
        } else {
            return x;
        }
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public Position getDLCorner() {
        return DLCorner;
    }
    
    /**
     * {@inheritDoc}}
     */
    @Override
    public Position getURCorner() {
        return URCorner;
    }
    
}
