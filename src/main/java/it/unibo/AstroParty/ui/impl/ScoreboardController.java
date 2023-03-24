package it.unibo.AstroParty.ui.impl;

import it.unibo.AstroParty.core.api.View;
import it.unibo.AstroParty.ui.api.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;

public class ScoreboardController implements Controller {

    @FXML
    private ProgressBar progressP1, progressP2, progressP3, progressP4;

    @FXML
    private Button next;

    private View app;

    public ScoreboardController(View app) {
        this.app = app;
    }

    /**
     * event handler for "NEXT" {@link Button}
     * @param event
     */
    @FXML
    public void nextOnClick(ActionEvent event) {
        try {
            this.app.switchScene(app.getSceneFactory().createGame());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        //TODO progressbar for each player
    }
}

