package it.unibo.AstroParty.core.api;

import java.util.List;

import it.unibo.AstroParty.ui.api.SceneFactory;
import javafx.scene.Scene;

public interface View {

    /**
     * @param scene to set on stage
     */
    public void switchScene(Scene scene);

    /**
     * @return the currente scene
     */
    public Scene getScene();

    /**
     * @return the scene factory
     */
    public SceneFactory getSceneFactory();
    
    /**
     * @param players a list of the players name
     * @param obstacle true if obstacles will be in the game
     * @param powerup true if power-ups will be in the game
     * @param rounds the number of rounds a player has to win to win the game
     */
    public void start(List<String> players, boolean obstacle, boolean powerup, int rounds);
}
