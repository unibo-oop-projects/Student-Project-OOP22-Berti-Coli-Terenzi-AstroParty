package it.unibo.AstroParty.model.api;

public interface Projectile extends Entity {
    
    @Override
    public CircleHitBox getHitBox();
}
