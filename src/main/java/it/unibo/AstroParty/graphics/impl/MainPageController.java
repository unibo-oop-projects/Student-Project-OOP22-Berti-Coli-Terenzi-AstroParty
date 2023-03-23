package it.unibo.AstroParty.graphics.impl;

import it.unibo.AstroParty.core.api.GameApplication;
import it.unibo.AstroParty.graphics.api.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainPageController implements Controller {

    @FXML
    private Button play;

    @FXML
    private Button tutorial;

    private GameApplication app;

    public MainPageController(GameApplication app) {
        this.app = app;
    }

    /**
     * event handler for "PLAY" {@link Button}
     * @param event
     */
    @FXML
    public void playOnClick(ActionEvent event) {
        this.app.settings();
    }

    /**
     * event handler for "TUTORIAL" {@link Button}
     * @param event
     */
    @FXML
    public void tutorialOnClick(ActionEvent event) {
        this.app.tutorial();
    }
}



