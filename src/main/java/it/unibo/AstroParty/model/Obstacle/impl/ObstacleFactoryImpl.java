package it.unibo.AstroParty.model.Obstacle.impl;

import it.unibo.AstroParty.common.Position;
import it.unibo.AstroParty.model.Obstacle.api.ObstacleFactory;
import it.unibo.AstroParty.model.api.EntityType;
import it.unibo.AstroParty.model.api.Obstacle;

/**
 * implementation of {@link ObstacleFactory}.
 */
public class ObstacleFactoryImpl implements ObstacleFactory {

    // milliseconds requiered to make the variable "active" change status (in a laser)
    private static final int LASER_INTERVAL = 6000;

    /**
     * {@inheritDoc}}
     */
    @Override
    public Obstacle createSimpleObstacle(final Position pos) {
        return new ObstacleImpl(pos, true, false, EntityType.SIMPLEOBSTACLE);
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public Obstacle createUndestroyableObstacle(final Position pos) {
        return new ObstacleImpl(pos, false, false, EntityType.UNDESTROYABLEOBSTACLE);
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public Obstacle createLaser(final Position pos) {
        return new ObstacleImpl(pos, false, true, EntityType.LASER, new Timer(LASER_INTERVAL));
    }
}
