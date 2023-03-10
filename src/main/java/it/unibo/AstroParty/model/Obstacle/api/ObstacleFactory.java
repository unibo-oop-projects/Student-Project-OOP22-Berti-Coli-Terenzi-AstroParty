package it.unibo.AstroParty.model.Obstacle.api;

import it.unibo.AstroParty.common.Position;
import it.unibo.AstroParty.model.api.Obstacle;

public interface ObstacleFactory {
    
    /**
     * create a simple destroyable obstacle
     * @param pos the down-left corner of the obstacle
     * @return a destroyable obstacle
     */
    public Obstacle createSimpleObstacle(Position pos);
    
    /**
     * create a fatal and intermittent (not always active) obstacle 
     * @param pos the down-left corner of the obstacle
     * @return a laser 
     */
    public Obstacle createLaser(Position pos);
}
