package it.unibo.AstroParty.model.impl;

import java.util.Collection;
import java.util.HashSet;

import it.unibo.AstroParty.common.Position;
import it.unibo.AstroParty.model.api.Entity;
import it.unibo.AstroParty.model.api.GameState;
import it.unibo.AstroParty.model.api.Obstacle;
import it.unibo.AstroParty.model.api.PowerUp;
import it.unibo.AstroParty.model.api.Projectile;
import it.unibo.AstroParty.model.api.Spaceship;

public class GameStateImpl implements GameState {

    private final Collection<Spaceship> spaceships = new HashSet<>();
    private final Collection<Obstacle> obstacles = new HashSet<>();
    private final Collection<PowerUp> powerUps = new HashSet<>();
    private final Collection<Projectile> projectiles = new HashSet<>();

    @Override
    public Collection<Entity> getEntities() {
        final Collection<Entity> entities = new HashSet<>();
        entities.addAll(spaceships);
        entities.addAll(obstacles);
        entities.addAll(powerUps);
        entities.addAll(projectiles);
        return entities;
    }

    @Override
    public void update(double time) {
        double r;
        Position pos;

        spaceships.forEach(obj -> obj.update(time));
        projectiles.forEach(obj -> obj.update(time));
        powerUps.forEach(obj -> obj.update(time));

        for (Projectile currProjectile : projectiles) {
            r = currProjectile.getHitBox().getRadius();
            pos = currProjectile.getPosition();

            if (pos.getX() + r > width || pos.getX() - r < 0 ||
                    pos.getY() + r > height || pos.getY() - r < 0) {
                projectiles.remove(currProjectile);
            }
            //TODO: check collision with obstacles
        }

        for (Spaceship currSpaceship : spaceships) {
            r = currSpaceship.getHitBox().getRadius();
            pos = currSpaceship.getPosition();

            double fixedX = pos.getX();
            double fixedY = pos.getY();

            if (pos.getX() + r > width) {
                fixedX = width - r;
            } else if (pos.getX() - r < 0) {
                fixedX = r;
            }

            if (pos.getY() + r > height) {
                fixedY = height - r;
            } else if (pos.getY() - r < 0) {
                fixedY = r;
            }

            if (fixedX != pos.getX() || fixedY != pos.getY()) {
                currSpaceship.setPosition(new Position(fixedX, fixedY));
            }
            //TODO: check collision with obstacles

            for (Projectile p : projectiles) {
                if (p.getHitBox().isHitted(pos,r)) {
                    if (currSpaceship.hit()) {
                        spaceships.remove(currSpaceship);
                    }
                    projectiles.remove(p);
                }
            }
        }
    }

    @Override
    public boolean isOver() {
        return spaceships.size() == 1;  //the game ends when there's only one player left
    }

    @Override
    public void addSpaceship(Spaceship s) {
        spaceships.add(s);
    }

    @Override
    public void addObstacle(Obstacle o) {
        obstacles.add(o);
    }

    @Override
    public void addPowerUp(PowerUp pUp) {
        powerUps.add(pUp);
    }

    @Override
    public void addProjectile(Projectile p) {
        projectiles.add(p);
    }
    
}
