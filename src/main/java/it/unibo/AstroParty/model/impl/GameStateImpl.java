package it.unibo.AstroParty.model.impl;

import java.util.Collection;
import java.util.HashSet;

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

    public final double width, height;

    /**
     * 
     * @param width the width of the world map
     * @param height the heigth of the world map
     */
    public GameStateImpl(double width, double height) {
        this.width = width;
        this.height = height;
    }

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
        // TO-DO
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

    @Override
    public double getWorldHeight() {
        return height;
    }

    @Override
    public double getWorldWidth() {
        return width;
    }
    
}
