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
 * implementation of {@link GameState}
 */
public class GameStateImpl implements GameState, Observable {

    private final List<Spaceship> spaceships;
    private final List<Obstacle> obstacles;
    private final List<Projectile> projectiles;
    private final List<PowerUp> powerUps;
    private final List<Observer> observers;
    private final EventFactory eventFactory;
    private final CollisionObserver collisionObserver;

    public GameStateImpl() {
        spaceships = new ArrayList<>();
        obstacles = new ArrayList<>();
        projectiles = new ArrayList<>();
        powerUps = new ArrayList<>();
        observers = new ArrayList<>();
        eventFactory = new EventFactoryImpl();
        collisionObserver = new CollisionObserver();
        registerObserver(collisionObserver);
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
    public void update(double time) {

        this.getEntities().stream()     // update all the entities 
                .forEach(e -> e.update(time));

        this.checkPlayerMovement();
        
        collisionObserver.manageEvents(this);    // manage movement events
        
        this.checkProjectileInteractions();
        this.checkSpaceshipInteractions();
        
        collisionObserver.manageEvents(this);    // manage interaction events
    }

    private void checkPlayerMovement() {
        spaceships.stream().forEach(s -> {
            if (checkBoundariesCollisions(s.getHitBox())
                    || obstacles.stream()
                            .anyMatch(e -> e.getHitBox().checkCircleCollision(s.getHitBox()))
                    || spaceships.stream()
                            .filter(targetSpaceship -> !targetSpaceship.equals(s))
                            .anyMatch(e -> e.getHitBox().checkCircleCollision(s.getHitBox()))) {
                this.notifyObservers(eventFactory.SpaceshipColliedEvent(s));
            }
        });
    }
    
    private void checkProjectileInteractions() {
        projectiles.stream().forEach(p -> {
            boolean hit = false;

            if (checkBoundariesCollisions(p.getHitBox())) {
                hit = true;
            }

            for (Spaceship s : spaceships) {
                if (s.getHitBox().checkCircleCollision(p.getHitBox())) {
                    this.notifyObservers(eventFactory.spaceshipHittedEvent(s));
                    hit = true;
                }
            }

            for (Obstacle o : obstacles) {
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
        for (Spaceship s : spaceships) {

            powerUps.stream()
                    .filter(p -> p.getHitBox().checkCircleCollision(s.getHitBox()))
                    .forEach(p -> this.notifyObservers(eventFactory.powerUpEquipEvent(p, s)));
        }
    }

    private boolean checkBoundariesCollisions(CircleHitBox hb) {
        Position pos = hb.getCenter();
        double r = hb.getRadius();

        return pos.getX() + r > rightSide
                || pos.getX() - r < leftSide
                || pos.getY() + r > lowerSide
                || pos.getY() - r < upperSide;
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
    public void addSpaceship(Spaceship spaceship) {
        spaceships.add(spaceship);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addObstacle(Obstacle obstacle) {
        obstacles.add(obstacle);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addProjectile(Projectile projectile) {
        projectiles.add(projectile);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPowerUp(PowerUp powerUp) {
        powerUps.add(powerUp);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeSpaceship(Spaceship spaceship) {
        spaceships.remove(spaceship);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeObstacle(Obstacle obstacle) {
        obstacles.remove(obstacle);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeProjectile(Projectile projectile) {
        projectiles.remove(projectile);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removePowerUp(PowerUp powerUp) {
        powerUps.remove(powerUp);
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void unregisterObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Event event) {
        for (Observer o : observers) {
            o.notify(event);
        }
    }
    
}
