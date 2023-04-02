package it.unibo.AstroParty.ui.impl;

import java.io.IOException;

import it.unibo.AstroParty.core.api.View;
import it.unibo.AstroParty.ui.api.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Controller of the Tutorial scene.
 */
public class TutorialController implements Controller {

    @FXML
    private Button back;

    private final View view;

    /**
     * Constructor for TutorialController.
     * @param view
     */
    public TutorialController(final View view) {
        this.view = view;
    }

    /**
     * event handler for "BACK" {@link Button}.
     * @param event
     */
    @FXML
    public void backOnClick(final ActionEvent event) {
        try {
            this.view.switchScene(view.getSceneFactory().createMain());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
