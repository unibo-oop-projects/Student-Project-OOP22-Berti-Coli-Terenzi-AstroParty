package it.unibo.AstroParty.model.api;

public interface Obstacle extends Entity {
    @Override
    public RectangleHitBox getHitBox();
}
