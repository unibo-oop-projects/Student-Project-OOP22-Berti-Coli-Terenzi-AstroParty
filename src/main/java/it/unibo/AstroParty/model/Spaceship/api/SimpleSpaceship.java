package it.unibo.AstroParty.model.Spaceship.api;

import it.unibo.AstroParty.model.api.HitBox;
import it.unibo.AstroParty.model.api.Spaceship;

public abstract class SimpleSpaceship implements Spaceship {
	
	private double speed;
	private final int maxBullets;
	private final String playerId;
	private final double bulletRegenTime;
	private final HitBox hitbox;
	
	
	private boolean shield;
	
	public SimpleSpaceship(double speed, int maxBullets, boolean startingShield, String playerId, double bulletRegenTime, HitBox hitbox){
		this.shield = startingShield;
		this.maxBullets = maxBullets;
		this.playerId = playerId;
		this.speed = speed;
		this.bulletRegenTime = bulletRegenTime;
		this.shield=startingShield;
		this.hitbox=hitbox;
	}
	
	abstract public void makeImmortal() ;
	abstract public void makeMmortal() ;
	abstract public void newShield() ;
	abstract public void upgradeSpeed() ;
	abstract public void normalSpeed() ;
}
