package it.unibo.astroparty.graphics.impl;

import java.util.Optional;

import it.unibo.astroparty.common.Position;
import it.unibo.astroparty.game.EntityType;
import it.unibo.astroparty.graphics.api.GraphicEntity;
import it.unibo.astroparty.input.api.GameId;

/**
 * {@inheritDoc}.
 */
public class GraphicEntityImpl implements GraphicEntity {

    private double angle;
    private final EntityType type;
    private GameId id;
    private final double height;
    private final double length;
    private final Position corner;

    /**
     * all the basic parameters for drqwing an entity.
     * 
     * @param topLeftCorner of the entity
     * @param height of the entity
     * @param length of the entity
     * @param type of the entity
     */
    public GraphicEntityImpl(final Position topLeftCorner, final double height, final double length, final EntityType type) {
        this.corner = topLeftCorner;
        this.height = height;
        this.length = length;
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityType getType() {

        return this.type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<GameId> getId() {

        return Optional.ofNullable(this.id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setId(final GameId id) {
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAngle(final double angle) {
        this.angle = angle;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Position getPosition() {
        return this.corner.copy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getAngle() {
        return this.angle;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getHeight() {
        return this.height;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getLength() {
        return this.length;
    }
}
