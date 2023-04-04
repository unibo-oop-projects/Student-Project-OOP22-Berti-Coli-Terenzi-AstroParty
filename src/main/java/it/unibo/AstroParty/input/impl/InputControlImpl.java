package it.unibo.AstroParty.input.impl;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import it.unibo.AstroParty.input.api.GameId;
import it.unibo.AstroParty.input.api.InputControl;
import it.unibo.AstroParty.model.api.Spaceship;

/**
 * 
 * a concrete {@link InputControl} to signal the right spaceship the input commands at the right time.
 */
public class InputControlImpl implements InputControl {

    private final Collection<Spaceship> spaceships;
    private final List<InputCommand> commands = new LinkedList<>();
    private boolean read;

    /**
     * @param spaceships : the spaceships of the current match.
     */
    //TODO prendile ad ogni giro se riesci
    public InputControlImpl(final Collection<Spaceship> spaceships) {
        this.spaceships = spaceships;
    }

    /**
     *  {@inheritDoc}
     */
    @Override
    public void stop() {
        this.read = false;
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
    public void compute() {
        for (final InputCommand event : commands) {
            event.compute(this.spaceships.stream()
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
        this.addEvent(new InputCommand(player, s -> s.shoot()));
    }

    /**
     *  {@inheritDoc}
     */
    @Override
    public void startTurn(final GameId player) {
        this.addEvent(new InputCommand(player, s -> s.startTurn()));
    }

    /**
     *  {@inheritDoc}
     */
    @Override
    public void stopTurn(final GameId player) {
        this.addEvent(new InputCommand(player, s -> s.stopTurn()));
    }
}
