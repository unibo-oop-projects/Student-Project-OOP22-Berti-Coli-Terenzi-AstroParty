package it.unibo.AstroParty.input.impl;

import it.unibo.AstroParty.input.api.GameId;
import it.unibo.AstroParty.input.api.InputControl;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

/**
 * 
 * a {@link EventHandler} for {@link KeyEvent} to be added to the graphic that translates the KeyEvents to commands.
 */
public class KeyboardEventsHandler implements EventHandler<KeyEvent> {

	private final InputControl control;

	/**
	 * @param control the controller in witch the input has to be added.
	 */
	public KeyboardEventsHandler(final InputControl control){
		this.control = control;
	}
	/**
	 * connects the keys with the respective actions.
	 * @param event: what key is been pressed.
	 */
	@Override
	public void handle(final KeyEvent event) {

		if(event.getEventType() == KeyEvent.KEY_PRESSED) {

			switch( event.getCode() ) {

			case Q:
				control.startTurn(GameId.Player1);
				break;

			case A:
				control.shoot(GameId.Player1);
				break;

			case P:
				control.startTurn(GameId.Player2);
				break;

			case L:
				control.shoot(GameId.Player2);
				break;

			case V:
				control.startTurn(GameId.Player3);
				break;

			case C:
				control.shoot(GameId.Player3);
				break;

			case UP:
				control.startTurn(GameId.Player4);
				break;

			case LEFT:
				control.shoot(GameId.Player4);
				break;

			default:
				break;
		}
	}else if (event.getEventType() == KeyEvent.KEY_RELEASED) {

		switch(event.getCode()) {

		case Q:
			control.stopTurn(GameId.Player1);
			break;

		case P:
			control.stopTurn(GameId.Player2);
			break;

		case V:
			control.stopTurn(GameId.Player3);
			break;

		case UP:
			control.stopTurn(GameId.Player4);
			break;

		default:
			break;
		}
		}
	}
}
