package it.unibo.astroparty.input.api;

import java.util.Collection;

import it.unibo.astroparty.game.spaceship.api.Spaceship;

/**
 * a controller used to signal to the model the input signals.
 */
public interface InputControl {

    /**
     * stop propagating input signals.
     */
    void stop();

    /**
     * start propagating input signals.
     */
    void start();

    /**
     * compute all the input events in queue.
     * @param spaceships all the {@link Spaceship} alive
     */
    void computeAll(Collection<Spaceship> spaceships);

    /**
     * adds a startTurn command to the queue.
     * 
     * @param player1 the id of the player that executed the command.
     */
    void startTurn(GameId player1);

    /**
     * adds a shoot command to the queue.
     * 
     * @param player1 the id of the player that executed the command.
     */
    void shoot(GameId player1);

    /**
     * adds a stopTurn command to the queue.
     * 
     * @param player1 : the id of the player that executed the command.
     */
    void stopTurn(GameId player1);
}
