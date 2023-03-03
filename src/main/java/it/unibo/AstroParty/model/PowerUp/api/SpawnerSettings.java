package it.unibo.AstroParty.model.PowerUp.api;

public interface SpawnerSettings {
	
	public PowerUpSpawner startGame();
	
	public void setSpawnDelay(double timeInterval);
	public void enableDoubleShot(boolean enable);
	public void enableTemporaryImmortality(boolean enable);
	public void enableUpgradedSpeed(boolean enable);
	public void enableLaserShot(boolean enable);
	public void enableLaserSword(boolean enable);
	public void enableShield(boolean enable);
	
}
