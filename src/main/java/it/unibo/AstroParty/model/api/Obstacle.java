package it.unibo.AstroParty.model.api;

public interface Obstacle extends Entity {

    static final double size = 3;

    /**
     * 
     * @return true if the obstacle is visible/hittable
     */
    public boolean isActive();

    @Override
    public RectangleHitBox getHitBox();

}
