package it.unibo.AstroParty.core.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import it.unibo.AstroParty.common.Pair;
import it.unibo.AstroParty.common.Position;
import it.unibo.AstroParty.core.api.GameEngine;
import it.unibo.AstroParty.core.api.GameView;
import it.unibo.AstroParty.graphics.api.GameScene;
import it.unibo.AstroParty.input.api.InputControl;
import it.unibo.AstroParty.input.impl.InputControlImpl;
import it.unibo.AstroParty.model.Obstacle.api.ObstacleFactory;
import it.unibo.AstroParty.model.Obstacle.impl.ObstacleFactoryImpl;
import it.unibo.AstroParty.model.PowerUp.api.PowerUpFactory;
import it.unibo.AstroParty.model.PowerUp.api.SpawnerSettings;
import it.unibo.AstroParty.model.PowerUp.impl.PowerUpFactoryImpl;
import it.unibo.AstroParty.model.PowerUp.impl.SpawnerSettingsImpl;
import it.unibo.AstroParty.model.Spaceship.api.SpaceshipBuilder;
import it.unibo.AstroParty.model.Spaceship.impl.SpaceshipBuilderImpl;
import it.unibo.AstroParty.model.api.EntityType;
import it.unibo.AstroParty.model.api.GameState;
import it.unibo.AstroParty.model.api.Observer;
import it.unibo.AstroParty.model.api.Obstacle;
import it.unibo.AstroParty.model.api.Spaceship;
import it.unibo.AstroParty.model.impl.CollisionObserver;
import it.unibo.AstroParty.model.impl.GameStateImpl;
import javafx.application.Platform;

/**
 * class for the core of the game: the gameLoop
 * @author dario
 *
 */

public class GameEngineImpl implements GameEngine, Runnable {
    
    private static final int FPS = 60;
    private GameStateImpl gameState;
    private SpawnerSettings spawnerSettings;
    private PowerUpFactory powerUpFactory;
    private SpaceshipBuilder spaceshipBuilder;
    private ObstacleFactory obstacleFactory;
    private GameView view;
    private Collection<Spaceship> spaceships;
    private InputControl inputControl;
    private GameScene gameScene;
    private CollisionObserver collisionObserver;
    private Map<Pair<Integer, Integer>, Pair<Integer, Integer>> mapObstacles = new HashMap<>();
    private int roundsGame;
    
    //Constructor
    public GameEngineImpl(GameView view, List<String> players, boolean obstacle, boolean powerup, int rounds) {
        this.view = view;
        spaceshipBuilder = new SpaceshipBuilderImpl();
        spaceshipBuilder.setNames(players);
        this.init();
        this.roundsGame = rounds;
    }
    
    public void init() {
        Set<Pair<Integer, Integer>> keySetObstacles = new HashSet<>();
        Object[] arrayObstacles;
        Random rand = new Random();
        Object a;
        int b, cont = 0;
        Pair<Integer, Integer> c;
        Set<Pair<Integer, Integer>> addedObstacles = new HashSet<>();
        
        gameState = new GameStateImpl();
        collisionObserver = new CollisionObserver();
        gameState.registerObserver(collisionObserver);
        
        
        //Set of the SpawnDelay and enumeration of PowerUpTypes
        spawnerSettings = new SpawnerSettingsImpl();
        spawnerSettings.enableAll();
        
        
        obstacleFactory = new ObstacleFactoryImpl();
        //Ostacolo fisso
        gameState.addObstacle(obstacleFactory.createUndestroyableObstacle(new Position(50, 50)));
        
        this.mapObstacles.put(new Pair<>(50, 30), new Pair<>(50, 70));
        this.mapObstacles.put(new Pair<>(50, 10), new Pair<>(50, 90));
        this.mapObstacles.put(new Pair<>(10, 50), new Pair<>(90, 50));
        this.mapObstacles.put(new Pair<>(30, 50), new Pair<>(70, 50));

        keySetObstacles = this.mapObstacles.keySet();
        arrayObstacles = keySetObstacles.toArray();
        b = arrayObstacles.length;

        

        while(cont < b/2) {
            a = arrayObstacles[rand.nextInt(arrayObstacles.length)];
            c = mapObstacles.get(a);

            @SuppressWarnings("unchecked")
            Pair<Integer,Integer> aPair = (Pair<Integer, Integer>) a;

            if(!addedObstacles.contains(new Pair<>(aPair.getX(), aPair.getY()))) {
                gameState.addObstacle(obstacleFactory.createSimpleObstacle(new Position(aPair.getX(), aPair.getY())));
                gameState.addObstacle(obstacleFactory.createSimpleObstacle(new Position(c.getX(),c.getY())));
                addedObstacles.add(new Pair<>(aPair.getX(), aPair.getY()));
                cont = cont + 1;
            }
        }
        
        this.spaceships = spaceshipBuilder.create(gameState);
        this.spaceships.forEach(s -> gameState.addSpaceship(s));
        this.inputControl = new InputControlImpl( this.spaceships);
        
        try {
            view.switchScene(view.getSceneFactory().createGame(inputControl));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        gameScene = (GameScene) this.view.getScene();
    }

    public void mainLoop() {
        Round round = new Round();
        round.start();
    }
    
    private class Round extends Thread{
        public void run() {
            double viewRefreshInterval = 1000/FPS;
            //long currentTime=0;
            double nextRefreshTime = viewRefreshInterval + System.currentTimeMillis();
            
            //start of Spawner of PowerUps
            spawnerSettings.startGame().start(gameState);
            
            inputControl.start();
            while(!gameState.isOver()) {
                //currentTime= System.currentTimeMillis();
                //System.out.println("current time:"+currentTime);
                
                
                processInput();
                
                updateGame(viewRefreshInterval);

                collisionObserver.manageEvents(gameState);
                
                render();
                
                try {
                    double surplusTime = nextRefreshTime - System.currentTimeMillis();
                    
                    if(surplusTime < 0) {
                        surplusTime = 0;
                    }
                    Thread.sleep((long) surplusTime);
                    nextRefreshTime += viewRefreshInterval;
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
            Platform.runLater(new Runnable() {

                @Override
                public void run() {
                    try {
                        view.switchScene(view.getSceneFactory().createScoreboard(List.of(1,2,3,4), roundsGame));
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                
            });
        }
    }
    
    public InputControl getController() {
        return inputControl;
    }
    
    protected void processInput() {
        inputControl.compute();
    }
    
    protected void updateGame(double timePassedCycle) {
        gameState.update(timePassedCycle);
    }
    
    protected void render() {
        ( (GameScene) view.getScene() ).renderAll(gameState.getEntities().stream().filter(e -> !(e instanceof Obstacle) || ((Obstacle) e).isActive()).map(e -> e.getGraphicComponent()).toList());
    }

    @Override
    public void run() {
        mainLoop();
    }

}
