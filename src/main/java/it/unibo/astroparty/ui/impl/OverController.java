package it.unibo.astroparty.ui.impl;

import javafx.scene.control.TextField;
import it.unibo.astroparty.core.api.GameView;
import it.unibo.astroparty.ui.api.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * class for the Game Over graphic interface at the end of a match.
 * @author dario
 *
 */
public class OverController implements Controller {

    @FXML
    private Button restartGame;

    @FXML
    private TextField t1, t2, t3, t4;

    private final GameView view;
    private final String winner;

    /**
     * Constructor of {@link OverController}.
     * @param gameView
     * @param winnerPlayer
     */
    public OverController(final GameView gameView, final String winnerPlayer) {
        this.view = gameView;
        this.winner = winnerPlayer;
    }

    /**
     * selects the winner from the players.
     */
    @FXML
    public void initialize() {
        final String winnerString = "WINNER!";
        switch (this.winner) {
        case "Player1":
            this.t1.setText(winnerString);
            break;
        case "Player2":
            this.t2.setText(winnerString);
            break;
        case "Player3":
            this.t3.setText(winnerString);
            break;
        case "Player4":
            this.t4.setText(winnerString);
            break;
        default:
            throw new UnsupportedOperationException();
        }
    }

    /**
     * called after pressing the "RESTART GAME" button.
     * it starts another match with the same settings chosen before 
     */
    @FXML
    public void nextOnClick() {
        this.view.nextRound();
    }
}
