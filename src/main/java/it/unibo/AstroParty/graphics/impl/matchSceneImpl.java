package it.unibo.AstroParty.graphics.impl;

import it.unibo.AstroParty.common.Position;
import it.unibo.AstroParty.graphics.api.matchScene;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class matchSceneImpl extends Scene implements matchScene {
	
	public matchSceneImpl(Parent root) {
		super(root);
	}

	public void addToScene(Position pos ,double size, double angle, String path) {
		
		Rectangle entity = new Rectangle(pos.getX(), pos.getY(), size, size );
		entity.setRotate( angle );
		Image img = new Image(path);
		entity.setFill(new ImagePattern(img));

	}

}
