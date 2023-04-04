package it.unibo.astroparty.game.obstacle.impl;

import java.util.Optional;

import it.unibo.astroparty.common.Position;
import it.unibo.astroparty.game.api.EntityType;
import it.unibo.astroparty.game.hitbox.api.RectangleHitBox;
import it.unibo.astroparty.game.hitbox.impl.RectangleHitBoxImpl;
import it.unibo.astroparty.game.obstacle.api.Obstacle;
import it.unibo.astroparty.graphics.api.GraphicEntity;

/**
 * Implementation of Obstacle interface.
 */
public class ObstacleImpl implements Obstacle {

    private final boolean destroyable, harm;
    private final Position position;
    private final RectangleHitBox hBox;
    private final EntityType type;
    private final Optional<Timer> timer;

    private boolean active;

    private ObstacleImpl(final Position pos, final boolean destroyable, final boolean harm, final EntityType type,
            final Optional<Timer> timer) {
        this.hBox = new RectangleHitBoxImpl(pos, Obstacle.SIZE, Obstacle.SIZE);
        this.position = pos;
        this.destroyable = destroyable;
        this.harm = harm;
        this.type = type;
        this.active = true;
        this.timer = timer;
    }

    /**
     * 
     * @param pos the down-left corner of the obstacle
     * @param destroyable true if the obstacle can be destroyed
     * @param harm true if the obstacle is harmful
     * @param type the type of the entity
     * @param timer a timer that manages the changing of {@code active}
     */
    public ObstacleImpl(final Position pos, final boolean destroyable, final boolean harm, final EntityType type,
            final Timer timer) {
        this(pos, destroyable, harm, type, Optional.of(timer));
    }

    /**
     * 
     * @param pos the down-left corner of the obstacle
     * @param destroyable true if the obstacle can be destroyed
     * @param harm true if the obstacle is harmful
     * @param type the type of the entity
     */
    public ObstacleImpl(final Position pos, final boolean destroyable, final boolean harm, final EntityType type) {
        this(pos, destroyable, harm, type, Optional.empty());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Position getPosition() {
        return position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hit() {
        return destroyable;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final double time) {
        if (timer.isPresent() && timer.get().check(time)) {
            active = !active;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isActive() {
        return active;
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public boolean isHarmful() {
        return harm;
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public RectangleHitBox getHitBox() {
        return hBox;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityType getType() {
        return type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GraphicEntity getGraphicComponent() {
        return hBox.getGraphicComponent(type);
    }

}
