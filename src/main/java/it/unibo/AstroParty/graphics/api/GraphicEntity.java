package it.unibo.AstroParty.graphics.api;

import java.util.Optional;

import it.unibo.AstroParty.common.Position;
import it.unibo.AstroParty.input.api.GameId;
import it.unibo.AstroParty.model.api.EntityType;

/**
 * 
 * an entity to be drawn on screen
 *
 */
public interface GraphicEntity {
	
	/**
	 * @return the {@link Position} of the top-left corner, used to draw the image
	 */
	public Position getPosition();
	
	/**
	 * @return the angle as double, useful to rotate images
	 */
	public double getAngle();
	
	/**
	 * @return the height of the entity to be drawn
	 */
	public double getHeight();

	/**
	 * @return the length of the entity to be drawn
	 */
	public double getLength();

	/**
	 * @return the {@linkplain EntityType} of the entity to be drawn
	 */
	public EntityType getType();
	
	/**
	 * for Saceship only
	 * @return the id to recognize the spaceship
	 */
	public Optional<GameId> getId();

	/**
	 * @param set the gameId od the entity to id
	 */
	public void setId( GameId id );

	/**
	 * @param set the rotation angle to angle
	 */
	public void setAngle( double angle );
}
