package it.unibo.AstroParty.model.Spaceship.api;

import java.util.Collection;

import it.unibo.AstroParty.core.impl.PlayerId;
import it.unibo.AstroParty.model.api.Spaceship;

/**
 * 
 * @author Alessandro Coli
 * a builder of all the {@link SimpleSpaceship} in a game
 */
public interface SpaceshipBuilder {
	
	/** 
	 * set the speed of the spaceships for the game
	 * @param speed
	 */
	public void setSpeed(double speed);
	
	/** 
	 * set max number of shots that can be fired at a time without power ups
	 * @param number of bullets
	 */
	public void setMaxBullets(int maxBullets);
	
	/** 
	 * set the time that a spaceship takes to recharge one shot
	 * @param time in milliseconds
	 */
	public void setRechargeTime(long time);
	
	/** 
	 * set the shield to o at the start of the game
	 * @param enable or disable if flase
	 */
	public void setStartingShield(boolean enable);
	
	/**
	 * creates the spaceships needed for the game
	 * 
	 * @param a collection of {@link PlayerId}, one for each {@link Spaceship} to be created
	 * @return a collection with one spaceship for each PlayerId given
	 */
	public Collection<Spaceship> create(Collection<PlayerId> playerIds);
	
}
