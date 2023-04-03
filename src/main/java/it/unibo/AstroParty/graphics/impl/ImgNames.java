package it.unibo.AstroParty.graphics.impl;

import it.unibo.AstroParty.graphics.api.GraphicEntity;
import it.unibo.AstroParty.input.api.GameId;

/**
 *  this class contains the name of the images for each {@link EntityType}
 * so that they can all be saved in only one place and reused for multiple implementations.
 *
 */
public class ImgNames {
    private final String p1 =  "p1";
    private final String p2 = "p2";
    private final String p3 = "p3";
    private final String P4 = "p4";
    private final String shield = "Shield";
    private final String immortality = "Immortality";
    private final String doubleshot = "Doubleshot";
    private final String speed = "Speed";
    private final String laserObstacle = "Laser";
    private final String basicObstacle = "BasicObstacle";
    private final String undestroyableObstacle = "UndestroyableObstacle";
    private final String projectile = "Projectile";

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
                s = shield;
                break;

            case IMMORTALITY:
                s = immortality;
                break;

            case DOUBLESHOT:
                s = doubleshot;
                break;

            case UPGRADEDSPEED:
                s = speed;
                break;

            case LASER:
                s = laserObstacle;
                break;

            case SIMPLEOBSTACLE:
                s = basicObstacle;
                break;

            case PROJECTILE:
                s = projectile;
                break;

            case UNDESTROYABLEOBSTACLE:
                s = undestroyableObstacle;
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
                return p1;

            case PLAYER2:
                return p2;

            case PLAYER3:
                return p3;

            case PLAYER4:
                return P4;

                default:
                throw new UnsupportedOperationException();
        }
    }
}
