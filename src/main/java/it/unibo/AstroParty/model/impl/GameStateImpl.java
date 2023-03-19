package it.unibo.AstroParty.model.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import it.unibo.AstroParty.common.Position;
import it.unibo.AstroParty.model.api.CircleHitBox;
import it.unibo.AstroParty.model.api.Entity;
import it.unibo.AstroParty.model.api.EntityType;
import it.unibo.AstroParty.model.api.Event;
import it.unibo.AstroParty.model.api.EventFactory;
import it.unibo.AstroParty.model.api.GameState;
import it.unibo.AstroParty.model.api.Obstacle;
import it.unibo.AstroParty.model.api.PowerUp;
import it.unibo.AstroParty.model.api.Projectile;
import it.unibo.AstroParty.model.api.Spaceship;

public class GameStateImpl implements GameState {

    private final Map<EntityType, List<Entity>> worldEntities;
    private final Queue<Event> eventQueue;
    private final EventFactory eventFactory;

    public GameStateImpl() {
        worldEntities = new HashMap<>();
        eventQueue = new LinkedBlockingQueue<>();
        eventFactory = new EventFactoryImpl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Entity> getEntities() {
        return worldEntities.values().stream()
                .reduce((l1,l2) -> {l1.addAll(l2); return l1;})
                .get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(double time) {
        worldEntities.get(EntityType.PROJECTILE).forEach(p -> {

            if (checkBoundariesCollision((CircleHitBox) p.getHitBox())) {
                eventQueue.add(eventFactory.hitEvent((Projectile) p, Optional.empty()));
            }

            this.getTargets(List.of(EntityType.SPACESHIP, EntityType.OBSTACLE)).stream()
                    .filter(e -> e.getHitBox().checkCircleCollision((CircleHitBox) p.getHitBox()))
                    .forEach(e -> eventFactory.hitEvent((Projectile) p, Optional.of(e))); 
                    
        });

        worldEntities.get(EntityType.SPACESHIP).forEach(s -> {

            if (checkBoundariesCollision((CircleHitBox) s.getHitBox())) {
                eventQueue.add(eventFactory.colliedEvent((Spaceship) s, Optional.empty()));
            }

            getTargets(List.of(EntityType.OBSTACLE, EntityType.PICKABLE)).stream()
                    .filter(e -> e.getHitBox().checkCircleCollision((CircleHitBox) s.getHitBox()))
                    .forEach(e -> eventFactory.colliedEvent((Spaceship) s, Optional.of(e)));

        });
    }

    private List<Entity> getTargets(List<EntityType> types) {
        return worldEntities.entrySet().stream()
        .filter(entry -> types.stream().anyMatch(t -> t == entry.getKey()))
        .map(entry -> entry.getValue())
        .reduce((l1,l2) -> {l1.addAll(l2); return l1;})
        .get();
    }

    private boolean checkBoundariesCollision(CircleHitBox hb) {
        Position pos = hb.getCenter();
        double r = hb.getRadius();

        return pos.getX() + r > rightSide
                || pos.getX() - r < leftSide
                || pos.getY() + r > upperSide
                || pos.getY() - r < lowerSide;
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOver() {
        return worldEntities.get(EntityType.SPACESHIP).size() == 1;  //the game ends when there's only one player left
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addSpaceship(Spaceship spaceship) {
        worldEntities.get(EntityType.SPACESHIP).add(spaceship);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addObstacle(Obstacle obstacle) {
        worldEntities.get(EntityType.OBSTACLE).add(obstacle);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPowerUp(PowerUp powerup) {
        worldEntities.get(EntityType.PICKABLE).add(powerup);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addProjectile(Projectile projectile) {
        worldEntities.get(EntityType.PROJECTILE).add(projectile);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void removeEntity(Entity entity) {
        if (entity instanceof Spaceship) {
            this.worldEntities.remove(EntityType.SPACESHIP, entity);
        } else if (entity instanceof Projectile) {
            this.worldEntities.remove(EntityType.PROJECTILE, entity);
        } else if (entity instanceof PowerUp) {
            this.worldEntities.remove(EntityType.PICKABLE, entity);
        } else if (entity instanceof Projectile) {
            this.worldEntities.remove(EntityType.PROJECTILE, entity);
        }
        
    }
    
}
