package it.unibo.AstroParty.model.api;

import it.unibo.AstroParty.core.impl.PlayerId;

/**
 * 
 * a basic spaceship in AstroParty
 */
public interface Spaceship extends Entity {

    /** size of a PowerUp relative to the map. */
    double RELATIVE_SIZE = 3;

    /** the speed at which a SpaceShip rotates. */
    double ROTATION_SPEED = 0.25;

    /**
    * 
    * @return the {@link PlayerId} of this spaceship.
    */
    PlayerId getId();

    /**
     * reset the position of the spaceship before of the update.
     */
    void resetPosition();

    /**
     * equips a power up to the spaceship.
     * @param pUp the  {@link PowerUp} to be equipped.
     * @return true if it can be picked up.
     */
    boolean equipPowerUp(PowerUp pUp);

    /**
     * shoot a {@link Projectile}.
     */
    void shoot();

    /**
     * start turning.
     */
    void startTurn();

    /**
     * @return the rotation angle.
     */
    double getAngle();

    /**
     * stop turning.
     */
    void stopTurn();

    /**
    * {@inheritDoc}
    */
    @Override
    CircleHitBox getHitBox();
}
