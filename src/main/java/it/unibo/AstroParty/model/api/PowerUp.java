package it.unibo.AstroParty.model.api;

import it.unibo.AstroParty.model.PowerUp.PowerUpTypes;

public interface PowerUp extends Entity {
	
	static final double relativaSize = 1.7;
	static final double speedModifier = 1.5;
	
    public void use();
    public boolean pickUp( Spaceship owner );
    public boolean isOffensive();
    public PowerUpTypes getType();

    @Override
    public RectangleHitBox getHitBox();
}
