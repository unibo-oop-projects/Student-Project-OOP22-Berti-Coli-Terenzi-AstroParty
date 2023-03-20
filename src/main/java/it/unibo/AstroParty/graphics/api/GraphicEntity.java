package it.unibo.AstroParty.graphics.api;

import it.unibo.AstroParty.common.Position;

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
}
