package it.unibo.astroparty.ui.impl;

import javafx.scene.control.TextField;

import it.unibo.astroparty.core.api.GameView;
import it.unibo.astroparty.ui.api.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class OverController implements Controller {
	
	@FXML
    private Button restartGame;
	
	@FXML
	private TextField t1,t2,t3,t4;
	
	
	private final GameView view;
	private String winner;
	
	public OverController(final GameView gameView, final String winnerPlayer) {
		this.view = gameView;
		this.winner = winnerPlayer;
	}
	
	
	
	public void initialize() {
		switch(this.winner) {
        case "Player1":
        	this.t1.setText("WINNER!");
        	break;
        case "Player2":
        	this.t2.setText("WINNER!");
        	break;
        case "Player3":
        	this.t3.setText("WINNER!");
        	break;
        case "Player4":
        	this.t4.setText("WINNER!");
        	break;
        default:
        	throw new UnsupportedOperationException();
        	
        }
    }
	
	@FXML
    public void nextOnClick() {
        this.view.nextRound();
    }
}
