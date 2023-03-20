package it.unibo.AstroParty.graphics.impl;

import it.unibo.AstroParty.common.Position;
import it.unibo.AstroParty.graphics.api.GraphicEntity;

public class GraphicEntityImpl implements GraphicEntity {
	
	private final Position pos;
	private final double angle;
	private final double size;
	
	public GraphicEntityImpl(double size, Position pos, double angle) {
		this.pos = pos;
		this.angle = angle;
		this.size = size;
		
	}
	@Override
	public Position getPosition() {
		
		return this.pos.copy() ;
	}

	@Override
	public double getAngle() {
		
		return this.angle;
	}

	@Override
	public double getSize() {
		
		return this.size;
	}

}
