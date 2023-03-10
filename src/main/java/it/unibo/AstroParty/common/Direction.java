package it.unibo.AstroParty.common;

/**
 * 
 * a simple class describing movements as the distance in X and Y coordinates from the starting poin t
 */
public class Direction {
	private double x,y;
	
	public Direction( double x, double y ) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}
	
	/**
	 * sum of 2 Directions
	 * @param v: the Direction to be summed to this
	 * @return a new Direction 
	 */
	public Direction add( Direction v ) {										//ad ogni update aggiungo alla posizione attuale il punto di arrivo
		return new Direction( this.x+v.getX() , this.y+v.getX() );
	}
	
	/**
	 * multiply the direction for a scalar
	 * @param alpha: the value to be multiplied 
	 * @return a new direction
	 */
	public Direction multiply( double alpha ) {
		return new Direction( this.x * alpha , this.y * alpha );
	}
	
	public String toString() {
		return Double.toString(x) + ":" + Double.toString(y);
	}
}
