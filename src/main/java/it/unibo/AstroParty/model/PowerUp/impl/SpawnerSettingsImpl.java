package it.unibo.AstroParty.model.PowerUp.impl;

import java.util.EnumSet;

import it.unibo.AstroParty.model.PowerUp.api.SpawnerSettings;
import it.unibo.AstroParty.model.api.PowerUpSpawner;
import it.unibo.AstroParty.model.api.EntityType;

/** 
 * 
 * @author Alessandro Coli
 * 
 * a concrete implementation of {@link SpawnerSettings }
 */
public class SpawnerSettingsImpl implements SpawnerSettings {
	
	EnumSet<EntityType> possible = EnumSet.noneOf( EntityType.class );
	long spawnDelay;
	
	public SpawnerSettingsImpl(){
		this.DisableAll();
		this.spawnDelay = SpawnerSettings.basic_spawn_delay;
	}
	@Override
	public PowerUpSpawner startGame() {
		
		return new PowerUpSpawnerImpl( this.possible , this.spawnDelay );
	}
	
	private void enable( EntityType type ) {
		
		this.possible.add(type);
	}
	
	private void disable( EntityType type ) {
		
		this.possible.remove(type);
	}

	@Override
	public void setSpawnDelay(long timeInterval) {

		this.spawnDelay = timeInterval;
	}

	@Override
	public void enableDoubleShot(boolean enable) {
		
		if( enable ) {
			this.enable(  EntityType.DOUBLESHOT );
		}else {
			this.disable(  EntityType.DOUBLESHOT );
		}
	}

	@Override
	public void enableTemporaryImmortality(boolean enable) {
		
		if( enable ) {
			this.enable(  EntityType.IMMORTALITY );
		}else {
			this.disable(  EntityType.IMMORTALITY );
		}
	}

	@Override
	public void enableUpgradedSpeed(boolean enable) {
		
		if( enable ) {
			this.enable(  EntityType.UPGRADEDSPEED );
		}else {
			this.disable(  EntityType.UPGRADEDSPEED );
		}
	}

	@Override
	public void enableShield(boolean enable) {
		
		if( enable ) {
			this.enable( EntityType.SHIELD );
		}else {
			this.disable(  EntityType.SHIELD );
		}
	}

	@Override
	public void enableAll() {
		this.possible.addAll( EnumSet.allOf(EntityType.class) );
		this.possible.removeIf( e -> ! (e.getGeneralType() == EntityType.POWERUP ) );
		System.out.println("-- " + this.possible);
	}

	@Override
	public void DisableAll() {

		this.possible = EnumSet.noneOf( EntityType.class );
	}

}
