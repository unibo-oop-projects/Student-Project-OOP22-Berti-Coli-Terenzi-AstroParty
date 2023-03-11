package it.unibo.AstroParty.model.Obstacle.impl;

import it.unibo.AstroParty.common.Position;
import it.unibo.AstroParty.model.Obstacle.api.ObstacleFactory;
import it.unibo.AstroParty.model.api.Obstacle;

public class ObstacleFactoryImpl implements ObstacleFactory {

    private static double laserInterval = 2; // time requiered to make the variable "active" change status (in a laser)

    @Override
    public Obstacle createSimpleObstacle(Position pos) {
        return new ObstacleImpl(pos, true, false, new ActiveSetter((sum,t) -> true));
    }

    @Override
    public Obstacle createLaser(Position pos) {
        return new ObstacleImpl(pos,
                false,
                true,
                new ActiveSetter((sum,t) -> {
                    sum += t;
                    if (sum >= laserInterval) {
                        sum += (t - laserInterval);
                        return true;
                    } else {
                        return false;
                    }
                }));
    }

}
