package it.unibo.AstroParty.input.api;

import it.unibo.AstroParty.common.Pair;
import it.unibo.AstroParty.core.api.GameId;
import it.unibo.AstroParty.input.impl.SpaceshipAction;

public interface InputControl {
    public void stop();
    public void compute();
    public void addEvent(Pair<GameId,SpaceshipAction> action);
}
