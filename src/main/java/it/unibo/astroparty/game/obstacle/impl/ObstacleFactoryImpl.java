package it.unibo.astroparty.game.obstacle.impl;

import it.unibo.astroparty.common.Position;
import it.unibo.astroparty.game.EntityType;
import it.unibo.astroparty.game.obstacle.api.Obstacle;
import it.unibo.astroparty.game.obstacle.api.ObstacleFactory;

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
