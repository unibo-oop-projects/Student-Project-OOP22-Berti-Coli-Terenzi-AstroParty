package it.unibo.AstroParty.input.impl;
import java.util.Optional;
import java.util.function.Consumer;

import it.unibo.AstroParty.input.api.GameId;
import it.unibo.AstroParty.model.api.Spaceship;

/**
 * 
 * @author alessandro.coli2
 * this class rappresents any command that can be given as input to the spaceship, inside contains the action to be performed and the gameId of the spaceship
 */
public class InputCommand {
	
	private final GameId gameID;
	private final Consumer<Spaceship> action;

	/**
	 * 
	 * @param gameID of the spaceship on wich to compute the action
	 * @param action the action to be performed on the spaceship
	 */
	InputCommand(GameId gameID, Consumer<Spaceship> action){
		this.gameID = gameID;
		this.action = action;		
	}
	
	/**
	 * execute the command on the spaceship given, after checking it's the correct one
	 * @param spaceship
	 */
	public void compute(Optional<Spaceship> spaceship) {
		if( spaceship.isPresent() && spaceship.get().getId().getGameId().equals( this.gameID )) {
			action.accept( spaceship.get() );
		}
	}

	/**
	 * @return the gameId of the spaceship that this action has to be performed on
	 */
	public GameId getID() {
		return this.gameID;
	}

}
