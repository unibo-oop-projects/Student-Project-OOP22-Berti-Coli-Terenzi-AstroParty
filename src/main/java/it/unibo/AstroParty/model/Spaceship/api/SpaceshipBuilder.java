package it.unibo.AstroParty.model.Spaceship.api;

import java.util.Collection;

import it.unibo.AstroParty.core.impl.PlayerId;
import it.unibo.AstroParty.model.api.GameState;
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
	void setSpeed(double speed);
	
	/** 
	 * set max number of shots that can be fired at a time without power ups
	 * @param number of bullets
	 */
	void setMaxBullets(int maxBullets);
	
	/** 
	 * set the time that a spaceship takes to recharge one shot
	 * @param time in milliseconds
	 */
	void setRechargeTime(long time);
	
	/** 
	 * set the shield to o at the start of the game
	 * @param enable or disable if flase
	 */
	void setStartingShield(boolean enable);
	
	/**
	 * set the Players for the game, after this is been called, only {@link #create(GameState)} can be used
	 * @param playerIds : the {@link PlayerId} of all the Players
	 */
	void setids( Collection<PlayerId> playerIds);
	
	/**
	 * set the Players for the game, after this is been called, only {@link #create(GameState)} can be used
	 * @param playerNames : the names of all the Players
	 */
	void setNames( Collection<String> playerNames);
	/**
	 * creates the spaceships needed for the game
	 * 
	 * @param tha {@link GameState} of the current game
	 * @param a collection of {@link PlayerId}, one for each {@link Spaceship} to be created
	 * @return a collection with one spaceship for each PlayerId given
	 */
	Collection<Spaceship> create(GameState world);
	
}
