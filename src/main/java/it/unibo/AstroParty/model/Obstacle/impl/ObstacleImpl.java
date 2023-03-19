package it.unibo.AstroParty.model.Obstacle.impl;

import java.util.Optional;

import it.unibo.AstroParty.common.Position;
import it.unibo.AstroParty.model.api.Obstacle;
import it.unibo.AstroParty.model.api.RectangleHitBox;
import it.unibo.AstroParty.model.impl.RectangleHitBoxImpl;

public class ObstacleImpl implements Obstacle {

    boolean destroyable, active, harm;
    Position position;
    RectangleHitBox hBox;
    Optional<Timer> timer;

    private ObstacleImpl(Position pos, boolean destroyable, boolean harm, Optional<Timer> timer) {
        this.hBox = new RectangleHitBoxImpl(pos, Obstacle.size, Obstacle.size);
        this.position = pos;
        this.destroyable = destroyable;
        this.harm = harm;
        this.active = true;
        this.timer = timer;
    }

    /**
     * 
     * @param pos the down-left corner of the obstacle
     * @param destroyable true if the obstacle can be destroyed
     * @param harm true if the obstacle is harmful
     * @param timer a timer that manages the changing of {@code active}
     */
    public ObstacleImpl(Position pos, boolean destroyable, boolean harm, Timer timer) {
        this(pos, destroyable, harm, Optional.of(timer));
    }

    /**
     * 
     * @param pos the down-left corner of the obstacle
     * @param destroyable true if the obstacle can be destroyed
     * @param harm true if the obstacle is harmful
     */
    public ObstacleImpl(Position pos, boolean destroyable, boolean harm) {
        this(pos, destroyable, harm, Optional.empty());
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public Position getPosition() {
        return position;
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public boolean hit() {
        return destroyable;
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public void update(double time) {
        if (timer.isPresent() && timer.get().check(time)) {
            active = !active;
        }
    }

    /**
     * {@inheritDoc}}
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
    
}
