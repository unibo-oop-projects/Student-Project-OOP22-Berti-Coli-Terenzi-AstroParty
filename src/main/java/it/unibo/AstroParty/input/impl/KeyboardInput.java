package it.unibo.AstroParty.input.impl;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import it.unibo.AstroParty.common.Pair;
import it.unibo.AstroParty.core.api.GameId;
import it.unibo.AstroParty.input.api.InputControl;
import it.unibo.AstroParty.input.api.InputReader;

public class KeyboardInput implements InputReader, KeyListener {
	
	private final InputControl controller ;
	private boolean read = false;
	
	public KeyboardInput(InputControl controller) {
		this.controller = controller;	
		
	}

	@Override
	public void keyPressed(KeyEvent key ) {
		
		switch ( key.getExtendedKeyCode() ) {
		case KeyEvent.VK_Q:
			this.addToQue( GameId.Player1, SpaceshipAction.StartTurn );
			break;
		case KeyEvent.VK_A:
			this.addToQue( GameId.Player1, SpaceshipAction.Shoot );
			break;
		case KeyEvent.VK_X:
			this.addToQue( GameId.Player2, SpaceshipAction.StartTurn );
			break;
		case KeyEvent.VK_C:
			this.addToQue( GameId.Player2, SpaceshipAction.Shoot );
			break;
		case KeyEvent.VK_P:
			this.addToQue( GameId.Player3, SpaceshipAction.StartTurn );
			break;
		case KeyEvent.VK_L:
			this.addToQue( GameId.Player3, SpaceshipAction.Shoot );
			break;
		case KeyEvent.VK_N:
			this.addToQue( GameId.Player4, SpaceshipAction.StartTurn );
			break;
		case KeyEvent.VK_M:
			this.addToQue( GameId.Player4, SpaceshipAction.Shoot );
			break;
	}
	}

	@Override
	public void keyReleased(KeyEvent key ) {
		
		switch ( key.getExtendedKeyCode() ) {
		case KeyEvent.VK_Q:
			this.addToQue( GameId.Player1, SpaceshipAction.StopTurn );
			break;
		case KeyEvent.VK_X:
			this.addToQue( GameId.Player2, SpaceshipAction.StopTurn );
			break;
		case KeyEvent.VK_P:
			this.addToQue( GameId.Player3, SpaceshipAction.StopTurn );
			break;
		case KeyEvent.VK_N:
			this.addToQue( GameId.Player4, SpaceshipAction.StopTurn );
			break;
		}
	}

	@Override
	public void stop() {
		this.read = false;
	}
	
	public void start() {
		this.read = true;
	}
	
	private void addToQue(GameId player , SpaceshipAction action) {
		if( this.read ) controller.addEvent(  new Pair<>( player, action));
	}
	
	@Override
	public void keyTyped(KeyEvent key ) {
	}
}
