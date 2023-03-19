package it.unibo.AstroParty.graphics.impl;

import it.unibo.AstroParty.graphics.api.GameScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class TutorialController {

    private final GameScene scene;

    @FXML
    private Button back;

    public TutorialController(GameScene scene) {
        this.scene = scene;
    }

    /**
     * event handler for "BACK" {@link Button}
     * @param event
     */
    @FXML
    public void backOnClick(ActionEvent event) {
        scene.renderMainPage();
    }
}
