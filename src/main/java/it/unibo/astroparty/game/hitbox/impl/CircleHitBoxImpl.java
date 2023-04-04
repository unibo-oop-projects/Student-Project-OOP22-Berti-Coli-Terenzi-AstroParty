package it.unibo.astroparty.game.hitbox.impl;

import it.unibo.astroparty.common.Position;
import it.unibo.astroparty.game.api.EntityType;
import it.unibo.astroparty.game.hitbox.api.CircleHitBox;
import it.unibo.astroparty.graphics.api.GraphicEntity;
import it.unibo.astroparty.graphics.impl.GraphicEntityImpl;

/**
 * CircleHitBox implementation.
 */
public class CircleHitBoxImpl implements CircleHitBox {

    private final Position center;
    private final double radius;

    /**
     * 
     * @param center the {@link Position} of the center
     * @param radius the radius
     */
    public CircleHitBoxImpl(final Position center, final double radius) {
        this.center = center;
        this.radius = radius;
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public boolean checkCircleCollision(final CircleHitBox hBox) {
        return center.getDistanceFrom(hBox.getCenter()) <= hBox.getRadius() + this.radius;
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public double getRadius() {
        return radius;
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public Position getCenter() {
        return center;
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public double getHeight() {
        return 2 * radius;
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public double getWidth() {
        return getHeight();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GraphicEntity getGraphicComponent(final EntityType type) {
        return new GraphicEntityImpl(center.add(new Position(-radius, -radius)),
                this.getHeight(), this.getWidth(), type);
    }
}
