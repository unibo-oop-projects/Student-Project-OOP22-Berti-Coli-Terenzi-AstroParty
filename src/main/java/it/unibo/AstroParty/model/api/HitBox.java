package it.unibo.AstroParty.model.api;

import it.unibo.AstroParty.common.Position;

/**
 * Rapresent the boundaries of an Entity
 */
public interface HitBox {

    /**
     * used to get to know if the current Entity has been hitten by another
     * @param pos the position of the other Entity
     * @param radius the radius of the other Entity
     * @return true if the two entities have collied
     */
    public boolean isHitted(Position pos, double radius);
}
