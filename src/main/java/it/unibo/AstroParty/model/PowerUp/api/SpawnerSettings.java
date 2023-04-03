package it.unibo.AstroParty.model.PowerUp.api;

import it.unibo.AstroParty.model.api.PowerUpSpawner;

/**
 * 
 * a builder for {@link PowerUpSpawner }.
 */
public interface SpawnerSettings {
	//uso una variable statica in quanto non reputo necessario usare un file config per un solo parametro
	long BASIC_SPAWN_DELAY = 2000;	

	/**
	 * when the game starts the setting of  {@link PowerUpSpawner } cannot be changhed anymore so it is created.
	 * @return the {@link PowerUpSpawner } for the game.
	 */
	PowerUpSpawner startGame();

	/**
	 * set the time between spawns.
	 * @param timeInterval in milliseconds.
	 */
	void setSpawnDelay(long timeInterval);

	/**
	 * enable the DoubleShot PowerUp.
	 * @param enable true for enable, false for disable.
	 */
	void enableDoubleShot(boolean enable);

	/**
	 * enable the TemporaryImmortality PowerUp.
	 * @param enable true for enable, false for disable.
	 */
	void enableTemporaryImmortality(boolean enable);

	/**
	 * enable the UpgradedSpeed PowerUp
	 * @param enable true for enable, false for disable.
	 */
	void enableUpgradedSpeed(boolean enable);

	/**
	 * enable the shield PowerUp.
	 * @param enable true for enable, false for disable.
	 */
	void enableShield(boolean enable);

	/**
	 * enable all the power ups.
	 */
	void enableAll();

	/**
	 * disable all the power ups.
	 */
	void disableAll();
	
}
