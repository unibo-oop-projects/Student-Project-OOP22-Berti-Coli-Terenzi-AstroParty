package it.unibo.astroparty.input.impl;

import it.unibo.astroparty.input.api.GameId;
import it.unibo.astroparty.input.api.InputControl;
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
    public KeyboardEventsHandler(final InputControl control) {
        this.control = control;
    }
    /**
     * connects the keys with the respective actions.
     * @param event what key is been pressed.
     */
    @Override
    public void handle(final KeyEvent event) {

        if (event.getEventType().equals(KeyEvent.KEY_PRESSED)) {

            switch (event.getCode()) {

            case Q:
                control.startTurn(GameId.PLAYER1);
                break;

            case A:
                control.shoot(GameId.PLAYER1);
                break;

            case P:
                control.startTurn(GameId.PLAYER2);
                break;

            case L:
                control.shoot(GameId.PLAYER2);
                break;

            case V:
                control.startTurn(GameId.PLAYER3);
                break;

            case C:
                control.shoot(GameId.PLAYER3);
                break;

            case DOWN:
                control.startTurn(GameId.PLAYER4);
                break;

            case RIGHT:
                control.shoot(GameId.PLAYER4);
                break;

            default:
                break;
        }
    } else if (event.getEventType().equals(KeyEvent.KEY_RELEASED)) {

        switch (event.getCode()) {

        case Q:
            control.stopTurn(GameId.PLAYER1);
            break;

        case P:
            control.stopTurn(GameId.PLAYER2);
            break;

        case V:
            control.stopTurn(GameId.PLAYER3);
            break;

        case DOWN:
            control.stopTurn(GameId.PLAYER4);
            break;

        default:
            break;
        }
        }
    }
}
