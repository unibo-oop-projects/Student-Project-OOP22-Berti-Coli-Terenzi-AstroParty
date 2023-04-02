package it.unibo.AstroParty.model.impl;

import it.unibo.AstroParty.common.Position;
import it.unibo.AstroParty.graphics.api.GraphicEntity;
import it.unibo.AstroParty.graphics.impl.GraphicEntityImpl;
import it.unibo.AstroParty.model.api.CircleHitBox;
import it.unibo.AstroParty.model.api.EntityType;
import it.unibo.AstroParty.model.api.RectangleHitBox;

/**
 * RectangleHitBox implementation.
 */
public class RectangleHitBoxImpl implements RectangleHitBox {

    private final Position ULCorner, DRCorner;
    private final double height, width;

    private RectangleHitBoxImpl(final Position ULCorner, final Position DRCorner, final double height, final double width) {
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
    public RectangleHitBoxImpl(final Position ULCorner, final Position DRCorner) {
        this(ULCorner, DRCorner, DRCorner.getY() - ULCorner.getY(), DRCorner.getX() - ULCorner.getX());
    }

    /**
     * 
     * @param ULCorner the up-left corner {@link Position}
     * @param width the rectangle width
     * @param height the rectangle height
     */
    public RectangleHitBoxImpl(final Position ULCorner, final double width, final double height) {
        this(ULCorner, new Position(ULCorner.getX() + width, ULCorner.getY() + height), height, width);
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public boolean checkCircleCollision(final CircleHitBox hBox) {
        final Position center = hBox.getCenter();
        return center.getDistanceFrom(clampOnRectangle(center)) < hBox.getRadius();
    }

    // method found in "2D Game Collision Detection" by Thomas Schwarzl
    private Position clampOnRectangle(final Position pos) {
        double x, y;
        x = clampOnRange(pos.getX(), ULCorner.getX(), DRCorner.getX());
        y = clampOnRange(pos.getY(), ULCorner.getY(), DRCorner.getY());
        return new Position(x, y);
    }

    // method found in "2D Game Collision Detection" by Thomas Schwarzl
    private double clampOnRange(final double x, final double min, final double max) {
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

    /**
     * {@inheritDoc}
     */
    @Override
    public GraphicEntity getGraphicComponent(final EntityType type) {
        return new GraphicEntityImpl(ULCorner, height, width, type);
    }

}
