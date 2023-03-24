package it.unibo.AstroParty.ui.api;

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
     * @return a new game scene
     * @throws Exception
     */
    public Scene createGame() throws Exception;

    /**
     * @return a new scoreboard scene
     * @throws Exception
     */
    public Scene createScoreboard() throws Exception;

    /**
     * @return a new game over scene
     * @throws Exception
     */
    public Scene createOver() throws Exception;
}
