package it.unibo.AstroParty.model.impl;

import java.util.ArrayList;
import java.util.List;

import it.unibo.AstroParty.common.Position;
import it.unibo.AstroParty.model.api.CircleHitBox;
import it.unibo.AstroParty.model.api.Entity;
import it.unibo.AstroParty.model.api.GameState;
import it.unibo.AstroParty.model.api.Obstacle;
import it.unibo.AstroParty.model.api.PowerUp;
import it.unibo.AstroParty.model.api.Projectile;
import it.unibo.AstroParty.model.api.Spaceship;

public class GameStateImpl implements GameState {

    private final List<Spaceship> spaceships;
    private final List<Projectile> projectiles;
    private final List<Obstacle> obstacles;
    private final List<Entity> powerUps;

    public GameStateImpl() {
        spaceships = new ArrayList<>();
        projectiles = new ArrayList<>();
        obstacles = new ArrayList<>();
        powerUps = new ArrayList<>();
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public List<Entity> getEntities() {
        final List<Entity> entities = new ArrayList<>();
        entities.addAll(spaceships);
        entities.addAll(projectiles);
        entities.addAll(obstacles);
        entities.addAll(powerUps);
        return entities;
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public void update(double time) {

        powerUps.forEach(p -> p.update(time));
        obstacles.forEach(o -> o.update(time));

        projectiles.forEach(p -> {
            boolean found = false;
            p.update(time);

            for (Spaceship s : spaceships) {
                if (s.getHitBox().checkCircleCollision(p.getHitBox()) && s.hit()) {
                    spaceships.remove(s);
                    found = true;
                }
            }

            for (Obstacle o : obstacles) {
                if (o.isActive() && o.getHitBox().checkCircleCollision(p.getHitBox()) && o.hit()) {
                    obstacles.remove(o);
                    found = true;
                }
            }

            if (found || checkBoundariesCollision(p.getHitBox())) {
                projectiles.remove(p);
            }
        });

        List<Spaceship> updatedSpaceships = new ArrayList<>();
        spaceships.forEach(s -> {
            Position curr = s.getPosition();
            boolean found = false;
            s.update(time);

            for (Spaceship updated : updatedSpaceships) {
                if (updated.getHitBox().checkCircleCollision(s.getHitBox())) {
                    found = true;
                }
            }

            for (Obstacle o : obstacles) {
                if (o.isActive() && o.getHitBox().checkCircleCollision(s.getHitBox())) {
                    if (o.isHarmful()) {
                        spaceships.remove(s);
                        break;
                    }
                    found = true;
                }
            }

            if (found || checkBoundariesCollision(s.getHitBox())) {
                s.setPosition(curr);
            }

            updatedSpaceships.add(s);
        });
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
     * {@inheritDoc}}
     */
    @Override
    public boolean isOver() {
        return spaceships.size() == 1;  //the game ends when there's only one player left
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public void addSpaceship(Spaceship s) {
        spaceships.add(s);
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public void addObstacle(Obstacle o) {
        obstacles.add(o);
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public void addPowerUp(PowerUp p) {
        powerUps.add(p);
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public void addProjectile(Projectile p) {
        projectiles.add(p);
    }
    
}
