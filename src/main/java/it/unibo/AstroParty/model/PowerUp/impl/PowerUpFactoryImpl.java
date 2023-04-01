package it.unibo.AstroParty.model.PowerUp.impl;

import it.unibo.AstroParty.common.Position;
import it.unibo.AstroParty.model.PowerUp.api.PowerUpFactory;
import it.unibo.AstroParty.model.api.EntityType;
import it.unibo.AstroParty.model.api.PowerUp;

/**
 * 
 * @author alessandro.coli2
 * a class that implements {@link PowerUpFactory}
 */
public class PowerUpFactoryImpl implements PowerUpFactory {
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public PowerUp createPowerUp(EntityType type, Position pos) {
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
				default:
					throw( new UnsupportedOperationException() );			
		}
		
		return pUp;
	}

	/**
	 * 
	 * @param pos of the PowerUp
	 * @return a UPGRADEDSPEED powerUP
	 */
	private PowerUp createSpeed(Position pos) {
		
		return new BasicPowerUp( pos, true,  EntityType.UPGRADEDSPEED ) {


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
				
				if ( this.inUse && this.startingTime + PowerUp.Duration  >= time ) {
					super.owner.normalSpeed();
					super.owner.removePowerUp( this );
				}
			}
			
		};
	}

	/**
	 * 
	 * @param pos of the PowerUp
	 * @return a DOUBLESHOT powerUP
	 */
	private PowerUp createDoubleShot(Position pos) {
		
		return new BasicPowerUp( pos, true,  EntityType.DOUBLESHOT ) {

			private boolean inUse;
			private double useTime = 0;

			@Override
			public void update(double time) {

				if( this.inUse ) {
					this.useTime +=time;
					System.out.println( this.useTime );
					if(this.useTime >= PowerUp.Duration) {
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

	/**
	 * 
	 * @param pos of the PowerUp
	 * @return a IMMORTALITY powerUP
	 */
	private PowerUp createImmortality(Position pos) {
		
		return new BasicPowerUp( pos, true,  EntityType.IMMORTALITY ) {


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
				
				if ( this.inUse && this.startingTime + PowerUp.Duration  >= time ) {
					super.owner.makeMortal();
					super.owner.removePowerUp( this );
				}
			}
			
		};
	}

	/**
	 * 
	 * @param pos of the PowerUp
	 * @return a SHIELD powerUP
	 */
	private PowerUp createShield(Position pos) {
		
		return new BasicPowerUp( pos, false, EntityType.SHIELD) {

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
