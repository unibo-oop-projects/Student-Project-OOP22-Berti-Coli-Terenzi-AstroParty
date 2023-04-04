package it.unibo.astroparty.game.hitbox.impl;

import it.unibo.astroparty.common.Position;
import it.unibo.astroparty.game.api.EntityType;
import it.unibo.astroparty.game.hitbox.api.CircleHitBox;
import it.unibo.astroparty.game.hitbox.api.RectangleHitBox;
import it.unibo.astroparty.graphics.api.GraphicEntity;
import it.unibo.astroparty.graphics.impl.GraphicEntityImpl;

/**
 * RectangleHitBox implementation.
 */
public class RectangleHitBoxImpl implements RectangleHitBox {

    private final Position cornerUL, cornerDR;
    private final double height, width;

    private RectangleHitBoxImpl(final Position cornerUL, final Position cornerDR, final double height, final double width) {
        this.cornerUL = cornerUL;
        this.cornerDR = cornerDR;
        this.height = height;
        this.width = width;
    }

    /**
     * 
     * @param cornerUL the up-left corner {@link Position}
     * @param cornerDR the down-right corner {@link Position}
     */
    public RectangleHitBoxImpl(final Position cornerUL, final Position cornerDR) {
        this(cornerUL, cornerDR, cornerDR.getY() - cornerUL.getY(), cornerDR.getX() - cornerUL.getX());
    }

    /**
     * 
     * @param cornerUL the up-left corner {@link Position}
     * @param width the rectangle width
     * @param height the rectangle height
     */
    public RectangleHitBoxImpl(final Position cornerUL, final double width, final double height) {
        this(cornerUL, new Position(cornerUL.getX() + width, cornerUL.getY() + height), height, width);
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
        x = clampOnRange(pos.getX(), cornerUL.getX(), cornerDR.getX());
        y = clampOnRange(pos.getY(), cornerUL.getY(), cornerDR.getY());
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
    public Position getcornerUL() {
        return cornerUL;
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public Position getcornerDR() {
        return cornerDR;
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
        return new GraphicEntityImpl(cornerUL, height, width, type);
    }

}
