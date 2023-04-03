package it.unibo.AstroParty.model.api;

/**
 * 
 * @author Alessandro Coli
 * 
 * an enum of the possible types of {@link PowerUp} inside AstroParty
 */
public enum EntityType {

	SPACESHIP,
	POWERUP,
	SHIELD(POWERUP),
	IMMORTALITY(POWERUP),
	DOUBLESHOT(POWERUP),
	UPGRADEDSPEED(POWERUP), 
	OBSTACLE,
	LASER(OBSTACLE), 
	SIMPLEOBSTACLE(OBSTACLE),
	UNDESTROYABLEOBSTACLE(OBSTACLE),
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
	EntityType(EntityType generalType) {
		this.generalType = generalType;
	}

	/**
	 * @return the general Type of Entity or null if it already is a General Type.
	 */
	public EntityType getGeneralType() {
		return this.generalType;
	}
}
