package it.unibo.AstroParty.graphics.impl;

import java.util.List;
import java.util.stream.Collectors;

import it.unibo.AstroParty.graphics.api.GameScene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class SettingsController {

    private final static String BLANK = "";
    private final static String STARTING_MESSAGE = "Write here your name";
    private final static List<Integer> ROUND_CHOICES = List.of(1, 2, 3);

    private final List<TextField> textAreas;
    private final GameScene scene;

    private boolean obstacle, powerUp;

    @FXML private TextField nameP1, nameP2, nameP3, nameP4;
    @FXML private CheckBox obstacleSelection, powerUpSelection;
    @FXML private ChoiceBox<Integer> roundSelection;
    @FXML private Button start, back;

    public SettingsController(GameScene scene) {
        this.scene = scene;
        this.obstacle = false;
        this.powerUp = false;
        this.textAreas = List.of(nameP1, nameP2, nameP3, nameP4);
    }

    /**
     * event handler for "START" {@link Button}
     * @param event
     */
    @FXML
    public void startOnClick(ActionEvent event) {
        List<String> players = textAreas.stream()
                .map(t -> t.getText())
                .filter(n -> n != BLANK && n != STARTING_MESSAGE)
                .collect(Collectors.toList());
        int round = roundSelection.getValue();
        // TODO: send the player list and all the other settings to the GameEngine
    }

    /**
     * event handler for "BACK" {@link Button}
     * @param event
     */
    @FXML
    public void backOnClick(ActionEvent event) {
        scene.renderMainPage();
    }

    /**
     * event handler for the obstacle {@link CheckBox}
     * @param event
     */
    @FXML
    public void obstacleSelOnClick(ActionEvent event) {
       obstacle = !obstacle;
    }

    /**
     * event handler for the power-up {@link CheckBox}
     * @param event
     */
    @FXML
    public void powerUpSelOnClick(ActionEvent event) {
       powerUp = !powerUp;
    }

    public void initialize() {
        textAreas.forEach(t -> t.setText(STARTING_MESSAGE));
        roundSelection.getItems().addAll(ROUND_CHOICES);
    }

}
