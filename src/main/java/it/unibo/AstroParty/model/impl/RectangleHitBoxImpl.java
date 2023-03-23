package it.unibo.AstroParty.model.impl;

import it.unibo.AstroParty.common.Position;
import it.unibo.AstroParty.graphics.api.GraphicEntity;
import it.unibo.AstroParty.graphics.impl.GraphicEntityImpl;
import it.unibo.AstroParty.model.api.CircleHitBox;
import it.unibo.AstroParty.model.api.EntityType;
import it.unibo.AstroParty.model.api.RectangleHitBox;

public class RectangleHitBoxImpl implements RectangleHitBox {

    private Position ULCorner, DRCorner;
    private double height, width;

    private RectangleHitBoxImpl(Position ULCorner, Position DRCorner, double height, double width) {
        this.ULCorner = ULCorner;
        this.DRCorner = DRCorner;
        this.height = height;
        this.width = width;
    }

    /**
     * 
     * @param ULCorner the up-left corner {@link Position}
     * @param DRCorner the down-right corner {@link Position}
     */
    public RectangleHitBoxImpl(Position ULCorner, Position DRCorner) {
        this(ULCorner, DRCorner, ULCorner.getY() - DRCorner.getY() ,ULCorner.getX() - DRCorner.getX());
    }

    /**
     * 
     * @param ULCorner the up-left corner {@link Position}
     * @param width the rectangle width
     * @param height the rectangle height
     */
    public RectangleHitBoxImpl(Position ULCorner, double width, double height) {
        this(ULCorner, new Position(ULCorner.getX()+width, ULCorner.getY()+height), height, width);
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public boolean checkCircleCollision(CircleHitBox hBox) {
        Position center = hBox.getCenter();
        return center.getDistanceFrom(clampOnRectangle(center)) < hBox.getRadius();
    }

    // method found in "2D Game Collision Detection" by Thomas Schwarzl
    private Position clampOnRectangle(Position pos) {
        double x,y;
        x = clampOnRange(pos.getX(), ULCorner.getX(), DRCorner.getX());
        y = clampOnRange(pos.getY(), ULCorner.getY(), DRCorner.getY());
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
    public Position getULCorner() {
        return ULCorner;
    }
    
    /**
     * {@inheritDoc}}
     */
    @Override
    public Position getDRCorner() {
        return DRCorner;
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public double getHeight() {
        return height;
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public GraphicEntity getGraphicComponent(EntityType type) {

        return new GraphicEntityImpl(DRCorner, height, height, type);
    }
    
}
