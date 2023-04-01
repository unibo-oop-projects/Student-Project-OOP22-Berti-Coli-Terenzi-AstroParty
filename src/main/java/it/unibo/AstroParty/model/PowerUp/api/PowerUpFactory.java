package it.unibo.AstroParty.model.PowerUp.api;

import it.unibo.AstroParty.common.Position;
import it.unibo.AstroParty.model.api.EntityType;
import it.unibo.AstroParty.model.api.PowerUp;

/**
 * 
 * a factory for {@link PowerUp}
 */
public interface PowerUpFactory {
	
	/**
	 * create a new {@link PowerUp}
	 * @param type: the type of the new power up
	 * @param pos: the position inside the map
	 * @return the powerup
	 */
	public PowerUp createPowerUp(EntityType type, Position pos);
}
