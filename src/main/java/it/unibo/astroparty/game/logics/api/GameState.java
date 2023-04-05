package it.unibo.astroparty.game.logics.api;

import java.util.Collection;

import it.unibo.astroparty.game.Entity;
import it.unibo.astroparty.game.obstacle.api.Obstacle;
import it.unibo.astroparty.game.powerups.api.PowerUp;
import it.unibo.astroparty.game.projectile.api.Projectile;
import it.unibo.astroparty.game.spaceship.api.Spaceship;

/**
 * Interface that models the state of the game.
 */
public interface GameState {

    /*
     * The logic dimensions of the map are always the same, the effective sizes displayed on
     * the screen are elaborated by the graphical part using the window sizes.
     */

    /**
     * the height of the map.
     */
    double HEIGHT = 100.0;

    /**
     * the width of the map.
     */
    double WIDTH = 100.0;

    /**
     * y coordinates of the top of the game world.
     */
    double UPPER_SIDE = 0;

    /**
     * y coordinates of the bottom of the game world.
     */
    double LOWER_SIDE = UPPER_SIDE + HEIGHT;

    /**
     * x coordinates of the left part of the game world.
     */
    double LEFT_SIDE = 0;

    /**
     * x coordinates of the right part of the game world.
     */
    double RIGHT_SIDE = LEFT_SIDE + WIDTH;


    /**
     * @return a collection of all the entities in the game
     */
    Collection<Entity> getEntities();

    /**
     * @return a collection of all the spaceships currently in the game
     */
    Collection<Spaceship> getSpaceships();

    /**
     * @return a collection of all the obstacles currently in the game
     */
    Collection<Obstacle> getObstacles();

    /**
     * @return a collection of all the projectiles currently in the game
     */
    Collection<Projectile> getProjectiles();

    /**
     * @return a collection of all the power-ups currently in the game
     */
    Collection<PowerUp> getPowerUps();

    /**
     * Called to update the position and status of all the entities in the map and manage their interactions.
     * @param time the time elapsed from the last update
     */
    void update(double time);

    /**
     * @return true if the game is over
     */
    boolean isOver();

    /**
     * @param spaceship to be added
     */
    void addSpaceship(Spaceship spaceship);

    /**
     * @param obstacle to be added
     */
    void addObstacle(Obstacle obstacle);

    /**
     * @param projectile to be added
     */
    void addProjectile(Projectile projectile);

    /**
     * @param powerUp to be added
     */
    void addPowerUp(PowerUp powerUp);

    /**
     * @param spaceship to be removed
     */
    void removeSpaceship(Spaceship spaceship);

    /**
     * @param obstacle to be removed
     */
    void removeObstacle(Obstacle obstacle);

    /**
     * @param projectile to be removed
     */
    void removeProjectile(Projectile projectile);

    /**
     * @param powerUp to be removed
     */
    void removePowerUp(PowerUp powerUp);

}
