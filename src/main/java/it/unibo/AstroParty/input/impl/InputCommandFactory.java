package it.unibo.AstroParty.input.impl;

import it.unibo.AstroParty.input.api.GameId;
import it.unibo.AstroParty.input.api.InputControl;

/**
 * 
 * @author alessandro.coli2
 * 
 * a simple factory used to add commans to the que inside {@link InputControl}
 */
public class InputCommandFactory {

	private InputControl que;

	/**
	 * @param inputControl the controller used to save the actions for this game
	 */
	public InputCommandFactory(InputControl inputControl) {
		this.que = inputControl;
	}
	
	/**
	 * @param player: the GameId of the spaceship that has to shoot
	 */
	public void shoot(GameId player) {
		que.addEvent(new InputCommand( player, s -> s.shoot()));
	}
	
	/**
	 * @param player: the GameId of the spaceship that has to start turning
	 */
	public void startTurn(GameId player) {
		que.addEvent(new InputCommand( player, s -> s.stopTurn()));
	}
	
	/**
	 * @param player: the GameId of the spaceship that has to stop turning
	 */
	public void stopTurn(GameId player) {
		que.addEvent(new InputCommand( player, s -> s.startTurn()));
	}


}
