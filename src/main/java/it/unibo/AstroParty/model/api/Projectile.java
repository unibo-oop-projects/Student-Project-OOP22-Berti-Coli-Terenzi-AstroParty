package it.unibo.AstroParty.model.api;

import it.unibo.AstroParty.common.Direction;
import it.unibo.AstroParty.common.Position;

public interface Projectile extends Entity {
    
    @Override
    public CircleHitBox getHitBox();
    
    public boolean create(Position SpaceshipPos, Direction SpaceshipDir);
}
