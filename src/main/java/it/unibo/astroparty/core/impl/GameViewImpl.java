package it.unibo.astroparty.core.impl;

import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.astroparty.core.api.GameEngine;
import it.unibo.astroparty.core.api.GameView;
import it.unibo.astroparty.ui.api.SceneFactory;
import it.unibo.astroparty.ui.impl.SceneFactoryImpl;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * GameView implementation.
 */
public class GameViewImpl implements GameView {

    private final Stage stage;
    private final SceneFactory sceneFactory;
    private final GameEngine engine;

    /**
     * GameViewImpl constructor.
     * @param stage
     */
    @SuppressFBWarnings(
        value = {
            "EI_EXPOSE_REP2"
        },
        justification = "The view must store the stage in order to set the new scenes on it."
    )
    public GameViewImpl(final Stage stage) {
        this.stage = stage;
        this.sceneFactory = new SceneFactoryImpl(this);
        this.engine = new GameEngineImpl(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void renderScene(final Scene scene) {
        this.stage.setScene(scene);
        this.stage.show();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Scene getScene() {
        return stage.getScene();
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
        this.engine.init(players, obstacle, powerup, rounds);
        this.nextRound();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void nextRound() {
        this.engine.mainLoop();
    }
}
