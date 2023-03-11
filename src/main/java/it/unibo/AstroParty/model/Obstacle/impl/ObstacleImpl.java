package it.unibo.AstroParty.model.Obstacle.impl;

import it.unibo.AstroParty.common.Position;
import it.unibo.AstroParty.model.api.Obstacle;
import it.unibo.AstroParty.model.api.RectangleHitBox;
import it.unibo.AstroParty.model.impl.RectangleHitBoxImpl;

public class ObstacleImpl implements Obstacle {

    boolean destroyable, active, harm;
    Position position;
    RectangleHitBox hBox;
    ActiveSetter setActive;

    /**
     * 
     * @param pos the down-left corner of the obstacle
     * @param destroyable true if the obstacle can be destroyed
     * @param harm true if the obstacle is harmful
     * @param setActive 
     */
    public ObstacleImpl(Position pos, boolean destroyable, boolean harm, ActiveSetter setActive) {
        this.position = pos;
        this.destroyable = destroyable;
        this.harm = harm;
        this.active = true;     // always starts as active
        this.setActive = setActive;
        this.hBox = new RectangleHitBoxImpl(pos, Obstacle.size, Obstacle.size);
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public boolean hit() {
        return destroyable;
    }

    @Override
    public void update(double time) {
        active = setActive.apply(time);
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public boolean isHarmful() {
        return harm;
    }

    @Override
    public RectangleHitBox getHitBox() {
        return hBox;
    }
    
}
