package it.unibo.AstroParty.model.Obstacle.api;

import it.unibo.AstroParty.common.Position;
import it.unibo.AstroParty.model.api.Obstacle;

/**
 * interface that model a Factory for creating the obstacles.
 */
public interface ObstacleFactory {

    /**
     * create a simple destroyable obstacle.
     * @param pos the down-left corner of the obstacle
     * @return a destroyable obstacle
     */
    Obstacle createSimpleObstacle(Position pos);

    /**
     * create an undestroyable obstacle.
     * @param pos the down-left corner of the obstacle
     * @return an unbreakable obstacle
     */
    Obstacle createUndestroyableObstacle(Position pos);

    /**
     * create a fatal and intermittent (not always active) obstacle.
     * @param pos the down-left corner of the obstacle
     * @return a laser 
     */
    Obstacle createLaser(Position pos);
}
