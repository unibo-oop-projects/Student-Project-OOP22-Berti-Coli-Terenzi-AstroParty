package it.unibo.astroparty.ui.impl;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.unibo.astroparty.core.api.GameView;
import it.unibo.astroparty.ui.api.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Controller of the Tutorial scene.
 */
public class TutorialController implements Controller {

    private static final Logger LOGGER = Logger.getLogger("TutorialController");
    private final GameView view;

    @FXML
    private Button back;

    /**
     * Constructor for TutorialController.
     * @param view
     */
    public TutorialController(final GameView view) {
        this.view = view;
    }

    /**
     * event handler for "BACK" {@link Button}.
     * @param event
     */
    @FXML
    public void backOnClick(final ActionEvent event) {
        try {
            this.view.renderScene(view.getSceneFactory().createMain());
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error during loading of MainPage scene");
        }
    }

}
