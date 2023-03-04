package it.unibo.AstroParty.model.impl;

import it.unibo.AstroParty.common.Position;
import it.unibo.AstroParty.model.api.CircleHitBox;

public class CircleHitBoxImpl implements CircleHitBox {

    private Position center;
    private double radius;

    /**
     * 
     * @param center the position of the center
     * @param radius the radius
     */
    public CircleHitBoxImpl(Position center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    @Override
    public boolean isHitted(Position pos, double radius) {
        return center.getDistanceFrom(pos) <= radius + this.radius;
    }

    @Override
    public double getRadius() {
        return radius;
    }
    
}
