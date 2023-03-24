package it.unibo.AstroParty.ui.impl;

import it.unibo.AstroParty.core.api.View;
import it.unibo.AstroParty.ui.api.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainPageController implements Controller {

    @FXML
    private Button play;

    @FXML
    private Button tutorial;

    private View app;

    public MainPageController(View app) {
        this.app = app;
    }

    /**
     * event handler for "PLAY" {@link Button}
     * @param event
     */
    @FXML
    public void playOnClick(ActionEvent event) {
        try {
            this.app.switchScene(app.getSceneFactory().createSettings());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * event handler for "TUTORIAL" {@link Button}
     * @param event
     */
    @FXML
    public void tutorialOnClick(ActionEvent event) {
        try {
            this.app.switchScene(app.getSceneFactory().createTutorial());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



