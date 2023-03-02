package it.unibo.AstroParty.model.api;

import it.unibo.AstroParty.common.Position;

public interface Spaceship extends Entity {
    public void setPosition(Position pos);
    public boolean equipPowerUp(PowerUp pUp);
    public void shoot();
    public void startTurn();
    public void stopTurn();
}
