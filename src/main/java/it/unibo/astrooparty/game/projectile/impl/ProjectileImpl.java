package it.unibo.astroparty.game.projectile.impl;

import it.unibo.astroparty.common.Direction;
import it.unibo.astroparty.common.Position;
import it.unibo.astroparty.game.api.EntityType;
import it.unibo.astroparty.game.hitbox.api.CircleHitBox;
import it.unibo.astroparty.game.hitbox.impl.CircleHitBoxImpl;
import it.unibo.astroparty.game.projectile.api.Projectile;
import it.unibo.astroparty.graphics.api.GraphicEntity;

/**
 * class for implementation of the projectile interface with the following methods
 * 
 * @author dario
 *
 */
public class ProjectileImpl implements Projectile {

    private Position position;
    private Direction direction;
    private EntityType entityType;
    private double projectileSpeed;
    
    /**
     * constructor for the class, it sets all the fields of the projectile
     * @param pos
     * @param dir
     * @param type
     * @param speed
     */
    public ProjectileImpl(Position pos, Direction dir, EntityType type, double speed) {
        this.position = pos;
        this.direction = dir;
        this.entityType = type;
        this.projectileSpeed = speed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Position getPosition() {
        // TODO Auto-generated method stub
        return position;
    }

    //TODO TOGLIERLO IN ENTITY COME METODO
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hit() {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(double time) {
        // TODO Auto-generated method stub
        this.position = this.position.move(this.direction.multiply( this.projectileSpeed * time ));
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public EntityType getType() {
        return entityType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GraphicEntity getGraphicComponent() {
        // TODO Auto-generated method stub
        return new CircleHitBoxImpl(position, Projectile.radius ).getGraphicComponent(entityType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CircleHitBox getHitBox() {
        // TODO Auto-generated method stub
        return new CircleHitBoxImpl(position, Projectile.radius);
    }

}
