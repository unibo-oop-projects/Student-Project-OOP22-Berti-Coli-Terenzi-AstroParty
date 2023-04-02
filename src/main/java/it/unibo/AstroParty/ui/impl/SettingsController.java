package it.unibo.AstroParty.ui.impl;

import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

import it.unibo.AstroParty.core.api.View;
import it.unibo.AstroParty.ui.api.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

/**
 * Controller of the Settings scene.
 */
public class SettingsController implements Controller {

    private static final String STARTING_MESSAGE = "il tuo nome qui";
    private static final int MIN_PLAYERS = 2;
    private static final List<Integer> ROUND_CHOICES = List.of(1, 2, 3);

    @FXML
    private TextField nameP1, nameP2, nameP3, nameP4;

    @FXML
    private CheckBox obstacleSelection, powerUpSelection;

    @FXML
    private ChoiceBox<Integer> roundSelection;

    @FXML
    private Button start, back;

    private final View view;
    private final List<TextField> nameFields = List.of(nameP1, nameP2, nameP3, nameP4);;

    /**
     * Constructor for SettingsController.
     * @param view
     */
    public SettingsController(final View view) {
        this.view = view;
    }

    /**
     * event handler for "START" {@link Button}.
     * @param event
     */
    @FXML
    public void startOnClick(final ActionEvent event) {
        final List<String> players = nameFields.stream()
                .map(t -> t.getText())
                .filter(n -> !n.isBlank())
                .toList();
        if (players.size() < MIN_PLAYERS) {
            showAlert("Non ci sono abbastanza giocatori per iniziare il gioco");
        } else if (IntStream.range(0, players.size()).anyMatch(i -> nameFields.get(i).getText().isBlank())) {
            showAlert("Inserisci i nomi dei giocatori in fila (es: non può esserci un player 3 senza prima il player 2)");
        } else if (players.size() != players.stream().distinct().count()) {
            showAlert("Sono presenti due player con lo stesso nome");
        } else {
            view.start(players,
                    obstacleSelection.isSelected(),
                    powerUpSelection.isSelected(),
                    roundSelection.getValue());
        }
    }

    private void showAlert(final String message) {
        final Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("AstroParty: errore");
        alert.setHeaderText("Errore nell'inserimento dei players");
        alert.setContentText(message);
        alert.showAndWait();
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

    /**
     * Called to initialize a controller after its root element has been completely processed.
     */
    public void initialize() {
        nameFields.stream().forEach(t -> t.setPromptText(STARTING_MESSAGE));
        roundSelection.getItems().addAll(ROUND_CHOICES);
        roundSelection.setValue(ROUND_CHOICES.get(0));
    }

}
