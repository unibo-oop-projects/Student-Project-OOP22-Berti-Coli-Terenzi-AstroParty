package it.unibo.AstroParty.graphics.impl;

import java.util.Collection;
import it.unibo.AstroParty.graphics.api.GameScene;
import it.unibo.AstroParty.graphics.api.GraphicEntity;
import it.unibo.AstroParty.input.api.InputControl;
import it.unibo.AstroParty.input.impl.KeyboardEventsHandler;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameSceneImpl extends Pane implements GameScene{

    private ImgPaths pathFinder = new ImgPaths();
    
    public GameSceneImpl( ImgPaths finder, InputControl keyController ) {
    	
    	 this.pathFinder = finder;
    	 
    	 KeyboardEventsHandler keyHandler =new KeyboardEventsHandler( keyController );
    	 this.setOnKeyPressed( keyHandler );
    	 this.setOnKeyReleased( keyHandler );
    }

    @Override
    public void renderAll(Collection<GraphicEntity> world) {
    	
    	this.getChildren().clear();
    	
        world.forEach( e ->  this.getChildren().add( this.paint( e ) ) );
        
    }

    private Rectangle paint(GraphicEntity  entity) {

		Rectangle view = new Rectangle(entity.getPosition().getX(), entity.getPosition().getY(), entity.getSize(), entity.getSize() ); // size e' relativo, va quindi moltiplicato per la dim assoluta
		view.setRotate( entity.getAngle() );
		// Image img = new Image( this.pathFinder.getImg( entity ) );
		// view.setFill(new ImagePattern(img));
		view.setFill( Color.AQUAMARINE );

		return view;
	}

}
