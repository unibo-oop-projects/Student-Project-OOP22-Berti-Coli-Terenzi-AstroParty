package it.unibo.AstroParty.graphics.impl;

import java.util.List;
import java.util.stream.Collectors;

import it.unibo.AstroParty.core.api.GameApplication;
import it.unibo.AstroParty.graphics.api.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class SettingsController implements Controller {

    private final static String STARTING_MESSAGE = "your name here";
    private final static int MIN_PLAYERS = 2;
    private final static List<Integer> ROUND_CHOICES = List.of(1, 2, 3);

    private GameApplication app;
    private List<TextField> nameFields;

    @FXML
    private TextField nameP1, nameP2, nameP3, nameP4;

    @FXML
    private CheckBox obstacleSelection, powerUpSelection;

    @FXML
    private ChoiceBox<Integer> roundSelection;

    @FXML
    private Button start, back;

    public SettingsController(GameApplication app) {
        this.app = app;
    }

    /**
     * event handler for "START" {@link Button}
     * @param event
     */
    @FXML
    public void startOnClick(ActionEvent event) {
        List<String> players = nameFields.stream()
                .map(t -> t.getText())
                .filter(n -> !n.isBlank())
                .collect(Collectors.toList());
        if (players.size() < MIN_PLAYERS) {
            nameFields.stream().forEach(t -> t.setStyle("-fx-border-color: " + (t.getText().isBlank() ? "red" : "black")));
        }
        int rounds = roundSelection.getValue();
        boolean obstacle = obstacleSelection.isSelected();
        boolean powerUp = powerUpSelection.isSelected();
        
        System.out.println("Players: " + players.toString());
        System.out.println("Rounds: " + rounds
                + " Obstacle: " + (obstacle ? "yes" : "no")
                + " PowerUp: " + (powerUp ? "yes" : "no"));
        // TODO: send the player list and all the other settings to the GameEngine
    }

    /**
     * event handler for "BACK" {@link Button}
     * @param event
     */
    @FXML
    public void backOnClick(ActionEvent event) {
        this.app.mainPage();
    }

    public void initialize() {
        nameFields = List.of(nameP1, nameP2, nameP3, nameP4);
        nameFields.stream().forEach(t -> t.setPromptText(STARTING_MESSAGE));
        roundSelection.getItems().addAll(ROUND_CHOICES);
        roundSelection.setValue(ROUND_CHOICES.get(0));
    }

}
