package it.unibo.AstroParty.graphics.api;

import java.util.Collection;

import javafx.scene.shape.Rectangle;

public interface GameScene {
	
    public void renderAll( Collection<GraphicEntity> world);
    
}
