package it.unibo.astroparty.input.impl;
import java.util.Optional;
import java.util.function.Consumer;

import it.unibo.astroparty.game.spaceship.api.Spaceship;
import it.unibo.astroparty.input.api.GameId;
import it.unibo.astroparty.input.api.InputCommand;

/**
 * 
 * this class rappresents any command that can be given as input to the spaceship,
 * inside contains the action to be performed and the gameId of the spaceship.
 */
public class InputCommandImpl implements InputCommand {

    private final GameId gameID;
    private final Consumer<Spaceship> action;
    /**
     * 
     * @param gameID of the spaceship on wich to compute the action.
     * @param action the action to be performed on the spaceship.
     */
    InputCommandImpl(final GameId gameID, final Consumer<Spaceship> action) {
        this.gameID = gameID;
        this.action = action;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void compute(final Optional<Spaceship> spaceship) {
        if (spaceship.isPresent() && spaceship.get().getId().getGameId().equals(this.gameID)) {
            action.accept(spaceship.get());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameId getID() {
        return this.gameID;
    }
}
