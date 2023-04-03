package it.unibo.AstroParty.model.api;

import java.util.Date;

/**
 * 
 * @author Alessandro Coli
 * 
 * a PowerUp inside AstroParty
 */
public interface PowerUp extends Entity {

	static final double RELATIVE_SIZE = 1.5;
	static final double SPEED_MODIFIER = 1.3;
	static final double DURATION = 5000;
    static final long DOUBLESHOT_DELAY = 55;

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
     * @return true if it is an offensive power up
     */
    boolean isOffensive();

	/**
	 * {@inheritDoc}
	 */
	@Override
    CircleHitBox getHitBox();
}
