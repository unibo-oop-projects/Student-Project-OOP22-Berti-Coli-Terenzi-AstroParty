package it.unibo.AstroParty.graphics.impl;

import it.unibo.AstroParty.core.api.GameApplication;
import it.unibo.AstroParty.graphics.api.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;

public class ScoreboardController implements Controller {

    @FXML
    private ProgressBar progressP1, progressP2, progressP3, progressP4;

    @FXML
    private Button next;

    private GameApplication app;

    public ScoreboardController(GameApplication app) {
        this.app = app;
    }

    /**
     * event handler for "BACK" {@link Button}
     * @param event
     */
    @FXML
    public void backOnClick(ActionEvent event) {
        this.app.game();
    }

    @FXML
    void initialize() {
        //TODO progressbar for each player
    }
}

