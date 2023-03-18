package it.unibo.AstroParty.graphics.impl;

import it.unibo.AstroParty.graphics.api.GameScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainPageController {

    private final GameScene scene;

    @FXML
    private Button play;

    @FXML
    private Button tutorial;

    public MainPageController(GameScene scene) {
        this.scene = scene;
    }

    /**
     * event handler for "PLAY" {@link Button}
     * @param event
     */
    @FXML
    public void playOnClick(ActionEvent event) {
        scene.renderSettings();
    }

    /**
     * event handler for "TUTORIAL" {@link Button}
     * @param event
     */
    @FXML
    public void tutorialOnClick(ActionEvent event) {
        scene.renderTutorial();
    }
}



