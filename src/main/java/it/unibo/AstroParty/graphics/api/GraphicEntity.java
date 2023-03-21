package it.unibo.AstroParty.graphics.api;

import java.util.Optional;

import it.unibo.AstroParty.common.Position;
import it.unibo.AstroParty.input.api.GameId;
import it.unibo.AstroParty.model.api.EntityType;

public interface GraphicEntity {
	
	/**
	 * @return the {@link Position} of the top-left corner, used to draw the image
	 */
	public Position getPosition();
	
	/**
	 * @return the angle as double, usefull to rotate images
	 */
	public double getAngle();
	
	/**
	 * @return the size of the entity to be drawn
	 */
	public double getSize();
	
	/**
	 * @return the {@linkplain EntityType} of the entity to be drawn
	 */
	public EntityType getType();
	
	/**
	 * for Saceship only
	 * @return the id to recognize the spaceship
	 */
	public Optional<GameId> getId();
}
