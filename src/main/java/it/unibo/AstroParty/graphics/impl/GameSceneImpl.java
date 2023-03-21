package it.unibo.AstroParty.graphics.impl;

import java.util.Collection;
import java.util.HashSet;

import it.unibo.AstroParty.graphics.api.GameScene;
import it.unibo.AstroParty.graphics.api.GraphicEntity;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class GameSceneImpl implements GameScene{

    private ImgPaths pathFinder = new ImgPaths();

    @Override
    public Collection<Rectangle> renderAll(Collection<GraphicEntity> world) {
        HashSet<Rectangle> set= new HashSet<>();
        world.forEach( e ->  set.add( this.paint( e ) ) );
        return set;
    }

    private Rectangle paint(GraphicEntity  entity) {

		Rectangle view = new Rectangle(entity.getPosition().getX(), entity.getPosition().getY(), entity.getSize(), entity.getSize() );
		view.setRotate( entity.getAngle() );
		Image img = new Image( this.pathFinder.getImg( entity ) );
		view.setFill(new ImagePattern(img));

		return view;
	}

}
