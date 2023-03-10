package it.unibo.AstroParty.model.PowerUp.impl;

import it.unibo.AstroParty.common.Position;
import it.unibo.AstroParty.model.PowerUp.PowerUpTypes;
import it.unibo.AstroParty.model.PowerUp.api.PowerUpFactory;
import it.unibo.AstroParty.model.api.PowerUp;

public class PowerUpFactoryImpl implements PowerUpFactory {

	@Override
	public PowerUp createPowerUp(PowerUpTypes type, Position pos) {
		PowerUp pUp = null;
		
		switch (type) {
		
				case SHIELD:
					pUp = this.createShield(pos);
					break;
					
				case IMMORTALITY:
					pUp = this.createImmortality(pos);
					break;
					
				case DOUBLESHOT:
					pUp = this.createDoubleShot(pos);
					break;
				case UPGRADEDSPEED:
					pUp = this.createSpeed(pos);
					break;			
		}
		
		return pUp;
	}

	private PowerUp createSpeed(Position pos) {
		
		return new UpgradedSpeed( pos );
	}

	private PowerUp createDoubleShot(Position pos) {
		
		return new DoubleShot( pos );
	}

	private PowerUp createImmortality(Position pos) {
		
		return new TemporaryImmortality( pos );
	}

	private PowerUp createShield(Position pos) {
		
		return new Shield( pos );
	}

}
