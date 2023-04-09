package it.unibo.astroparty.game.projectile.api;

import it.unibo.astroparty.game.Entity;
import it.unibo.astroparty.game.hitbox.api.CircleHitBox;

/**
 * interface for projectiles with the following methods.
 * @author dario
 *
 */
public interface Projectile extends Entity {
    /**
     * radius of the {@link Projectile}.
     */
    double RADIUS = 1.3;

    /**
     * {@inheritDoc}}
     */
    @Override
    CircleHitBox getHitBox();
}
