package it.unibo.AstroParty.model.api;

import it.unibo.AstroParty.common.Position;

public interface Spaceship extends Entity {

	static final double relativeSize = 3.0;
	static final double rotationSpeed = 2.0;
	
	
    public void setPosition(Position pos);
    public boolean equipPowerUp(PowerUp pUp);
    public void shoot();
    public void startTurn();
    public void stopTurn();
}