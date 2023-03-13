package it.unibo.AstroParty.model.api;

/**
 * Rapresent the boundaries of an {@link Entity}
 */
public interface HitBox {

    /**
     * used to get to know if the current entity has been hitten by another
     * @param hBox the {@link HitBox} of the other {@link Entity}
     * @return true if the two entities have collied
     * @throws IllegalArgumentException if the collision with the hitbox given is not managed
     */
    public boolean isHittedBy(HitBox hBox) throws IllegalArgumentException;

}
