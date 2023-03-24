package it.unibo.AstroParty.ui.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import it.unibo.AstroParty.common.Pair;
import it.unibo.AstroParty.core.api.View;
import it.unibo.AstroParty.input.api.GameId;
import it.unibo.AstroParty.ui.api.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class SettingsController implements Controller {

    private final static String STARTING_MESSAGE = "il tuo nome qui";
    private final static int MIN_PLAYERS = 2;
    private final static List<Integer> ROUND_CHOICES = List.of(1, 2, 3);

    private View app;
    private List<TextField> nameFields;
    private List<GameId> ids;

    @FXML
    private TextField nameP1, nameP2, nameP3, nameP4;

    @FXML
    private CheckBox obstacleSelection, powerUpSelection;

    @FXML
    private ChoiceBox<Integer> roundSelection;

    @FXML
    private Button start, back;

    public SettingsController(View app) {
        this.app = app;
    }

    /**
     * event handler for "START" {@link Button}
     * @param event
     */
    @FXML
    public void startOnClick(ActionEvent event) {

        List<Pair<GameId,String>> players = IntStream.range(0,3)
                .boxed()
                .map(i -> new Pair<GameId,String>(ids.get(i),nameFields.get(i).getText()))
                .filter(p -> !p.getY().isBlank())
                .collect(Collectors.toList());

        // check if the game can start
        if (players.size() < MIN_PLAYERS) {
            nameFields.stream()
                    .forEach(t -> t.setStyle("-fx-border-color: " + (t.getText().isBlank() ? "red" : "black")));
        } else {
            int rounds = roundSelection.getValue();
            boolean obstacle = obstacleSelection.isSelected();
            boolean powerUp = powerUpSelection.isSelected();
            /* 
            System.out.println("Players: " + players.toString());
            System.out.println("Rounds: " + rounds
                    + " Obstacle: " + (obstacle ? "yes" : "no")
                    + " PowerUp: " + (powerUp ? "yes" : "no"));
            */
        }
        
        // TODO: send the player list and all the other settings to the GameEngine
    }

    /**
     * event handler for "BACK" {@link Button}
     * @param event
     */
    @FXML
    public void backOnClick(ActionEvent event) {
        try {
            this.app.switchScene(app.getSceneFactory().createMain());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initialize() {
        ids = List.of(GameId.values());
        nameFields = List.of(nameP1, nameP2, nameP3, nameP4);
        nameFields.stream().forEach(t -> t.setPromptText(STARTING_MESSAGE));
        roundSelection.getItems().addAll(ROUND_CHOICES);
        roundSelection.setValue(ROUND_CHOICES.get(0));
    }

}

