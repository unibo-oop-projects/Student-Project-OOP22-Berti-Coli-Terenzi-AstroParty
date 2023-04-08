package it.unibo.astroparty.ui.api;

import java.io.IOException;
import java.util.List;

import it.unibo.astroparty.input.api.InputControl;
import javafx.scene.Scene;

/**
 * Factory interface from simple factory pattern that handles scene creation.
 */
public interface SceneFactory {

    /**
     * @return a new main-page scene
     * @throws IOException
     */
    Scene createMain() throws IOException;

    /**
     * @return a new tutorial scene
     * @throws IOException
     */
    Scene createTutorial() throws IOException;

    /**
     * @return a new settings scene
     * @throws IOException
     */
    Scene createSettings() throws IOException;

    /**
     * @param inputControl 
     * @return a new game scene
     * @throws IOException
     */
    Scene createGame(InputControl inputControl) throws IOException;

    /**
     * @param scores a list with the number of victories of each player
     * @param rounds the number of rounds that a player have to win for winning the game
     * @return a new scoreboard scene
     * @throws IOException
     */
    Scene createScoreboard(List<Integer> scores, int rounds) throws IOException;

    /**
     * @return a new game over scene
     * @throws IOException
     */
    Scene createOver(String winnerPlayer) throws IOException;
}