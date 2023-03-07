package it.unibo.AstroParty.common;

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
	
	public Direction add( Direction v ) {										//ad ogni update aggiungo alla posizione attuale il punto di arrivo
		return new Direction( this.x+v.getX() , this.y+v.getX() );
	}
	
	public Direction multiply( double alpha ) {
		return new Direction( this.x * alpha , this.y * alpha );
	}
	
	public String toString() {
		return Double.toString(x) + ":" + Double.toString(y);
	}
}
