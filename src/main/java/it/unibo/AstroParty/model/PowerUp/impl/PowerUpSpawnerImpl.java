package it.unibo.AstroParty.model.PowerUp.impl;

import java.util.Collection;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

import it.unibo.AstroParty.common.Position;
import it.unibo.AstroParty.model.PowerUp.api.PowerUpFactory;
import it.unibo.AstroParty.model.api.EntityType;
import it.unibo.AstroParty.model.api.GameState;
import it.unibo.AstroParty.model.api.PowerUpSpawner;

/**
 * 
 * @author Alessandro Coli
 * concrete implementation of {@link PowerUpSpawner}
 */
public class PowerUpSpawnerImpl implements PowerUpSpawner {
	
	private final Collection<EntityType> possiblePowerUpTypes;
	private final long SpawnDelay;
	private GameState world;
	private PowerUpFactory pUPfactory= new PowerUpFactoryImpl();
	
	private Timer timer = new Timer();
	
	PowerUpSpawnerImpl(Collection<EntityType> possiblePowerUpTypes, long spawnDelay){
		this.possiblePowerUpTypes = possiblePowerUpTypes;
		this.SpawnDelay = spawnDelay;
	}
	
	public void start(GameState world) {
		
		this.world=world;
		this.timer.scheduleAtFixedRate( new TimerTask() {
			@Override
			public void run() {
				generate();
			}
		}, 2*SpawnDelay, SpawnDelay);
	}
	@Override
	public void stop() {
		timer.cancel();
	}
	
	/**
	 * creates and adds a new powerUp to the world
	 */
	private void generate() {
		
		this.world.addPowerUp( this.pUPfactory.createPowerUp( this.generateType() , this.generatePos() ) );
	}
	
	/**
	 * generate the type of the new Power Up between the active ones in the world
	 * @return the {@link PowerUpTypes}
	 */
	private EntityType generateType() {
		
		int rand = new Random().nextInt( this.possiblePowerUpTypes.size() );
		var it = this.possiblePowerUpTypes.iterator();
		
		for ( int i = 0 ; i < rand ; i++) {
			it.next();							//non controllo perche' ho preso un num minore di size quindi deve esserci qualcosa;
		}
		
		return it.next();
	}

	/**
	 * generates a possible {@link Position} for the new PowerUp
	 * @return the position
	 */
	private Position generatePos() {
		
		Position pos;
		Random rand = new Random();
		
		do {
			
			pos = new Position( rand.nextDouble( GameState.width ) , rand.nextDouble( GameState.height ) );
			
		} while ( this.world.getEntities().stream()
				.map( entity -> entity.getPosition() )
				.collect(Collectors.toSet())
				.contains(pos) );
		
		return pos;
	}

	

}
