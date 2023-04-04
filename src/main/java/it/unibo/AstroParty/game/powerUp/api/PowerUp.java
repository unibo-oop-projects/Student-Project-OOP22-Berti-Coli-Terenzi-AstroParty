package it.unibo.astroparty.game.powerUp.api;

import it.unibo.astroparty.game.api.Entity;
import it.unibo.astroparty.game.hitbox.api.CircleHitBox;
import it.unibo.astroparty.game.spaceship.api.Spaceship;

/**
 * 
 * 
 * a PowerUp inside AstroParty.
 */
public interface PowerUp extends Entity {

    /** size of a PowerUp relative to the map. */
    double RELATIVE_SIZE = 2.5;

    /** mofifier for the UpgradedSpeed. */
    double SPEED_MODIFIER = 1.3;

    /** the duration of the effect for most PowerUPs in milliseconds. */
    double DURATION = 5000;

    /** the delay between the first and the second shot. */
    long DOUBLESHOT_DELAY = 55;

    /** the maximun number of powerUps to be on the screen at the same time. */
    int MAX_ON_SCREEN = 5;

    /**
    * use the powerUp on the spaceship that has {@link #pickUp(Spaceship)} this power up.
    */
    void use();

    /**
     * inform this power up that is been picked up.
     * @param owner the spaceship that picked it up.
     * @return true if it has been pickedUp.
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
