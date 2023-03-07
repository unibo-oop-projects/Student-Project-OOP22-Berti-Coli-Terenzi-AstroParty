package it.unibo.AstroParty.common;

public class Position {
	final static double epsilon = 0.000001;
	private double x,y;

	public Position(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Position move(Direction v) {										//ad ogni update aggiungo alla posizione attuale il punto di arrivo
		return new Position( this.x+v.getX() , this.y+v.getY() );
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}
	
	public boolean equals(Position pos) {					
		return Math.abs( pos.getX() - this.getX() ) < epsilon 
				&& Math.abs( pos.getX() - this.getX() ) < epsilon;
	}

	public double getDistanceFrom(Position pos) {
		final double deltaX = x - pos.getX();
		final double deltaY = y - pos.getY();
		return Math.sqrt((deltaX*deltaX) + (deltaY+deltaY));
	}
	
	public String toString() {
		return Double.toString(x) + ":" + Double.toString(y);
	}
}
