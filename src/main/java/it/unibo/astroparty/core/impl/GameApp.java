package it.unibo.astroparty.core.impl;

import it.unibo.astroparty.core.api.GameView;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Extension of Application (from JavaFX), initialize the view.
 */
public class GameApp extends Application {

    /**
     * the window size that have to be used for the current screen.
     */
    public static final double WINDOW_SIZE;

    // the percentage of the screen that the window should cover.
    private static final double WINDOW_PERC = 0.75;

    // gets the sizes of the current screen and takes the shorter as window size
    static {
        final Rectangle2D bounds = Screen.getPrimary().getBounds();
        WINDOW_SIZE = Math.min(bounds.getHeight(), bounds.getWidth()) * WINDOW_PERC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(final Stage primaryStage) throws Exception {
        // stage settings
        primaryStage.setTitle("AstroParty");
        primaryStage.getIcons().add(new Image(ClassLoader.getSystemResource("sprites/icon.png").toString()));
        primaryStage.setResizable(false);

        final GameView view = new GameViewImpl(primaryStage);
        view.renderScene(view.getSceneFactory().createMain());
        primaryStage.sizeToScene();
    }
}
