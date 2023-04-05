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
                    startTurn(GameId.PLAYER1);
                    break;

                case P:
                    startTurn(GameId.PLAYER2);
                    break;

                case V:
                    startTurn(GameId.PLAYER3);
                    break;

                case DOWN:
                    startTurn(GameId.PLAYER4);
                    break;

                default:
            }
    } else if (event.getEventType().equals(KeyEvent.KEY_RELEASED)) {

        switch (event.getCode()) {

            case Q:
                stopTurn(GameId.PLAYER1);
                break;

            case P:
                stopTurn(GameId.PLAYER2);
                break;

            case V:
                stopTurn(GameId.PLAYER3);
                break;

            case DOWN:
                stopTurn(GameId.PLAYER4);
                break;

            default:
            }
        }
        switch (event.getCode()) {

            case W:
                shoot(GameId.PLAYER1);
                break;

            case L:
                shoot(GameId.PLAYER2);
                break;

            case C:
                shoot(GameId.PLAYER3);
                break;

            case RIGHT:
                shoot(GameId.PLAYER4);
                break;

            default:
        }
    }

    private void shoot(GameId id){
        control.shoot(id);
    }

    private void stopTurn(GameId id){
        control.stopTurn(id);
    }

    private void startTurn(GameId id){
        control.startTurn(id);
    }
}
