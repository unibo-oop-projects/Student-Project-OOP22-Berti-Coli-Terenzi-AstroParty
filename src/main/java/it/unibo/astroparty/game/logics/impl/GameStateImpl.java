package it.unibo.astroparty.game.logics.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

import it.unibo.astroparty.common.Position;
import it.unibo.astroparty.game.Entity;
import it.unibo.astroparty.game.hitbox.api.CircleHitBox;
import it.unibo.astroparty.game.logics.api.Event;
import it.unibo.astroparty.game.logics.api.EventFactory;
import it.unibo.astroparty.game.logics.api.GameState;
import it.unibo.astroparty.game.logics.api.Observable;
import it.unibo.astroparty.game.logics.api.Observer;
import it.unibo.astroparty.game.obstacle.api.Obstacle;
import it.unibo.astroparty.game.powerup.api.PowerUp;
import it.unibo.astroparty.game.projectile.api.Projectile;
import it.unibo.astroparty.game.spaceship.api.Spaceship;

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
        return List.copyOf(this.spaceships);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Obstacle> getObstacles() {
        return List.copyOf(this.obstacles);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Projectile> getProjectiles() {
        return List.copyOf(this.projectiles);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<PowerUp> getPowerUps() {
        return List.copyOf(this.powerUps);
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

    private boolean checkCollisionWith(final List<? extends Entity> entities, final CircleHitBox targetHB,
            final Function<Entity, Event> eventGetter) {

        final List<? extends Entity> hitted = entities.stream()
                .filter(e -> e.getHitBox().checkCircleCollision(targetHB))
                .toList();

        if (hitted.isEmpty()) {
            return false;
        } else {
            hitted.stream().forEach(e -> this.notifyObservers(eventGetter.apply(e)));
            return true;
        }
    }

    private void checkProjectileInteractions() {
        projectiles.stream().forEach(p -> {
            final CircleHitBox hBox = p.getHitBox();

            if (checkBoundariesCollisions(p.getHitBox())
                    || this.checkCollisionWith(
                            spaceships,
                            hBox,
                            e -> this.eventFactory.spaceshipHittedEvent((Spaceship) e))
                    || this.checkCollisionWith(
                            obstacles,
                            hBox,
                            e -> this.eventFactory.obstacleHittedEvent((Obstacle) e))) {
                this.notifyObservers(eventFactory.projectileHitEvent(p));
            }
        });
    }

    private void checkSpaceshipInteractions() {
        spaceships.stream().forEach(s -> {
            final CircleHitBox hBox = s.getHitBox();

            this.checkCollisionWith(
                    powerUps,
                    hBox,
                    p -> this.eventFactory.powerUpEquipEvent((PowerUp) p, s)
                );

            this.checkCollisionWith(
                    obstacles.stream().filter(o -> o.isActive() && o.isHarmful()).toList(),
                    hBox,
                    o -> this.eventFactory.spaceshipHittedEvent(s)
                );
        });
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
        return spaceships.size() == 1;  // the game ends when there's only one player left
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
        powerUps.add(powerUp);
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
