package it.unibo.astroparty.graphics.impl;

import it.unibo.astroparty.graphics.api.GraphicEntity;
import it.unibo.astroparty.input.api.GameId;

/**
 *  this class contains the name of the images for each {@link EntityType}
 * so that they can all be saved in only one place and reused for multiple implementations.
 *
 */
public class ImgNames {
    private static final String P1 =  "p1";
    private static final String P2 = "p2";
    private static final String P3 = "p3";
    private static final String P4 = "p4";
    private static final String SHIELD = "Shield";
    private static final String IMMORTALITY = "Immortality";
    private static final String DOUBLESHOT = "Doubleshot";
    private static final String SPEED = "Speed";
    private static final String LASER_OBSTACLE = "Laser";
    private static final String BASIC_OBSTACLE = "BasicObstacle";
    private static final String UNDESTROYABLE_OBSTACLE = "UndestroyableObstacle";
    private static final String PROJECTILE = "Projectile";

    /**
     * 
     * @param entity 
     * @return the name of the file that has to be drawn for the specific {@link EntityType}.
     */
    public String getName(final GraphicEntity entity) {
        String s = null;
        switch (entity.getType()) {

            case SPACESHIP:
                s = this.getSpaceshipimg(entity.getId().get());
                break;

            case SHIELD:
                s = SHIELD;
                break;

            case IMMORTALITY:
                s = IMMORTALITY;
                break;

            case DOUBLESHOT:
                s = DOUBLESHOT;
                break;

            case UPGRADEDSPEED:
                s = SPEED;
                break;

            case LASER:
                s = LASER_OBSTACLE;
                break;

            case SIMPLEOBSTACLE:
                s = BASIC_OBSTACLE;
                break;

            case PROJECTILE:
                s = PROJECTILE;
                break;

            case UNDESTROYABLEOBSTACLE:
                s = UNDESTROYABLE_OBSTACLE;
                break;

            default:
                throw new UnsupportedOperationException();
        }
        return s;
    }

    /**
     * 
     * @param id of a spaceship.
     * @return the name of the file that has to be drawn for the specific {@link Spaceship}.
     */
    private String getSpaceshipimg(final GameId id) {

        switch (id) {
            case PLAYER1:
                return P1;

            case PLAYER2:
                return P2;

            case PLAYER3:
                return P3;

            case PLAYER4:
                return P4;

                default:
                throw new UnsupportedOperationException();
        }
    }
}
