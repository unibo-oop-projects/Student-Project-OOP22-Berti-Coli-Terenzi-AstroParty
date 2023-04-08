package it.unibo.astroparty.game.hitbox.api;

import it.unibo.astroparty.game.EntityType;
import it.unibo.astroparty.graphics.api.GraphicEntity;

/**
 * Interface that shapes the boundaries of an {@link Entity} and its collisions.
 */
public interface HitBox {

    /**
     * Used to get to know if the {@link Entity} modelled by this hitbox and another (circle-shaped) have collied.
     * @param hBox the {@link CircleHitBox} of the other {@link Entity}
     * @return true if the two entities have collied
     */
    boolean checkCollision(CircleHitBox hBox);

    /*
     * For future implementation, at the moment we have to manage only collisions with circles.
     */
    // boolean checkCollision(RectangleHitBox hBox);

    /**
     * @return the height
     */
    double getHeight();

    /**
     * @return the width
     */
    double getWidth();

    /**
     * @param type of the entity from {@link EntityType} enum
     * @return the GraphicEntity corresponding to the entity modelled by this HitBox
     */
    GraphicEntity getGraphicComponent(EntityType type);

}
