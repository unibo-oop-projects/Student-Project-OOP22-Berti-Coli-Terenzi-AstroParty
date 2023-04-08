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
     * @throws IOException could be launched while loading the file .fxml
     */
    Scene createMain() throws IOException;

    /**
     * @return a new tutorial scene
     * @throws IOException could be launched while loading the file .fxml
     */
    Scene createTutorial() throws IOException;

    /**
     * @return a new settings scene
     * @throws IOException could be launched while loading the file .fxml
     */
    Scene createSettings() throws IOException;

    /**
     * @param inputControl 
     * @return a new game scene
     */
    Scene createGame(InputControl inputControl);

    /**
     * @param scores a list with the number of victories of each player
     * @param rounds the number of rounds that a player have to win for winning the game
     * @return a new scoreboard scene
     * @throws IOException could be launched while loading the file .fxml
     */
    Scene createScoreboard(List<Integer> scores, int rounds) throws IOException;

    /**
     * @return a new game over scene
     * @throws IOException could be launched while loading the file .fxml
     */
    Scene createOver(String winnerPlayer) throws IOException;
}
