package it.unibo.AstroParty.model.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import it.unibo.AstroParty.common.Position;
import it.unibo.AstroParty.model.api.CircleHitBox;
import it.unibo.AstroParty.model.api.Entity;
import it.unibo.AstroParty.model.api.EntityType;
import it.unibo.AstroParty.model.api.EventFactory;
import it.unibo.AstroParty.model.api.GameState;
import it.unibo.AstroParty.model.api.PowerUp;
import it.unibo.AstroParty.model.api.Projectile;
import it.unibo.AstroParty.model.api.Spaceship;

/**
 * implementation of {@link GameState}
 */
public class GameStateImpl implements GameState {

    private final Map<EntityType, List<Entity>> worldEntities;
    private final EventFactory eventFactory;
    private final CollisionObserver observer;

    public GameStateImpl() {
        worldEntities = new HashMap<>();
        eventFactory = new EventFactoryImpl();
        observer = new CollisionObserver();
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
        worldEntities.values().stream()     // update all the entities 
                .forEach(l -> l.forEach(e -> e.update(time)));

        checkEntityCollisions();
        
        observer.manageEvents(this);
    }

    private void checkEntityCollisions() {
        worldEntities.get(EntityType.PROJECTILE).stream().forEach(p -> {     // check projectiles collisions

            if (checkBoundariesCollisions((CircleHitBox) p.getHitBox())) {
                observer.notify(eventFactory.hitEvent((Projectile) p, Optional.empty()));
            }

            this.getTargets(List.of(EntityType.SPACESHIP, EntityType.OBSTACLE)).stream()
                    .filter(e -> e.getHitBox().checkCircleCollision((CircleHitBox) p.getHitBox()))
                    .forEach(e -> eventFactory.hitEvent((Projectile) p, Optional.of(e))); 
                    
        });

        worldEntities.get(EntityType.SPACESHIP).stream().forEach(s -> {      // check spaceships colllisions

            if (checkBoundariesCollisions((CircleHitBox) s.getHitBox())) {
                observer.notify(eventFactory.colliedEvent((Spaceship) s, Optional.empty()));
            }

            getTargets(List.of(EntityType.OBSTACLE, EntityType.PICKABLE)).stream()
                    .filter(e -> e.getHitBox().checkCircleCollision((CircleHitBox) s.getHitBox()))
                    .forEach(e -> eventFactory.colliedEvent((Spaceship) s, Optional.of(e)));

        });
    }

    // returns all the entities in game of the types given in input
    private List<Entity> getTargets(List<EntityType> types) {
        return worldEntities.entrySet().stream()
        .filter(entry -> types.stream().anyMatch(t -> t == entry.getKey()))
        .map(entry -> entry.getValue())
        .reduce((l1,l2) -> {l1.addAll(l2); return l1;})
        .get();
    }

    private boolean checkBoundariesCollisions(CircleHitBox hb) {
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
    public void addEntity(Entity entity) {
        worldEntities.get(this.getType(entity)).add(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeEntity(Entity entity) {
        worldEntities.get(this.getType(entity)).remove(entity); 
    }

    private EntityType getType(Entity entity) {
        if (entity instanceof Spaceship) {
            return EntityType.SPACESHIP;
        } else if (entity instanceof Projectile) {
            return EntityType.PROJECTILE;
        } else if (entity instanceof PowerUp) {
            return EntityType.PICKABLE;
        } else if (entity instanceof Projectile) {
            return EntityType.PROJECTILE;
        } else {
            throw new IllegalArgumentException("class not found");
        }
    }
    
}
