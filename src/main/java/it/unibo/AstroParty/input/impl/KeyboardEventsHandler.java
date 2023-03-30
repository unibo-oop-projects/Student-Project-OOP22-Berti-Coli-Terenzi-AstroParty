package it.unibo.AstroParty.input.impl;

import it.unibo.AstroParty.input.api.GameId;
import it.unibo.AstroParty.input.api.InputControl;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

/**
 * 
 * @author Alessandro Coli
 * 
 * a {@link EventHandler} for {@link KeyEvent} to be added to the graphic that translates the KeyEvents to commands
 */
public class KeyboardEventsHandler implements EventHandler<KeyEvent> {

	private final InputControl control ;
	
	public KeyboardEventsHandler( InputControl control ){
		this.control = control;
	}
	
	@Override
	public void handle(KeyEvent event) {
		
		if( event.getEventType() == KeyEvent.KEY_PRESSED ) {
			
			switch( event.getCode() ) {
			
			case Q:
				control.startTurn( GameId.Player1 );
				break;
				
			case A:
				control.shoot( GameId.Player1 );
				break;
			
			case P:
				control.startTurn( GameId.Player2 );
				break;
				
			case L:
				control.shoot( GameId.Player2 );
				break;
			
			case C:
				control.startTurn( GameId.Player3 );
				break;
				
			case V:
				control.shoot( GameId.Player3 );
				break;
			
			case UP:
				control.startTurn( GameId.Player4 );
				break;
				
			case LEFT:
				control.shoot( GameId.Player4 );
				break;
			default:
				break;
		}
	}else if( event.getEventType() == KeyEvent.KEY_RELEASED ) {
			
		switch( event.getCode() ) {
		
		case Q:
			control.stopTurn( GameId.Player1 );
			break;
			
		case P:
			control.stopTurn( GameId.Player2 );
			break;
		
		case C:
			control.stopTurn( GameId.Player3 );
			break;
		
		case UP:
			control.stopTurn( GameId.Player4 );
			break;
		default:
			break;
		}
		}
		
	}

}
