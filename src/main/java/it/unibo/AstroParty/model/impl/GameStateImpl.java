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
        obstacles.forEach(obj -> obj.update(time));

        // controllo le collisioni dei proiettili con i bordi della mappa (in caso li elimino)
        for (Projectile currProjectile : projectiles) {
            r = currProjectile.getHitBox().getRadius();
            pos = currProjectile.getPosition();

            if (pos.getX() + r > rightSide || pos.getX() - r < leftSide ||
                    pos.getY() + r > upperSide || pos.getY() - r < lowerSide) {
                projectiles.remove(currProjectile);
            }
            
            // controllo collisioni proiettile-ostacolo
            for (Obstacle obs : obstacles) {
                if (obs.getHitBox().isHitted(pos, r)) {
                    if (obs.hit()) {
                        obstacles.remove(obs);
                    }
                    projectiles.remove(currProjectile);
                }
            }
        }
        
        for (Spaceship currSpaceship : spaceships) {
            r = currSpaceship.getHitBox().getRadius();
            pos = currSpaceship.getPosition();

            double fixedX = pos.getX();
            double fixedY = pos.getY();

            // controllo la collisione delle astronavi con i bordi e in caso sistemo la loro posizione
            if (pos.getX() + r > rightSide) {
                fixedX = rightSide - r;
            } else if (pos.getX() - r < leftSide) {
                fixedX = leftSide + r;
            }

            if (pos.getY() + r > upperSide) {
                fixedY = upperSide - r;
            } else if (pos.getY() - r < lowerSide) {
                fixedY = lowerSide + r;
            }

            if (fixedX != pos.getX() || fixedY != pos.getY()) { // fix the position if needed
                pos = new Position(fixedX, fixedY);
                currSpaceship.setPosition(pos);
            }

            // controllo le collisioni astronavi-ostacoli
            for (Obstacle o : obstacles) {
                if (o.getHitBox().isHitted(pos, r)) {
                    if (o.isHarmful() && currSpaceship.hit()) {
                        spaceships.remove(currSpaceship);
                    } else {
                        //TODO: correggere la posizione in caso di collisione
                    }
                }
            }
            //TODO: collisione astronave-astronave

            // controllo le collisioni astronavi-proiettili
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
