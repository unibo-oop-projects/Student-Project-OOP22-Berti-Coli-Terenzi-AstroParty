package it.unibo.AstroParty.model.PowerUp.impl;

import it.unibo.AstroParty.common.Position;
import it.unibo.AstroParty.model.PowerUp.PowerUpTypes;
import it.unibo.AstroParty.model.api.Spaceship;

/** 
 * 
 * @author Alessandro Coli
 * a {@link PowerUp} that gives a shield that tanks one damage instance
 */
public class Shield extends BasicPowerUp {

	public Shield(Position position) {
		super(position, false);
	}

	@Override
	public void update(double time) {
		
	}
	
	@Override
	public boolean pickUp(Spaceship owner) {
		
		super.pickUp(owner);
		this.use();
		
		return super.pickedUp;	
	}

	@Override
	public void use() {
		super.owner.newShield();
		super.owner.removePowerUp( this );
	}

	@Override
	public PowerUpTypes getType() {
		
		return PowerUpTypes.SHIELD;
	}

}
