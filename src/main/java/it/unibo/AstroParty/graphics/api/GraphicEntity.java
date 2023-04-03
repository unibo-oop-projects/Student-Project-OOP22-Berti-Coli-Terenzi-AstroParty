package it.unibo.AstroParty.graphics.api;

import java.util.Optional;

import it.unibo.AstroParty.common.Position;
import it.unibo.AstroParty.input.api.GameId;
import it.unibo.AstroParty.model.api.EntityType;

/**
 * an entity to be drawn on screen.
 *
 */
public interface GraphicEntity {
    /**
     * @return the {@link Position} of the top-left corner, used to draw the image.
     */
    Position getPosition();

    /**
     * @return the angle as double, useful to rotate images.
     */
    double getAngle();

    /**
     * @return the height of the entity to be drawn.
     */
    double getHeight();

    /**
     * @return the length of the entity to be drawn.
     */
    double getLength();

    /**
     * @return the {@linkplain EntityType} of the entity to be drawn.
     */
    EntityType getType();

    /**
     * for Saceship only.
     * @return id the id to recognize the spaceship.
     */
    Optional<GameId> getId();

    /**
     * @param id set the gameId od the entity to id.
     */
    void setId(GameId id);

    /**
     * @param angle set the rotation angle to angle.
     */
    void setAngle(double angle);
}
