package it.unibo.AstroParty.core.api;

import java.util.List;

import it.unibo.AstroParty.ui.api.SceneFactory;
import javafx.scene.Scene;

/**
 * Interface that models the view of the game.
 */
public interface View {

    /**
     * @param scene to set on stage
     */
    void switchScene(Scene scene);

    /**
     * @return the currente scene
     */
    Scene getScene();

    /**
     * @return the scene factory
     */
    SceneFactory getSceneFactory();

    /**
     * Starts the GameEngine.
     * @param players a list of the players name
     * @param obstacle true if obstacles will be in the game
     * @param powerup true if power-ups will be in the game
     * @param rounds the number of rounds a player has to win to win the game
     */
    void start(List<String> players, boolean obstacle, boolean powerup, int rounds);

    /**
     * Creates a new round.
     */
    void nextRound();
}
