package it.unibo.AstroParty.graphics.impl;

import java.util.Collection;
import java.util.HashSet;

import it.unibo.AstroParty.graphics.api.GameScene;
import it.unibo.AstroParty.graphics.api.GraphicEntity;
import it.unibo.AstroParty.input.api.InputControl;
import it.unibo.AstroParty.input.impl.KeyboardEventsHandler;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameSceneImpl extends Scene implements GameScene{

    //private ImgPaths pathFinder = new ImgPaths();
    private Pane pane;
    
    public GameSceneImpl( InputControl keyController ) {
    	
    	super( new Pane() , 100,100);			
    	this.pane = (Pane) this.getRoot();
    	 KeyboardEventsHandler keyHandler =new KeyboardEventsHandler( keyController );
    	 this.setOnKeyPressed( keyHandler );
    	 this.setOnKeyReleased( keyHandler );
    }

    @Override
    public void renderAll(Collection<GraphicEntity> world) { 

        Platform.runLater( new Runnable(){ 

            public void run(){ 

                Collection<Rectangle> set = new HashSet<>();;
				world.forEach( e -> set.add( paint( e ) ) ); 

                pane.getChildren().clear(); 
                pane.getChildren().addAll(set);
            } 
        } );

    } 

    private Rectangle paint(GraphicEntity  entity) {

		Rectangle view = new Rectangle(entity.getPosition().getX(), entity.getPosition().getY(), entity.getLength() , entity.getHeight() ); // size e' relativo, va quindi moltiplicato per la dim assoluta
		view.setRotate( entity.getAngle() );
		// Image img = new Image( this.pathFinder.getImg( entity ) );
		// view.setFill(new ImagePattern(img));
		view.setFill( Color.AQUAMARINE );

		return view;
	}

}