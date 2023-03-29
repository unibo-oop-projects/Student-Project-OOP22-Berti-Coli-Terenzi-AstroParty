package it.unibo.AstroParty.ui.api;

import java.util.List;

import it.unibo.AstroParty.input.api.InputControl;
import javafx.scene.Scene;

public interface SceneFactory {
    
    /**
     * @return a new main-page scene
     * @throws Exception
     */
    public Scene createMain() throws Exception;

    /**
     * @return a new tutorial scene
     * @throws Exception
     */
    public Scene createTutorial() throws Exception;

    /**
     * @return a new settings scene
     * @throws Exception
     */
    public Scene createSettings() throws Exception;

    /**
     * @param inputControl 
     * @return a new game scene
     * @throws Exception
     */
    public Scene createGame(InputControl inputControl) throws Exception;

    /**
     * @param scores a list with the number of victories of each player
     * @param rounds the number of rounds that a player have to win for winning the game
     * @return a new scoreboard scene
     * @throws Exception
     */
    public Scene createScoreboard(List<Integer> scores, int rounds) throws Exception;

    /**
     * @return a new game over scene
     * @throws Exception
     */
    public Scene createOver() throws Exception;
}
