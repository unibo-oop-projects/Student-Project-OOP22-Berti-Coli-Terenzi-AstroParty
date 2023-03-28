package it.unibo.AstroParty.model.impl;

import it.unibo.AstroParty.common.Position;
import it.unibo.AstroParty.graphics.api.GraphicEntity;
import it.unibo.AstroParty.graphics.impl.GraphicEntityImpl;
import it.unibo.AstroParty.model.api.CircleHitBox;
import it.unibo.AstroParty.model.api.EntityType;

public class CircleHitBoxImpl implements CircleHitBox {

    private Position center;
    private double radius;

    /**
     * 
     * @param center the {@link Position} of the center
     * @param radius the radius
     */
    public CircleHitBoxImpl(Position center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public boolean checkCircleCollision(CircleHitBox hBox) {
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
        return 2*radius;
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
    public GraphicEntity getGraphicComponent(EntityType type) {
        return new GraphicEntityImpl(center.add(new Position(-radius, -radius)),
                this.getHeight(), this.getWidth(), type);
    }
}
