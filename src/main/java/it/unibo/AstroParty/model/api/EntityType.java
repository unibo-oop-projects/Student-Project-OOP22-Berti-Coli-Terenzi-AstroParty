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
	PROJECTILE;

	EntityType generalType;

	EntityType(){
		this.generalType = null;
	}

	EntityType (EntityType generalType){
		this.generalType = generalType;
	}

	public EntityType getGeneralType(){
		return this.generalType;
	}
}
