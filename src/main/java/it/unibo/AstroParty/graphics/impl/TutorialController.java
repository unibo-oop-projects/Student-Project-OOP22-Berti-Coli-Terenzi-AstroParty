package it.unibo.AstroParty.graphics.impl;

import it.unibo.AstroParty.core.api.GameApplication;
import it.unibo.AstroParty.graphics.api.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class TutorialController implements Controller {

    @FXML
    private Button back;

    private GameApplication app;

    public TutorialController(GameApplication app) {
        this.app = app;
    }

    /**
     * event handler for "BACK" {@link Button}
     * @param event
     */
    @FXML
    public void backOnClick(ActionEvent event) {
        this.app.mainPage();
    }

}
