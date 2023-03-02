package it.unibo.AstroParty.model.Spaceship.api;

import java.util.Collection;

import it.unibo.AstroParty.model.api.Spaceship;

public interface SpaceshipBuilder {
	
	public void setSpeed(double speed);
	public void setMaxBullets(int maxBullets);
	public void setRechargeTime(double time);
	public void setStartingShield(boolean enable);
	
	public Collection<Spaceship> create(Collection<String> playerId);
	
}
