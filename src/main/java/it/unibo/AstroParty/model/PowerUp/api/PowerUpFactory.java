package it.unibo.AstroParty.model.PowerUp.api;

import it.unibo.AstroParty.common.Position;
import it.unibo.AstroParty.model.PowerUp.PowerUpTypes;
import it.unibo.AstroParty.model.api.PowerUp;

public interface PowerUpFactory {
	public PowerUp createPowerUp(PowerUpTypes type, Position pos);
}
