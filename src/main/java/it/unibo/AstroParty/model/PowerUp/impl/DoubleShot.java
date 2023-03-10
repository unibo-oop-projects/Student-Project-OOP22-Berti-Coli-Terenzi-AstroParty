package it.unibo.AstroParty.model.PowerUp.impl;

import it.unibo.AstroParty.common.Position;
import it.unibo.AstroParty.model.PowerUp.PowerUpTypes;
import it.unibo.AstroParty.model.api.PowerUp;

public class DoubleShot extends BasicPowerUp implements PowerUp {

	public DoubleShot(Position position) {
		super(position);
	}

	@Override
	public void update(double time) {
		// nothing to be done
	}

	@Override
	public void use() {
		super.owner.removePowerUp( this );
	}

	@Override
	public boolean isOffensive() {

		return true;
	}

	@Override
	public PowerUpTypes getType() {

		return PowerUpTypes.DOUBLESHOT;
	}

}
