package it.unibo.AstroParty.graphics.api;

import it.unibo.AstroParty.graphics.impl.GraphicEntityImpl;

public interface GameScene {
	
    public void drawEntity( GraphicEntityImpl entity, matchScene view );
}
