package it.unibo.AstroParty.graphics.impl;

import java.util.Optional;

import it.unibo.AstroParty.common.Position;
import it.unibo.AstroParty.graphics.api.GraphicEntity;
import it.unibo.AstroParty.input.api.GameId;
import it.unibo.AstroParty.model.api.EntityType;
import it.unibo.AstroParty.model.api.HitBox;

public class GraphicEntityImpl implements GraphicEntity {
	
	private final HitBox hitbox;
	private final double angle;
	private final EntityType type;
	private final GameId id;
	
	public GraphicEntityImpl( HitBox hitbox, double angle, EntityType type) {
		this( hitbox, angle, type, null);
	}
	
	public GraphicEntityImpl( HitBox hitbox, double angle, EntityType type, GameId id) {
		this.hitbox = hitbox;
		this.angle = angle;
		this.type = type;
		this.id = id;
	}
	
	@Override
	public Position getPosition() {
		
		return this.hitbox.getUpperLeftCorner() ;
	}

	@Override
	public double getAngle() {
		
		return this.angle;
	}

	@Override
	public double getSize() {
		
		return this.hitbox.getSize();
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