package it.unibo.AstroParty.model.api;

import it.unibo.AstroParty.common.Position;

public interface Entity {
    public HitBox getHitBox();
    public Position getPosition();
    public boolean hit();
    public void update(double time);
}
