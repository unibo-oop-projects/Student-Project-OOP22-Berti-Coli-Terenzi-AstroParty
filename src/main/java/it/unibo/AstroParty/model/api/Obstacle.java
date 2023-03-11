package it.unibo.AstroParty.model.api;

public interface Obstacle extends Entity {

    static final double size = 3;

    /**
     * 
     * @return true if the obstacle is visible/hittable
     */
    public boolean isActive();

    /**
     * 
     * @return true if the obstacle damages the spaceship
     */
    public boolean isHarmful();

    @Override
    public RectangleHitBox getHitBox();

}
