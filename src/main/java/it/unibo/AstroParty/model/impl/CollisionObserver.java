package it.unibo.AstroParty.model.impl;

import java.util.LinkedList;
import java.util.List;

import it.unibo.AstroParty.model.api.Event;
import it.unibo.AstroParty.model.api.Observer;
import it.unibo.AstroParty.model.api.GameState;

/**
 * simple implementation of {@EventOberserver} 
 */
public class CollisionObserver implements Observer {

    private final List<Event> eventQueue;

    public CollisionObserver() {
        eventQueue = new LinkedList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notify(Event event) {
        eventQueue.add(event);
    }

    /**
     * 
     * @param state of the game world
     */
    public void manageEvents(GameState state) {
        for (Event event : eventQueue) {
            event.manage(state);
        }
        eventQueue.clear();
    }
    
}
