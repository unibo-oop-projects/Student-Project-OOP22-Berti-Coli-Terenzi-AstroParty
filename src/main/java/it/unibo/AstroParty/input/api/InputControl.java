package it.unibo.AstroParty.input.api;

import it.unibo.AstroParty.common.Pair;
import it.unibo.AstroParty.input.impl.InputCommand;

/** 
 * @author Alessandro Coli
 *	
 *	a controller used to signal to the model the input signals
 */
public interface InputControl {
	
	/**
	 * stop propagating input signals
	 */
	public void stop();
	
	/**
	 * start propagating input signals
	 */
	public void start();
	
	/**
	 * compute all the input events in queue
	 */
	public void compute();

	/**
	 * adds a startTurn command to the queue
	 * @param player1: the id of the |player that executed the command
	 */
	public void startTurn(GameId player1);

	/**
	 * adds a shoot command to the queue
	 * @param player1: the id of the |player that executed the command
	 */
	public void shoot(GameId player1);

	/**
	 * adds a stopTurn command to the queue
	 * @param player1: the id of the |player that executed the command
	 */
	public void stopTurn(GameId player1);
}
