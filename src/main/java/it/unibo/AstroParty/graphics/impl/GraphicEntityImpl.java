package it.unibo.AstroParty.graphics.impl;

import java.util.Optional;

import it.unibo.AstroParty.common.Position;
import it.unibo.AstroParty.graphics.api.GraphicEntity;
import it.unibo.AstroParty.input.api.GameId;
import it.unibo.AstroParty.model.api.EntityType;

public class GraphicEntityImpl implements GraphicEntity {
	
	private double angle = 0;
	private final EntityType type;
	private GameId id;
	private final double height;
	private final double length ;
	private final Position corner;
	
	public GraphicEntityImpl(Position topLetfCorner, double height, double length, EntityType type){
		this.corner = topLetfCorner;
		this.height = height;
		this.length = length;
		this.type = type;
	}

	@Override
	public EntityType getType() {
		
		return this.type;
	}
	@Override
	public Optional<GameId> getId() {
		
		return Optional.ofNullable( this.id );
	}

	@Override
	public void setId(GameId id) {
		this.id = id;
	}

	@Override
	public void setAngle(double angle) {
		this.angle = angle;
	}
	@Override
	public Position getPosition() {
		return this.corner.copy();
	}
	@Override
	public double getAngle() {
		return this.angle;
	}
	@Override
	public double getHeight() {
		return this.height;
	}

	@Override
	public double getLength() {
		return this.length;
	}

}