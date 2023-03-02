package it.unibo.AstroParty.model.PowerUp.api;

import java.util.Collection;

import it.unibo.AstroParty.model.PowerUp.PowerUpTypes;
import it.unibo.AstroParty.model.api.GameState;

public abstract class PowerUpSpawner {
	
	private final Collection<PowerUpTypes> possiblePowerUpTypes;
	private final double SpawnDelay;
	private GameState world;
	
	PowerUpSpawner(Collection<PowerUpTypes> possiblePowerUpTypes, GameState world, double spawnDelay){
		this.possiblePowerUpTypes = possiblePowerUpTypes;
		this.SpawnDelay = spawnDelay;
		this.world = world;
	}
	
	public PowerUpSpawner start(GameState world) {
		this.world=world;
		return this;
	}
	
	public abstract void stop();
	protected abstract void generateNewPowerUp();
}
