package it.unibo.AstroParty.ui.impl;

import it.unibo.AstroParty.core.api.View;
import it.unibo.AstroParty.ui.api.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class TutorialController implements Controller {

    @FXML
    private Button back;

    private View view;

    public TutorialController(View view) {
        this.view = view;
    }

    /**
     * event handler for "BACK" {@link Button}
     * @param event
     */
    @FXML
    public void backOnClick(ActionEvent event) {
        try {
            this.view.switchScene(view.getSceneFactory().createMain());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
