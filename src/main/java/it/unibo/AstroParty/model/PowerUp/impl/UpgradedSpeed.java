package it.unibo.AstroParty.model.PowerUp.impl;

import it.unibo.AstroParty.common.Position;
import it.unibo.AstroParty.model.PowerUp.PowerUpTypes;
import it.unibo.AstroParty.model.api.Spaceship;

/** 
 * 
 * @author Alessandro Coli
 * a {@link PowerUp} that upgrades speed for a limited time
 */
public class UpgradedSpeed extends BasicPowerUp {
	
	private final static double duration = 5;
	
	private boolean inUse;
	
	private double upTime = 0;
	
	public UpgradedSpeed(Position position) {
		super(position, false);
	}
	@Override
	public boolean pickUp(Spaceship owner) {
		
		super.pickUp(owner);
		this.use();
		
		return super.pickedUp;	
	}

	@Override
	public void use() {
		this.inUse=true;
		super.owner.upgradeSpeed();
	}

	@Override
	public PowerUpTypes getType() {

		return PowerUpTypes.UPGRADEDSPEED;
	}

	@Override
	public void update(double time) {

		if ( super.pickedUp ) {
			this.upTime += time;
		}
		
		if ( this.inUse && this.upTime <= UpgradedSpeed.duration) {
			super.owner.normalSpeed();
			super.owner.removePowerUp( this );
		}
	}
	

}