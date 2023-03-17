package it.unibo.AstroParty.model.api;

import it.unibo.AstroParty.model.PowerUp.PowerUpTypes;

/**
 * 
 * @author Alessandro Coli
 * 
 * a PowerUp inside AstroParty
 */
public interface PowerUp extends Entity {
	
	static final double relativaSize = 1.7;
	static final double speedModifier = 1.5;
	static final double Duration = 2;
	
	/**
	 * use the powerUp on the spaceship that has {@link #pickUp(Spaceship)} this power up
	 */
    public void use();
    
    /**
     * inform this power up that is been picked up
     * @param owner: the spaceship that picked it up
     * @return 
     */
    public boolean pickUp( Spaceship owner );
    
    /**
     * @return true if it is an offensive power up
     */
    public boolean isOffensive();
    
    /**
     * @return the {@link PowerUpTypes} of this power up
     */
    public PowerUpTypes getType();

    @Override
    public CircleHitBox getHitBox();
}
