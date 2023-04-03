package it.unibo.AstroParty.common;


/**
 * 
 * a simple class that represents a point in a two dimensional space
 * 
 */
public class Position {
	final static double EPSILON = 0.000_001;
	private final double x;
	private final double y;

	/**
	 * @param x =  the X of the Position
	 * @param y =  the Y of the Position
	 */
	public Position(final double x, final double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * moves the point such accordingly to the direction given
	 * @param v: the {@link Direction} of the movemnt to be done
	 * @return the arriving position
	 */
	public Position move(final Direction v) {
		return new Position(this.x+v.getX(), this.y+v.getY());
	}
	
	/**
	 *  sum of two positions
	 * @param p: the position to be added to this
	 * @return the new position
	 */
	public Position add(final Position p) {
		return new Position(this.x+p.getX(), this.y+p.getY());
	}
	
	/**
	 * get the distance from another position
	 * @param pos : the position which the distance is to be calculated from
	 * @return the distance ad double using pitagora
	 */
	public double getDistanceFrom(final Position pos) {
		final double deltaX = x - pos.getX();
		final double deltaY = y - pos.getY();
		return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
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
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(final Object obj) {
	    
	    if (!(obj instanceof Position)) {
	        return false;
	    }
		final Position pos = (Position)obj;
	    return Math.abs(pos.getX() - this.getX()) < EPSILON
		&& Math.abs(pos.getY() - this.getY()) < EPSILON;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode(){
		final int prime = 7;
		int result = (int) (x + y);
		result = prime * result ;
		result = prime * result ;
		return result;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return Double.toString(x) + ":" + Double.toString(y);
	}

	/**
	 * @return a defensive copy of this Position
	 */
	public Position copy() {
		
		return new Position(this.x, this.y);
	}
}
