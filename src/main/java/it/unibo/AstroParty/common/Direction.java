package it.unibo.AstroParty.common;

/**
 * 
 * a simple class describing movements as the distance in X and Y coordinates from the starting poin t
 */
public class Direction {
	private final double x;
	private final double y;
	
	/**
	 * @param x =  the X of the Position
	 * @param y =  the Y of the Position
	 */
	public Direction(final double x, final double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @return the X of the Position
	 */
	public double getX() {
		return this.x;
	}

	/**
	 * @return the Y of the Position
	 */
	public double getY() {
		return this.y;
	}
	
	/**
	 * sum of 2 Directions
	 * @param v: the Direction to be summed to this
	 * @return a new Direction 
	 */
	public Direction add(final Direction v) {										//ad ogni update aggiungo alla posizione attuale il punto di arrivo
		return new Direction(this.x+v.getX() , this.y+v.getX());
	}
	
	/**
	 * multiply the direction for a scalar
	 * @param alpha: the value to be multiplied 
	 * @return a new direction
	 */
	public Direction multiply(final double alpha) {
		return new Direction(this.x * alpha , this.y * alpha);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return Double.toString(x) + ":" + Double.toString(y);
	}
}
