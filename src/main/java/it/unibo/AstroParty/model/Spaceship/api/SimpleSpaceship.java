package it.unibo.AstroParty.model.Spaceship.api;

import it.unibo.AstroParty.model.api.PowerUp;
import it.unibo.AstroParty.model.api.Spaceship;

/**
 * 
 * @author Alessandro Coli
 *
 * an extension of {@link Spaceship} used by the {@link PowerUp}
 */
public interface SimpleSpaceship extends Spaceship {

	/**
	 * makes the ship immortal
	 */
	public void makeImmortal() ;
	
	/**
	 * makes the ship mortal
	 */
	public void makeMortal() ;
	
	/**
	 * gives the ship a shield that blocks one damage instance
	 */
	public void newShield() ;
	
	/**
	 * set the speed to max
	 */
	public void upgradeSpeed() ;
	
	/**
	 * set the speed to normal
	 */
	public void normalSpeed() ;
	
	/**
	 * remove the power up after it's been used
	 * @param upgradedSpeed the powerUp to be removed
	 */
	public void removePowerUp(PowerUp upgradedSpeed);
}
