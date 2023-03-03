package it.unibo.AstroParty.model.PowerUp.impl;

import java.util.Collection;
import java.util.Random;
import java.util.stream.Collectors;

import it.unibo.AstroParty.common.Position;
import it.unibo.AstroParty.model.PowerUp.PowerUpTypes;
import it.unibo.AstroParty.model.PowerUp.api.PowerUpFactory;
import it.unibo.AstroParty.model.api.GameState;
import it.unibo.AstroParty.model.api.PowerUpSpawner;

public class PowerUpSpawnerImpl implements PowerUpSpawner {
	
	private final static double epsilon = 0.000001;
	
	private final Collection<PowerUpTypes> possiblePowerUpTypes;
	private final double SpawnDelay;
	private GameState world;
	private PowerUpFactory pUPfacrtory;
	
	PowerUpSpawnerImpl(Collection<PowerUpTypes> possiblePowerUpTypes, double spawnDelay){
		this.possiblePowerUpTypes = possiblePowerUpTypes;
		this.SpawnDelay = spawnDelay;
	}
	
	public void start(GameState world) {
		
		this.world=world;
	}
	@Override
	public void stop() {
												// TODO implementa timer
	}
	
	protected void generate() {
		
		this.world.addPowerUp( this.pUPfacrtory.createPowerUp( this.generateType() , this.generatePos() ) );
											//TODO fai partire un nuovo timer
	}

	private PowerUpTypes generateType() {
		
		int rand = new Random().nextInt( this.possiblePowerUpTypes.size() );
		var it = this.possiblePowerUpTypes.iterator();
		
		for ( int i = 0 ; i < rand ; i++) {
			it.next();							//non controllo perche' ho preso un num minore di size quindi deve esserci qualcosa;
		}
		
		return it.next();
	}

	private Position generatePos() {
		
		Position pos;
		Random rand = new Random();
		
		do {
			
			pos = new Position( rand.nextDouble( GameState.length ) , rand.nextDouble( GameState.height ) );
			
		} while ( this.world.getWorld().stream()
				.map( entity -> entity.getPosition() )
				.collect(Collectors.toSet())
				.contains(pos) );
		
		return pos;
	}

	

}
