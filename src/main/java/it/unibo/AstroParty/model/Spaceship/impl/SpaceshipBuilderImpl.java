package it.unibo.AstroParty.model.Spaceship.impl;

import java.util.Collection;
import java.util.stream.Collectors;

import it.unibo.AstroParty.core.impl.PlayerId;
import it.unibo.AstroParty.model.Spaceship.api.SpaceshipBuilder;
import it.unibo.AstroParty.model.api.GameState;
import it.unibo.AstroParty.model.api.Spaceship;

/**
 * 
 * @author Alessandro Coli
 * a concrete implementation of {@link SpaceshipBuilder}
 */
public class SpaceshipBuilderImpl implements SpaceshipBuilder {
	
	private double speed;
	private int maxBullets;
	private long time;
	private boolean shield;
	
	public SpaceshipBuilderImpl(){
										//TODO usa un file .yml per salvare i default
		this.setSpeed(2);
		this.setStartingShield(false);
		this.setRechargeTime(3);
		this.setMaxBullets(3);		
	}

	@Override
	public void setSpeed(double speed) {
		this.speed = speed;
	}

	@Override
	public void setMaxBullets(int maxBullets) {

		this.maxBullets = maxBullets;
	}

	@Override
	public void setRechargeTime(long time) {

		this.time = time ;
	}

	@Override
	public void setStartingShield(boolean enable) {

		this.shield = enable;
	}
	@Override
	public Collection<Spaceship> create(GameState world, Collection<PlayerId> playerIds) {
		
		return playerIds.stream()
				.distinct() 	// controlla se ci sono due nomi o GameId uguali
				.map( id -> new SpaceshipImpl(world,speed , maxBullets, shield, id, time))
				.collect( Collectors.toSet());
	}

}
