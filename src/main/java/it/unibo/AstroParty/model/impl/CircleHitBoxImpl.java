package it.unibo.AstroParty.model.impl;

import it.unibo.AstroParty.common.Position;
import it.unibo.AstroParty.model.api.CircleHitBox;
import it.unibo.AstroParty.model.api.HitBox;

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
    public boolean isHittedBy(HitBox hBox) throws IllegalArgumentException {
        if (hBox instanceof CircleHitBox) {     // we currently have only circle entities moving, this is for future updates
            return center.getDistanceFrom(((CircleHitBox) hBox).getCenter())
                    <= ((CircleHitBox) hBox).getRadius() + this.radius;
        }
        throw new IllegalArgumentException();
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
    
}
