package it.unibo.AstroParty.core.api;

import java.util.Collection;

import it.unibo.AstroParty.model.api.Entity;

public interface GameApplication {
    public void update(Collection<Entity> entities);
    public void renderMainPage();
    public void renderTutorial();
    public void renderSettings();
    public void renderScoreboard();
    public void renderGameOver();
}
