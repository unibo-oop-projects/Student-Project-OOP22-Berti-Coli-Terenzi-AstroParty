package it.unibo.astroparty.game.logics.impl;

import java.util.LinkedList;
import java.util.Queue;

import it.unibo.astroparty.game.logics.api.Event;
import it.unibo.astroparty.game.logics.api.GameState;
import it.unibo.astroparty.game.logics.api.Observer;

/**
 * EventObserver implementation for the game collisions.
 */
public class CollisionObserver implements Observer {

    private final Queue<Event> eventQueue;

    /**
     * Constructor for CollisionObserver.
     */
    public CollisionObserver() {
        eventQueue = new LinkedList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notify(final Event event) {
        eventQueue.add(event);
    }

    /**
     * Manages all the events in the queue.
     * @param state of the game world
     */
    public void manageEvents(final GameState state) {
        while (!eventQueue.isEmpty()) {
            eventQueue.poll().manage(state);
        }
    }
}
