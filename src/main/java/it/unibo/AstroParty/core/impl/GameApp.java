package it.unibo.AstroParty.core.impl;

import java.util.List;

import it.unibo.AstroParty.core.api.GameEngine;
import it.unibo.AstroParty.core.api.View;
import it.unibo.AstroParty.ui.api.SceneFactory;
import it.unibo.AstroParty.ui.impl.SceneFactoryImpl;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * View implementation and extension of Application (from JavaFX).
 */
public class GameApp extends Application implements View {

    // the percentage of the screen that the window should cover.
    private static final double WINDOW_PERC = 0.75;

    /**
     * the window size that have to be used for the current screen
     */
    public static final int WINDOW_SIZE;

    private GameEngine engine;
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
    public void start(final Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.sceneFactory = new SceneFactoryImpl(this);

        // stage settings
        this.primaryStage.setTitle("AstroParty");
        this.primaryStage.getIcons().add(new Image(ClassLoader.getSystemResource("sprites/icon.png").toString()));
        this.primaryStage.setResizable(false);

        this.switchScene(sceneFactory.createMain());
        this.primaryStage.sizeToScene();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void switchScene(final Scene scene) {
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Scene getScene() {
        return primaryStage.getScene();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SceneFactory getSceneFactory() {
        return sceneFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(final List<String> players, final boolean obstacle, final boolean powerup, final int rounds) {
        this.engine = new GameEngineImpl(this, players, obstacle, powerup, rounds);
        this.nextRound();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void nextRound() {
        this.engine.init();
        this.engine.mainLoop();
    }
}

