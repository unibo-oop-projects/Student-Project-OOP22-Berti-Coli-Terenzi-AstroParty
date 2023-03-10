package it.unibo.AstroParty.model.PowerUp.api;

import it.unibo.AstroParty.common.Position;
import it.unibo.AstroParty.model.PowerUp.PowerUpTypes;
import it.unibo.AstroParty.model.api.PowerUp;

/**
 * 
 * @author Alessandro Coli
 * a factory for {@link PowerUp}
 */
public interface PowerUpFactory {
	
	/**
	 * create a new {@link PowerUp}
	 * @param type: the type of the new power up
	 * @param pos: the position inside the map
	 * @return the powerup
	 */
	public PowerUp createPowerUp(PowerUpTypes type, Position pos);
}
