package it.unibo.AstroParty.graphics.impl;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainPageController {

    private final Stage stage;

    @FXML private Button play;
    @FXML private Button tutorial;

    public MainPageController(Stage stage) {
        this.stage = stage;
    }

    /**
     * manages the click on "PLAY" button
     */
    public void playOnClick() {
        stage.setScene(null); //TODO: renderizzare la pagina successiva (selezione players)
        stage.show();
    }

    /**
     * manages the click on "HOW TO PLAY" button
     */
    public void tutorialOnClick() {
        stage.setScene(null); //TODO: tutorial page
        stage.show();
    }

    public void initialize() {

    }
}



