package it.unibo.astroparty.game.projectile.impl;

import it.unibo.astroparty.common.Direction;
import it.unibo.astroparty.common.Position;
import it.unibo.astroparty.game.EntityType;
import it.unibo.astroparty.game.hitbox.api.CircleHitBox;
import it.unibo.astroparty.game.hitbox.impl.CircleHitBoxImpl;
import it.unibo.astroparty.game.projectile.api.Projectile;
import it.unibo.astroparty.graphics.api.GraphicEntity;

/**
 * class for implementation of the projectile interface with the following methods.
 * 
 * @author dario
 *
 */
public class ProjectileImpl implements Projectile {

    private Position position;
    private final Direction direction;
    private final EntityType entityType;
    private final double projectileSpeed;

    /**
     * constructor for {@link ProjectileImpl} it sets all the fields of the projectile.
     * @param pos
     * @param dir
     * @param type
     * @param speed
     */
    public ProjectileImpl(final Position pos, final  Direction dir, final  EntityType type, final double speed) {
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

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hit() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final double time) {
        // TODO Auto-generated method stub
        position = this.position.move(this.direction.multiply(this.projectileSpeed * time));
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
        return new CircleHitBoxImpl(position, Projectile.RADIUS).getGraphicComponent(entityType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CircleHitBox getHitBox() {
        // TODO Auto-generated method stub
        return new CircleHitBoxImpl(position, Projectile.RADIUS);
    }

}
