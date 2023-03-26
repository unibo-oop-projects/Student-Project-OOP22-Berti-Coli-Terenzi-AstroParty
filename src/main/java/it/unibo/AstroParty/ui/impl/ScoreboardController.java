package it.unibo.AstroParty.ui.impl;

import java.util.List;
import java.util.stream.IntStream;

import it.unibo.AstroParty.core.api.View;
import it.unibo.AstroParty.ui.api.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;

public class ScoreboardController implements Controller {

    @FXML
    private ProgressBar progressP1, progressP2, progressP3, progressP4;

    @FXML
    private Button next;

    private final View view;
    private final List<Integer> scores;
    private final int rounds;

    public ScoreboardController(View view, List<Integer> scores, int rounds) {
        this.view = view;
        this.scores = scores;
        this.rounds = rounds;
    }

    /**
     * event handler for "NEXT" {@link Button}
     * @param event
     */
    @FXML
    public void nextOnClick(ActionEvent event) {
        try {
            view.switchScene(view.getSceneFactory().createGame());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        List<ProgressBar> progress = List.of(progressP1, progressP2, progressP3, progressP4);
        IntStream.range(0, 3)
                .boxed()
                .forEach(i -> progress.get(i).setProgress(scores.get(i)/rounds));
    }
}

