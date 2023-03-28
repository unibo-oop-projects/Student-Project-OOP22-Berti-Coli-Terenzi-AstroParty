package it.unibo.AstroParty.model.Projectile.impl;

import it.unibo.AstroParty.common.Direction;
import it.unibo.AstroParty.common.Position;
import it.unibo.AstroParty.graphics.api.GraphicEntity;
import it.unibo.AstroParty.model.api.CircleHitBox;
import it.unibo.AstroParty.model.api.EntityType;
import it.unibo.AstroParty.model.api.Obstacle;
import it.unibo.AstroParty.model.api.Projectile;
import it.unibo.AstroParty.model.impl.CircleHitBoxImpl;

public class ProjectileImpl implements Projectile {

	private Position position;
	private Direction direction;
	private EntityType entityType;
	private CircleHitBox projectileHitBox;
	private double projectileSpeed;

	public ProjectileImpl(Position pos, Direction dir, EntityType type, double speed) {
		this.position = pos;
		this.direction = dir;
		this.entityType = type;
		this.projectileSpeed = speed;
		this.projectileHitBox = new CircleHitBoxImpl(pos, Projectile.radius);
	}

	@Override
	public Position getPosition() {
		// TODO Auto-generated method stub
		return position;
	}

	//TODO TOGLIERLO IN ENTITY COME METODO
	@Override
	public boolean hit() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update(double time) {
		// TODO Auto-generated method stub
		this.position = this.position.move(this.direction.multiply( this.projectileSpeed * time));
	}

	@Override
	public EntityType getType() {
		return entityType;
	}

	@Override
	public GraphicEntity getGraphicComponent() {
		// TODO Auto-generated method stub
		return projectileHitBox.getGraphicComponent(entityType);
	}

	@Override
	public CircleHitBox getHitBox() {
		// TODO Auto-generated method stub
		return projectileHitBox;
	}

}
