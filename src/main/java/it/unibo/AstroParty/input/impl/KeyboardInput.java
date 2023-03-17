package it.unibo.AstroParty.input.impl;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import it.unibo.AstroParty.input.api.GameId;

/**
 * 
 * @author Alessandro Coli
 * a {@link InputReader} for commands from keyboard
 */
public class KeyboardInput implements KeyListener {
	
	private final InputCommandFactory controller ;
	
	public KeyboardInput(InputCommandFactory controller) {
		this.controller = controller;	
		
	}
	
	@Override
	/**
	 * the possible actions to be performed depending on which key is pressed
	 */
	public void keyPressed(KeyEvent key ) {
		
		switch ( key.getExtendedKeyCode() ) {
		case KeyEvent.VK_Q:
			 controller.startTurn( GameId.Player1 );
			break;
		case KeyEvent.VK_A:
			controller.shoot( GameId.Player1 );
			break;
		case KeyEvent.VK_X:
			controller.startTurn( GameId.Player2 );
			break;
		case KeyEvent.VK_C:
			controller.shoot( GameId.Player2 );
			break;
		case KeyEvent.VK_P:
			controller.startTurn( GameId.Player3 );
			break;
		case KeyEvent.VK_L:
			controller.shoot( GameId.Player3 );
			break;
		case KeyEvent.VK_N:
			controller.startTurn( GameId.Player4 );
			break;
		case KeyEvent.VK_M:
			controller.shoot( GameId.Player4 );
			break;
	}
	}

	@Override
	/**
	 * the possible actions to be performed depending on which key is relesed
	 */
	public void keyReleased(KeyEvent key ) {
		
		switch ( key.getExtendedKeyCode() ) {
		case KeyEvent.VK_Q:
			controller.stopTurn( GameId.Player1 );
		case KeyEvent.VK_X:
			controller.stopTurn( GameId.Player2 );
			break;
		case KeyEvent.VK_P:
			controller.stopTurn( GameId.Player3 );
			break;
		case KeyEvent.VK_N:
			controller.stopTurn( GameId.Player4 );
			break;
		}
	}
	@Override
	public void keyTyped(KeyEvent key ) {
	}
}
