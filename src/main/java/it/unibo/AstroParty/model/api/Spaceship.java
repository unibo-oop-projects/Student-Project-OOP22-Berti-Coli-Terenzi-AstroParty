package it.unibo.AstroParty.model.api;

import it.unibo.AstroParty.core.impl.PlayerId;

/**
 * 
 * @author Alessandro Coli
 *	
 * a basic spaceship in AstroParty
 */
public interface Spaceship extends Entity {

	static final double relativeSize = 2.0;
	static final double rotationSpeed = .15;
	
	/**
	 * 
	 * @return the {@link PlayerId} of this spaceship
	 */
	public PlayerId getId();
	
	/**
	 * reset the position of the spaceship before of the update
	 */
    public void resetPosition();
    
    /**
     * equips a power up to the spaceship
     * @param pUp:  {@link PowerUp} to be equipped
     * @return true if it can be picked up
     */
    public boolean equipPowerUp(PowerUp pUp);
    
    /**
     * shoot a {@link Projectile}
     */
    public void shoot();
    
    /**
     * start turning
     */
    public void startTurn();
    
    /**
     * @return the rotation angle 
     */
    public double getAngle();
    
    /**
     * stop turning
     */
    public void stopTurn();

    @Override
    public CircleHitBox getHitBox();
}
