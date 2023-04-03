package it.unibo.AstroParty.model.Spaceship.api;

import it.unibo.AstroParty.model.api.PowerUp;
import it.unibo.AstroParty.model.api.Spaceship;

/**
 *
 * an extension of {@link Spaceship} used by the {@link PowerUp}.
 */
public interface SimpleSpaceship extends Spaceship {

    /**
     * makes the ship immortal.
     */
    void makeImmortal();

    /**
     * makes the ship mortal.
     */
    void makeMortal();

    /**
     * gives the ship a shield that blocks one damage instance.
     */
    void newShield();

    /**
     * set the speed to max
     */
    void upgradeSpeed();

    /**
     * set the speed to normal.
     */
    void normalSpeed();

    /**
     * remove the power up after it's been used.
     * @param upgradedSpeed the powerUp to be removed.
     */
    void removePowerUp(PowerUp upgradedSpeed);
}
