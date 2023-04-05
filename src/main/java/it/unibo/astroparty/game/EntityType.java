package it.unibo.astroparty.game;

import it.unibo.astroparty.game.powerUp.api.PowerUp;

/**
 * 
 * @author Alessandro Coli
 * 
 * an enum of the possible types of {@link PowerUp} inside AstroParty
 */
public enum EntityType {

    /** a spaceship. */
    SPACESHIP,

    /** a generalType of powerUP. */
    POWERUP,

    /** the shield powerUP. */
    SHIELD(POWERUP),

    /** the immortality powerUP. */
    IMMORTALITY(POWERUP),

    /** the doubleshot powerUP. */
    DOUBLESHOT(POWERUP),

    /**  the UpgradedSpeed powerUP. */
    UPGRADEDSPEED(POWERUP), 

    /** a general obstacle. */
    OBSTACLE,

    /** a laser Obstacle. */
    LASER(OBSTACLE), 

    /** a simple Obstacle. */
    SIMPLEOBSTACLE(OBSTACLE),

    /** a undestroyable Obstacle. */
    UNDESTROYABLEOBSTACLE(OBSTACLE),

    /** a projectile. */
    PROJECTILE;

    private final EntityType generalType;

    /**
     * used for already macrotypes.
     */
    EntityType() {
        this.generalType = null;
    }

    /**
     * 
     * @param generalType the macrotype of the entity.
     */
    EntityType(final EntityType generalType) {
        this.generalType = generalType;
    }

    /**
     * @return the general Type of Entity or null if it already is a General Type.
     */
    public EntityType getGeneralType() {
        return this.generalType;
    }
}
