package it.unibo.AstroParty.graphics.api;

import java.util.Collection;

/**
 * 
 * the graphic scene while inside a match 
 *
 */
public interface GameScene {
	
	/**
	 * draws all the {@link GraphicEntity} at the right spots
	 * @param world
	 */
    public void renderAll( Collection<GraphicEntity> world);
    
}
