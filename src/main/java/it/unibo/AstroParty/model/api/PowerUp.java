package it.unibo.AstroParty.model.api;

/**
 * 
 * @author Alessandro Coli
 * 
 * a PowerUp inside AstroParty
 */
public interface PowerUp extends Entity {
	

	
	static final double relativeSize = 1.5;
	static final double speedModifier = 1.5;
	static final double Duration = 5000;
	
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
	 * {@inheritDoc}
	 */
	@Override
    public CircleHitBox getHitBox();
}
