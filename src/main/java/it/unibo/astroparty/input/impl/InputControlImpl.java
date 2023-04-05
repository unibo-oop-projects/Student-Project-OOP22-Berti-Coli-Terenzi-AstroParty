package it.unibo.astroparty.input.impl;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import it.unibo.astroparty.game.spaceship.api.Spaceship;
import it.unibo.astroparty.input.api.GameId;
import it.unibo.astroparty.input.api.InputCommand;
import it.unibo.astroparty.input.api.InputControl;

/**
 * 
 * a concrete {@link InputControl} to signal the right spaceship the input commands at the right time.
 */
public class InputControlImpl implements InputControl {

    private final List<InputCommand> commands = new LinkedList<>();
    private boolean read;

    /**
     *  {@inheritDoc}
     */
    @Override
    public void stop() {
        this.read = false;
        this.commands.clear();
    }

    /**
     *  {@inheritDoc}
     */
    @Override
    public void start() {
        this.read = true;
    }

    /**
     *  {@inheritDoc}
     */
    @Override
    public void computeAll(final Collection<Spaceship> spaceships) {
        for (final InputCommand event : commands) {
            event.compute(spaceships.stream()
                        .filter(s -> s.getId().getGameId().equals(event.getID()))
                        .findAny());
        }
        this.commands.clear();
    }

    /**
     *  adds an event to the queue of events.
     * @param action the action to be added.
     */
    private void addEvent(final InputCommand action) {

        if (this.read) {
            this.commands.add(action);
        }
    }

    /**
     *  {@inheritDoc}
     */
    @Override
    public void shoot(final GameId player) {
        this.addEvent(new InputCommandImpl(player, s -> s.shoot()));
    }

    /**
     *  {@inheritDoc}
     */
    @Override
    public void startTurn(final GameId player) {
        this.addEvent(new InputCommandImpl(player, s -> s.startTurn()));
    }

    /**
     *  {@inheritDoc}
     */
    @Override
    public void stopTurn(final GameId player) {
        this.addEvent(new InputCommandImpl(player, s -> s.stopTurn()));
    }
}
