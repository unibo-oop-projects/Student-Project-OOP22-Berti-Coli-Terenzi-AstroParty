package it.unibo.AstroParty.model.api;

/**
 * 
 * @author Alessandro Coli
 * 
 * a PowerUp inside AstroParty
 */
public interface PowerUp extends Entity {

	double RELATIVE_SIZE = 1.5;
	double SPEED_MODIFIER = 1.3;
	double DURATION = 5000;
    long DOUBLESHOT_DELAY = 55;

	/**
	 * use the powerUp on the spaceship that has {@link #pickUp(Spaceship)} this power up.
	 */
    void use();

    /**
     * inform this power up that is been picked up.
     * @param owner: the spaceship that picked it up.
     * @return 
     */
    boolean pickUp(Spaceship owner);

    /**
     * @return true if it is an offensive power up.
     */
    boolean isOffensive();

	/**
	 * {@inheritDoc}
	 */
	@Override
    CircleHitBox getHitBox();
}
