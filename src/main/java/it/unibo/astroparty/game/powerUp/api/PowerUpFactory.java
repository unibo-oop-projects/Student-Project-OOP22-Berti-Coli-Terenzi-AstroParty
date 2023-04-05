package it.unibo.astroparty.game.powerUp.api;

import it.unibo.astroparty.common.Position;
import it.unibo.astroparty.game.EntityType;

/**
 * 
 * a factory for {@link PowerUp}.
 */
public interface PowerUpFactory {

    /**
     * create a new {@link PowerUp}.
     * @param type the type of the new power up.
     * @param pos the position inside the map.
     * @return the powerup.
    */
    PowerUp createPowerUp(EntityType type, Position pos);
}
