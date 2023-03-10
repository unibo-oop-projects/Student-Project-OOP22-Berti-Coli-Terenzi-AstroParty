package it.unibo.AstroParty.model.PowerUp.impl;

import it.unibo.AstroParty.common.Position;
import it.unibo.AstroParty.model.PowerUp.PowerUpTypes;
import it.unibo.AstroParty.model.api.PowerUp;

/**
 * 
 * @author Alessandro Coli
 * implementation of a {@link PowerUp} that shoots two {@link Projectile} with one bullets for a limited time
 */
public class DoubleShot extends BasicPowerUp implements PowerUp {

	private final static double duration = 5;
	
	private boolean inUse;
	private double useTime=0;

	public DoubleShot(Position position) {
		super(position, true);
	}

	@Override
	public void update(double time) {
		
		if( this.inUse ) {
			this.useTime +=time;
			if(this.useTime <= DoubleShot.duration) {
				super.owner.removePowerUp( this );
			}
		}
		
	}

	@Override
	public void use() {
		this.inUse=true;
	}

	@Override
	public PowerUpTypes getType() {

		return PowerUpTypes.DOUBLESHOT;
	}

}
