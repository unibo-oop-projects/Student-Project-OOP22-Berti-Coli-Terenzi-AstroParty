package it.unibo.AstroParty.model.PowerUp.impl;

import it.unibo.AstroParty.common.Position;
import it.unibo.AstroParty.model.PowerUp.PowerUpTypes;
import it.unibo.AstroParty.model.PowerUp.api.PowerUpFactory;
import it.unibo.AstroParty.model.api.PowerUp;

/**
 * 
 * @author alessandro.coli2
 * a class that implements {@link PowerUpFactory}
 */
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
		
		return new BasicPowerUp( pos, true,  PowerUpTypes.UPGRADEDSPEED ) {


			private boolean inUse;
			private double startingTime;

			@Override
			public void use() {
				this.inUse=true;
				super.owner.upgradeSpeed();
			}

			@Override
			public void update(double time) {
				
				if( super.pickedUp && !this.inUse) {
					
					this.startingTime = time;
					this.use();
				}
				
				if ( this.inUse && this.startingTime + PowerUp.Duration  <= time ) {
					super.owner.normalSpeed();
					super.owner.removePowerUp( this );
				}
			}
			
		};
	}

	private PowerUp createDoubleShot(Position pos) {
		
		return new BasicPowerUp( pos, true,  PowerUpTypes.DOUBLESHOT ) {

			private boolean inUse;
			private double useTime;

			@Override
			public void update(double time) {
				if( this.inUse ) {
					this.useTime +=time;
					if(this.useTime <= PowerUp.Duration) {
						super.owner.removePowerUp( this );
					}
				}
			}

			@Override
			public void use() {
				this.inUse=true;
			}
			
		};
	}

	private PowerUp createImmortality(Position pos) {
		
		return new BasicPowerUp( pos, true,  PowerUpTypes.IMMORTALITY ) {


			private boolean inUse;
			private double startingTime;

			@Override
			public void use() {
				this.inUse=true;
				super.owner.makeImmortal();;
			}

			@Override
			public void update(double time) {
				
				if( super.pickedUp && !this.inUse) {
					
					this.startingTime = time;
					this.use();
				}
				
				if ( this.inUse && this.startingTime + PowerUp.Duration  <= time ) {
					super.owner.makeMortal();
					super.owner.removePowerUp( this );
				}
			}
			
		};
	}

	private PowerUp createShield(Position pos) {
		
		return new BasicPowerUp( pos, false, PowerUpTypes.SHIELD) {

			@Override
			public void update(double time) {
				if( super.pickedUp ) {
					this.use();
				}
			}

			@Override
			public void use() {
				super.owner.newShield();
				super.owner.removePowerUp( this );
			}
			
		};
	}

}
