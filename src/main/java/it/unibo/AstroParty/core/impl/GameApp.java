package it.unibo.AstroParty.core.impl;

import it.unibo.AstroParty.core.api.View;
import it.unibo.AstroParty.ui.api.SceneFactory;
import it.unibo.AstroParty.ui.impl.SceneFactoryImpl;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class GameApp extends Application implements View {

    public static final int WINDOW_SIZE;
    private static final double WINDOW_PERC = 0.75;


    private Stage primaryStage;
    private SceneFactory sceneFactory;

    // gets the sizes of the current screen and takes the shorter as window size
    static {
        final Rectangle2D bounds = Screen.getPrimary().getBounds();
        WINDOW_SIZE = (int) (Math.min(bounds.getHeight(), bounds.getWidth()) * WINDOW_PERC);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;

        // set title and icon
        this.primaryStage.setTitle("AstroParty");
        this.primaryStage.getIcons().add(new Image(ClassLoader.getSystemResource("sprites/icon.png").toString()));
        
        // stage settings
        this.primaryStage.setWidth(WINDOW_SIZE);
        this.primaryStage.setHeight(WINDOW_SIZE);
        this.primaryStage.setResizable(false);
        this.primaryStage.setOnCloseRequest(s -> System.exit(0));

        // create SceneFactory
        sceneFactory = new SceneFactoryImpl(this);

        this.switchScene(sceneFactory.createMain());
    }

    /**
     * {@inheritDoc}
     */
    public void switchScene(final Scene scene) {
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * {@inheritDoc}
     */
    public Scene getScene() {
        return primaryStage.getScene();
    }

    /**
     * {@inheritDoc}
     */
    public SceneFactory getSceneFactory() {
        return sceneFactory;
    }
}
