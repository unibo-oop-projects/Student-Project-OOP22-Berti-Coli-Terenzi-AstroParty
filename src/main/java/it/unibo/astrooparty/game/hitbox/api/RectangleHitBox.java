package it.unibo.astroparty.game.hitbox.api;

import it.unibo.astroparty.common.Position;

/**
 * Interface modeling a rectangular hitbox.
 */
public interface RectangleHitBox extends HitBox {

    /**
     * @return the up-left corner {@link Position}
     */
    Position getcornerUL();

    /**
     * @return the down-right corner {@link Position}
     */
    Position getcornerDR();
}
