package it.unibo.AstroParty.graphics.api;

import java.util.Collection;

import it.unibo.AstroParty.model.api.Entity;

public interface GameScene {
    public void update(Collection<Entity> entities);
    public void renderMainPage();
    public void renderScoreboard();
    public void renderGameOver();
}
