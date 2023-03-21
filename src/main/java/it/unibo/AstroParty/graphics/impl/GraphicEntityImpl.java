package it.unibo.AstroParty.graphics.impl;

import java.util.Optional;

import it.unibo.AstroParty.common.Position;
import it.unibo.AstroParty.graphics.api.GraphicEntity;
import it.unibo.AstroParty.input.api.GameId;
import it.unibo.AstroParty.model.api.EntityType;

public class GraphicEntityImpl implements GraphicEntity {
	
	private final Position pos;
	private final double angle;
	private final double size;
	private final EntityType type;
	private final GameId id;
	
	public GraphicEntityImpl(double size, Position pos, double angle, EntityType type) {
		this(angle, pos, angle, type, null);
	}
	
	public GraphicEntityImpl(double size, Position pos, double angle, EntityType type, GameId id) {
		this.pos = pos;
		this.angle = angle;
		this.size = size;
		this.type = type;
		this.id = id;
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
	@Override
	public EntityType getType() {
		
		return this.type;
	}
	@Override
	public Optional<GameId> getId() {
		
		return Optional.ofNullable( this.id );
	}

}
