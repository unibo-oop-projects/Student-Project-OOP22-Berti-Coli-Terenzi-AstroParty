package it.unibo.AstroParty.input.impl;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import it.unibo.AstroParty.input.api.GameId;
import it.unibo.AstroParty.input.api.InputControl;
import it.unibo.AstroParty.model.api.Spaceship;

/**
 * 
 * @author Alessandro Coli
 * a concrete {@link InputControl} to signal the right spaceship the input commands at the right time
 */
public class InputControlImpl implements InputControl {
	
	private final Collection<Spaceship> spaceships;
	private final List<InputCommand> commands = new LinkedList<>();
	private boolean read;
	
	public InputControlImpl(Collection<Spaceship> spaceships){
		this.spaceships = spaceships;
	}
	@Override
	public void stop() {
		this.read = false;
	}
	
	public void start() {
		this.read = true;
	}

	@Override
	public void compute() {
		for( InputCommand event : commands) {
			event.compute( this.spaceships.stream()
						.filter( s -> s.getId().getGameId().equals( event.getID() ))
						.findAny() );
		}
		this.commands.clear();
	}

	private void addEvent(InputCommand action) {

		if( this.read ) {
			this.commands.add(action);
		}
	}
	/**
	 * @param player: the GameId of the spaceship that has to shoot
	 */
	public void shoot(GameId player) {
		this.addEvent(new InputCommand( player, s -> s.shoot()));
	}
	
	/**
	 * @param player: the GameId of the spaceship that has to start turning
	 */
	public void startTurn(GameId player) {
		this.addEvent(new InputCommand( player, s -> s.startTurn()));
	}
	
	/**
	 * @param player: the GameId of the spaceship that has to stop turning
	 */
	public void stopTurn(GameId player) {
		this.addEvent(new InputCommand( player, s -> s.stopTurn()));
	}


}
