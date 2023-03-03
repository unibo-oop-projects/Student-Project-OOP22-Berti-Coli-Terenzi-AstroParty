package it.unibo.AstroParty.model.PowerUp.impl;

import java.util.EnumSet;
import it.unibo.AstroParty.model.PowerUp.PowerUpTypes;
import it.unibo.AstroParty.model.PowerUp.api.SpawnerSettings;
import it.unibo.AstroParty.model.api.PowerUpSpawner;

public class SpawnerSettingImpl implements SpawnerSettings {
	
	EnumSet<PowerUpTypes> possible = EnumSet.noneOf( PowerUpTypes.class );
	double spawnDelay;
	
	@Override
	public PowerUpSpawner startGame() {
		// TODO Auto-generated method stub
		return new PowerUpSpawnerImpl( this.possible , this.spawnDelay );
	}
	
	private void enable( PowerUpTypes type ) {
		
		this.possible.add(type);
	}
	
	private void disable( PowerUpTypes type ) {
		
		this.possible.remove(type);
	}

	@Override
	public void setSpawnDelay(double timeInterval) {

		this.spawnDelay = timeInterval;
	}

	@Override
	public void enableDoubleShot(boolean enable) {
		
		if( enable ) {
			this.enable(  PowerUpTypes.DOUBLESHOT );
		}else {
			this.disable(  PowerUpTypes.DOUBLESHOT );
		}
	}

	@Override
	public void enableTemporaryImmortality(boolean enable) {
		
		if( enable ) {
			this.enable(  PowerUpTypes.IMMORTALITY );
		}else {
			this.disable(  PowerUpTypes.IMMORTALITY );
		}
	}

	@Override
	public void enableUpgradedSpeed(boolean enable) {
		
		if( enable ) {
			this.enable(  PowerUpTypes.UPGRADEDSPEED );
		}else {
			this.disable(  PowerUpTypes.UPGRADEDSPEED );
		}
	}

	@Override
	public void enableLaserShot(boolean enable) {
		
		if( enable ) {
			this.enable(  PowerUpTypes.LASERSHOT );
		}else {
			this.disable(  PowerUpTypes.LASERSHOT );
		}
	}

	@Override
	public void enableLaserSword(boolean enable) {
		
		if( enable ) {
			this.enable(  PowerUpTypes.LASERSWORD );
		}else {
			this.disable(  PowerUpTypes.LASERSWORD );
		}
	}

	@Override
	public void enableShield(boolean enable) {
		
		if( enable ) {
			this.enable( PowerUpTypes.SHIELD );
		}else {
			this.disable(  PowerUpTypes.SHIELD );
		}
	}

	@Override
	public void enableAll() {

		possible = EnumSet.allOf( PowerUpTypes.class );
	}

	@Override
	public void DisableAll() {

		this.possible = EnumSet.noneOf( PowerUpTypes.class );
	}

}
