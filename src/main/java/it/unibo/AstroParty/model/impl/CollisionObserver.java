package it.unibo.AstroParty.model.impl;

import java.util.LinkedList;
import java.util.List;

import it.unibo.AstroParty.model.api.Event;
import it.unibo.AstroParty.model.api.Observer;
import it.unibo.AstroParty.model.api.GameState;

/**
 * EventObserver implementation for the game collisions.
 */
public class CollisionObserver implements Observer {

    private final List<Event> eventQueue;

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
     * 
     * @param state of the game world
     */
    public void manageEvents(final GameState state) {
        for (final Event event : eventQueue) {
            event.manage(state);
        }
        eventQueue.clear();
    }
}
