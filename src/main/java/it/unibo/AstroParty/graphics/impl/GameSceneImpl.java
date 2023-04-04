package it.unibo.astroparty.graphics.impl;

import java.util.Collection;
import java.util.HashSet;

import it.unibo.astroparty.core.impl.GameApp;
import it.unibo.astroparty.graphics.api.GameScene;
import it.unibo.astroparty.graphics.api.GraphicEntity;
import it.unibo.astroparty.input.api.InputControl;
import it.unibo.astroparty.input.impl.KeyboardEventsHandler;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * 
 * a javaFx implementation of {@link GameScene}.
 */
public class GameSceneImpl extends Scene implements GameScene {

    private final ImgNames namefinder = new ImgNames();
    private final Pane pane;
    private final double scale = GameApp.WINDOW_SIZE / 100;

    /**
     * @param keyController the controller for the input to be added to the scene.
     */
    public GameSceneImpl(final InputControl keyController) {
        super(new Pane(), GameApp.WINDOW_SIZE, GameApp.WINDOW_SIZE);
        this.pane = (Pane) this.getRoot();
        final KeyboardEventsHandler keyHandler = new KeyboardEventsHandler(keyController);
        this.setOnKeyPressed(keyHandler);
        this.setOnKeyReleased(keyHandler);
        this.pane.setId("pane");
        this.getStylesheets().addAll(ClassLoader.getSystemResource("css/game.css").toExternalForm());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void renderAll(final Collection<GraphicEntity> world) { 
        Platform.runLater(new Runnable() { 

            @Override
            public void run() {

                final Collection<ImageView> set = new HashSet<>();
                world.forEach(e -> set.add(paint(e))); 
                pane.getChildren().clear(); 
                pane.getChildren().addAll(set);
            }
        });

    } 

    /**
     * 
     * @param entity that has to bre drwan
     * @return the graphic rappresentation of the entity passed as parameter.
     */
    private ImageView paint(final GraphicEntity  entity) {
        final ImageView view = new ImageView();
        view.setId(this.namefinder.getName(entity));
        view.setX(entity.getPosition().getX() * scale);
        view.setY(entity.getPosition().getY() * scale);
        view.setFitWidth(entity.getLength() * scale);
        view.setFitHeight(entity.getHeight() * scale);
        view.setRotate(entity.getAngle());
        return view;
    }
}
