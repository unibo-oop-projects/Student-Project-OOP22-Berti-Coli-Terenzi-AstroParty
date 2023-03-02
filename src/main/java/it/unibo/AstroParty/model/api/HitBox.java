package it.unibo.AstroParty.model.api;

import it.unibo.AstroParty.common.Position;

public interface HitBox {
    public boolean isHitted(Position pos, double radius);
}
