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
    private final static List<Resolution> RESOLUTION_CHOICES = List.of(Resolution.values());

    private final List<TextField> textAreas;
    private final GameScene gameScene;

    private boolean obstacle, powerUp;

    @FXML private TextField nameP1;
    @FXML private TextField nameP2;
    @FXML private TextField nameP3;
    @FXML private TextField nameP4;
    @FXML private CheckBox obstacleSelection;
    @FXML private CheckBox powerUpSelection;
    @FXML private ChoiceBox<Integer> roundSelection;
    @FXML private ChoiceBox<String> resolutionSelection;
    @FXML private Button start;
    @FXML private Button back;

    public SettingsController(GameScene gameScene) {
        this.gameScene = gameScene;
        this.textAreas = List.of(nameP1, nameP2, nameP3, nameP4);
        this.obstacle = false;
        this.powerUp = false;

    }

    /**
     * manages the click on "START" button
     */
    public void startOnClick() {
        List<String> players = textAreas.stream()
                .map(t -> t.getText())
                .filter(n -> n != BLANK && n != STARTING_MESSAGE)
                .collect(Collectors.toList());
        // TODO: send the player list and all the other settings to the GameEngine
    }

    /**
     * manage the click on "BACK" button
     */
    public void backOnClick() {
        gameScene.renderMainPage();
    }

    /**
     * manage the click on the obstacle selection checkbox
     */
    public void obstacleSelOnClick() {
       obstacle = !obstacle;
    }

    /**
     * manage the click on the power-up selection checkbox
     */
    public void powerUpSelOnClick() {
       powerUp = !powerUp;
    }

    /**
     * event listener of the resolution selection {@link ChoiceBox}
     * @param event
     */
    public void resolutionSelOnAction(ActionEvent event) {
        String selected = resolutionSelection.getValue();
        Resolution res = RESOLUTION_CHOICES.stream()
                .filter(t -> t.getName() == selected)
                .findAny()
                .get();
        setResoultion(res.getHeight(), res.getWidth());
        
    }

    private void setResoultion(double height, double width) {
        //TODO: set the new resolution
    }

    public void initialize() {
        textAreas.forEach(t -> t.setText(STARTING_MESSAGE));
        roundSelection.getItems().addAll(ROUND_CHOICES);
        resolutionSelection.getItems().addAll(RESOLUTION_CHOICES.stream().map(r -> r.getName()).toList());
        resolutionSelection.setOnAction(this::resolutionSelOnAction);
    }

}
