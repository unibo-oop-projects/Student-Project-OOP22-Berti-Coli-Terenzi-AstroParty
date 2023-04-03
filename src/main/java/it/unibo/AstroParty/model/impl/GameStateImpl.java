package it.unibo.AstroParty.model.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import it.unibo.AstroParty.common.Position;
import it.unibo.AstroParty.model.api.CircleHitBox;
import it.unibo.AstroParty.model.api.Entity;
import it.unibo.AstroParty.model.api.Event;
import it.unibo.AstroParty.model.api.EventFactory;
import it.unibo.AstroParty.model.api.GameState;
import it.unibo.AstroParty.model.api.Observable;
import it.unibo.AstroParty.model.api.Observer;
import it.unibo.AstroParty.model.api.Obstacle;
import it.unibo.AstroParty.model.api.PowerUp;
import it.unibo.AstroParty.model.api.Projectile;
import it.unibo.AstroParty.model.api.Spaceship;

/**
 * GameState implementation.
 */
public class GameStateImpl implements GameState, Observable {

    private final List<Spaceship> spaceships;
    private final List<Obstacle> obstacles;
    private final List<Projectile> projectiles;
    private final List<PowerUp> powerUps;
    private final List<Observer> observers;
    private final EventFactory eventFactory;

    /**
     * Constructor for GameStateImpl.
     */
    public GameStateImpl() {
        spaceships = new ArrayList<>();
        obstacles = new ArrayList<>();
        projectiles = new ArrayList<>();
        powerUps = new ArrayList<>();
        observers = new ArrayList<>();
        eventFactory = new EventFactoryImpl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Entity> getEntities() {
        final List<Entity> worldEntities = new ArrayList<>();
        worldEntities.addAll(spaceships);
        worldEntities.addAll(obstacles);
        worldEntities.addAll(projectiles);
        worldEntities.addAll(powerUps);
        return worldEntities;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Spaceship> getSpaceships() {
        return this.spaceships;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Obstacle> getObstacles() {
        return this.obstacles;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Projectile> getProjectiles() {
        return this.projectiles;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<PowerUp> getPowerUps() {
        return this.powerUps;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final double time) {

        this.getEntities().stream()     // update all the entities 
                .forEach(e -> e.update(time));

        this.checkPlayerMovement();
        this.checkProjectileInteractions();
        this.checkSpaceshipInteractions();
    }

    private void checkPlayerMovement() {
        spaceships.stream().forEach(s -> {
            if (checkBoundariesCollisions(s.getHitBox())
                    || obstacles.stream()
                            .filter(o -> !o.isHarmful())
                            .anyMatch(e -> e.getHitBox().checkCircleCollision(s.getHitBox()))
                    || spaceships.stream()
                            .filter(targetSpaceship -> !targetSpaceship.equals(s))
                            .anyMatch(e -> e.getHitBox().checkCircleCollision(s.getHitBox()))) {
                this.notifyObservers(eventFactory.spaceshipColliedEvent(s));
            }
        });
    }

    private void checkProjectileInteractions() {
        projectiles.stream().forEach(p -> {
            boolean hit = false;

            if (checkBoundariesCollisions(p.getHitBox())) {
                hit = true;
            }

            for (final Spaceship s : spaceships) {
                if (s.getHitBox().checkCircleCollision(p.getHitBox())) {
                    this.notifyObservers(eventFactory.spaceshipHittedEvent(s));
                    hit = true;
                }
            }

            for (final Obstacle o : obstacles) {
                if (o.getHitBox().checkCircleCollision(p.getHitBox())) {
                    this.notifyObservers(eventFactory.obstacleHittedEvent(o));
                    hit = true;
                }
            }

            if (hit) {
                this.notifyObservers(eventFactory.projectileHitEvent(p));
            }
        });
    }

    private void checkSpaceshipInteractions() {
        for (final Spaceship s : spaceships) {

            powerUps.stream()
                    .filter(p -> p.getHitBox().checkCircleCollision(s.getHitBox()))
                    .forEach(p -> this.notifyObservers(eventFactory.powerUpEquipEvent(p, s)));

            obstacles.stream()
                    .filter(o -> o.isActive() && o.isHarmful() && o.getHitBox().checkCircleCollision(s.getHitBox()))
                    .forEach(o -> this.notifyObservers(eventFactory.spaceshipHittedEvent(s)));
        }
    }

    private boolean checkBoundariesCollisions(final CircleHitBox hb) {
        final Position pos = hb.getCenter();
        final double r = hb.getRadius();

        return pos.getX() + r > RIGHT_SIDE
                || pos.getX() - r < LEFT_SIDE
                || pos.getY() + r > LOWER_SIDE
                || pos.getY() - r < UPPER_SIDE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOver() {
        return spaceships.size() == 1;  //the game ends when there's only one player left
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addSpaceship(final Spaceship spaceship) {
        spaceships.add(spaceship);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addObstacle(final Obstacle obstacle) {
        obstacles.add(obstacle);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addProjectile(final Projectile projectile) {
        projectiles.add(projectile);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPowerUp(final PowerUp powerUp) {
        if (powerUps.size() < MAX_POWER_UP) {       //TODO: controllare se va (soluzione così così dai)
            powerUps.add(powerUp);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeSpaceship(final Spaceship spaceship) {
        spaceships.remove(spaceship);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeObstacle(final Obstacle obstacle) {
        obstacles.remove(obstacle);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeProjectile(final Projectile projectile) {
        projectiles.remove(projectile);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removePowerUp(final PowerUp powerUp) {
        powerUps.remove(powerUp);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void registerObserver(final Observer observer) {
        observers.add(observer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void unregisterObserver(final Observer observer) {
        observers.remove(observer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyObservers(final Event event) {
        for (final Observer o : observers) {
            o.notify(event);
        }
    }
}
