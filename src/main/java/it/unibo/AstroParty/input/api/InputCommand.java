package it.unibo.AstroParty.input.api;

import java.util.Optional;

import it.unibo.AstroParty.model.api.Spaceship;
/**
 * 
 * rappresents any command that can be given as input to the spaceship,
 * inside contains the action to be performed and the gameId of the spaceship.
 */
public interface InputCommand {
    /**
     * execute the command on the spaceship given, after checking it's the correct one.
     * @param spaceship the spaceship on wich to compute the action.
     */
    void compute(Optional<Spaceship> spaceship);

    /**
     * @return the gameId of the spaceship that this action has to be performed on.
     */
    GameId getID();
}
