package it.unibo.AstroParty.model.PowerUp.api;

import it.unibo.AstroParty.model.api.PowerUpSpawner;

/**
 * 
 * a builder for {@link PowerUpSpawner }
 */
public interface SpawnerSettings {
	static final long basic_spawn_delay = 2000;	//uso una variable statica in quanto non reputo necessario usare un file config per un solo parametro
	
	/**
	 * when the game starts the setting of  {@link PowerUpSpawner } cannot be changhed anymore so it is created
	 * @return the {@link PowerUpSpawner } for the game
	 */
	public PowerUpSpawner startGame();
	
	/**
	 * set the time between spawns
	 * @param time in milliseconds
	 */
	public void setSpawnDelay(long timeInterval);
	
	/**
	 * enable the DoubleShot PowerUp
	 * @param enable: true for enable, false for disable
	 */
	public void enableDoubleShot(boolean enable);
	
	/**
	 * enable the TemporaryImmortality PowerUp
	 * @param enable: true for enable, false for disable
	 */
	public void enableTemporaryImmortality(boolean enable);
	
	/**
	 * enable the UpgradedSpeed PowerUp
	 * @param enable: true for enable, false for disable
	 */
	public void enableUpgradedSpeed(boolean enable);
	
	/**
	 * enable the shield PowerUp
	 * @param enable: true for enable, false for disable
	 */
	public void enableShield(boolean enable);
	
	/**
	 * enable all the power ups
	 */
	public void enableAll();
	
	/**
	 * disable all the power ups
	 */
	public void disableAll();
	
}
