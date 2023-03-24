package it.unibo.AstroParty.core.api;

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
}
