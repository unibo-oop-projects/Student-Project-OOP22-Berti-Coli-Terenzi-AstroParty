package it.unibo.astroparty.core.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import it.unibo.astroparty.common.Pair;
import it.unibo.astroparty.common.Position;
import it.unibo.astroparty.core.api.GameEngine;
import it.unibo.astroparty.core.api.GameView;
import it.unibo.astroparty.game.EntityType;
import it.unibo.astroparty.game.logics.api.GameState;
import it.unibo.astroparty.game.logics.api.Observer;
import it.unibo.astroparty.game.logics.impl.CollisionObserver;
import it.unibo.astroparty.game.logics.impl.GameStateImpl;
import it.unibo.astroparty.game.obstacle.api.Obstacle;
import it.unibo.astroparty.game.obstacle.api.ObstacleFactory;
import it.unibo.astroparty.game.obstacle.impl.ObstacleFactoryImpl;
import it.unibo.astroparty.game.powerup.api.PowerUpFactory;
import it.unibo.astroparty.game.powerup.api.SpawnerSettings;
import it.unibo.astroparty.game.powerup.impl.PowerUpFactoryImpl;
import it.unibo.astroparty.game.powerup.impl.SpawnerSettingsImpl;
import it.unibo.astroparty.game.spaceship.api.Spaceship;
import it.unibo.astroparty.game.spaceship.api.SpaceshipBuilder;
import it.unibo.astroparty.game.spaceship.impl.SpaceshipBuilderImpl;
import it.unibo.astroparty.graphics.api.GameScene;
import it.unibo.astroparty.input.api.InputControl;
import it.unibo.astroparty.input.impl.InputControlImpl;
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
    private SpaceshipBuilder spaceshipBuilder;
    private ObstacleFactory obstacleFactory;
    private GameView view;
    private InputControl inputControl;
    private GameScene gameScene;
    private CollisionObserver collisionObserver;
    private Map<Pair<Integer, Integer>, Pair<Integer, Integer>> mapObstacles = new HashMap<>();
    private int roundsGame;
	private boolean obstaclesBool;
	private boolean powerupsBool;
    
    //Constructor
    public GameEngineImpl(GameView view) {
        this.view = view;
    }
    
    public void init(List<String> players, boolean obstacle, boolean powerup, int rounds) {

        spaceshipBuilder = new SpaceshipBuilderImpl();
        spaceshipBuilder.setNames(players);
        this.roundsGame = rounds;
        this.obstaclesBool = obstacle;
        this.powerupsBool = powerup;
    	
        gameState = new GameStateImpl();
        collisionObserver = new CollisionObserver();
        gameState.registerObserver(collisionObserver);
         
        
        if(this.obstaclesBool) {
        	createObstacles();
        }
        
        if(this.powerupsBool) {
        	createPowerups();
        }


        spaceshipBuilder.create(gameState).forEach(s -> gameState.addSpaceship(s));
        this.inputControl = new InputControlImpl();
        
        try {
            view.switchScene(view.getSceneFactory().createGame(inputControl));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        gameScene = (GameScene) this.view.getScene();
    }

    private void createPowerups() {
    	 //Set of the SpawnDelay and enumeration of PowerUpTypes
        spawnerSettings = new SpawnerSettingsImpl();
        spawnerSettings.enableAll();
	}

	private void createObstacles() {
    	Set<Pair<Integer, Integer>> keySetObstacles = new HashSet<>();
        Object[] arrayObstacles;
        Random rand = new Random();
        Object a;
        int b, cont = 0;
        Pair<Integer, Integer> c;
        Set<Pair<Integer, Integer>> addedObstacles = new HashSet<>();
        obstacleFactory = new ObstacleFactoryImpl();
    	
    	
    	//Ostacolo fisso
        gameState.addObstacle(obstacleFactory.createUndestroyableObstacle(new Position(47, 47)));
        
        this.mapObstacles.put(new Pair<>(47, 27), new Pair<>(47, 67));
        this.mapObstacles.put(new Pair<>(47, 7), new Pair<>(47, 87));
        this.mapObstacles.put(new Pair<>(7, 47), new Pair<>(87, 47));
        this.mapObstacles.put(new Pair<>(27, 47), new Pair<>(67, 47));

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
            
            if(powerupsBool) {
            	//start of Spawner of PowerUps
                spawnerSettings.startGame().start(gameState);
            }
            
            
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
        inputControl.computeAll(this.gameState.getSpaceships());
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
