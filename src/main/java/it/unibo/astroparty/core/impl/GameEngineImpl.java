package it.unibo.astroparty.core.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import it.unibo.astroparty.common.Pair;
import it.unibo.astroparty.common.Position;
import it.unibo.astroparty.core.api.GameEngine;
import it.unibo.astroparty.core.api.GameView;
import it.unibo.astroparty.game.logics.impl.CollisionEventQueue;
import it.unibo.astroparty.game.logics.impl.GameStateImpl;
import it.unibo.astroparty.game.obstacle.api.Obstacle;
import it.unibo.astroparty.game.obstacle.api.ObstacleFactory;
import it.unibo.astroparty.game.obstacle.impl.ObstacleFactoryImpl;
import it.unibo.astroparty.game.powerup.api.SpawnerSettings;
import it.unibo.astroparty.game.powerup.impl.SpawnerSettingsImpl;
import it.unibo.astroparty.game.spaceship.api.SpaceshipBuilder;
import it.unibo.astroparty.game.spaceship.impl.SpaceshipBuilderImpl;
import it.unibo.astroparty.graphics.api.GameScene;
import it.unibo.astroparty.input.api.InputControl;
import it.unibo.astroparty.input.impl.InputControlImpl;
import it.unibo.astroparty.ui.impl.OverController;
import javafx.application.Platform;
import javafx.scene.Scene;

/**
 * class for the core of the game: the gameLoop
 * @author dario
 *
 */

public class GameEngineImpl implements GameEngine {
    
    private static final int FPS = 60;
    private GameStateImpl gameState;
    private SpawnerSettings spawnerSettings;
    private SpaceshipBuilder spaceshipBuilder;
    private GameView view;
    private InputControl inputControl;
    private CollisionEventQueue collisionObserver;
    private int roundsGame;
	private boolean obstaclesBool;
	private boolean powerupsBool;
	private Set<Pair<Integer, Integer>> keySetObstacles;
	private Set<Pair<Integer, Integer>> addedObstacles;
	private Map<Pair<Integer, Integer>, Pair<Integer, Integer>> mapObstacles;
	private ObstacleFactory obstacleFactory;
	private Random rand;
	private Integer p1,p2,p3,p4;
    
    //Constructor
    public GameEngineImpl(GameView view) {
        this.view = view;
    }
    
    //chiamata solo una volta
    public void init(List<String> players, boolean obstacle, boolean powerup, int rounds) {

        this.spaceshipBuilder = new SpaceshipBuilderImpl();
        
        this.spaceshipBuilder.setNames(players);
        this.roundsGame = rounds;
        this.obstaclesBool = obstacle;
        this.powerupsBool = powerup;
        this.p1 = 0;
        this.p2 = 0;
        this.p3 = 0;
        this.p4 = 0;
    }
    
  //chiamata ogni round
    private void createMap() {
    	this.gameState = new GameStateImpl();
    	this.inputControl = new InputControlImpl();
        this.collisionObserver = new CollisionEventQueue();
        //this.spaceshipBuilder = new SpaceshipBuilderImpl();

        createObstacles();

        if(this.powerupsBool) {
        	createPowerups();
        }
        
        if(this.obstaclesBool) {
        	createLasers();
        }

        this.gameState.registerObserver(this.collisionObserver);

        this.spaceshipBuilder.create(this.gameState).forEach(s -> this.gameState.addSpaceship(s));

        try {
            this.view.renderScene(this.view.getSceneFactory().createGame(this.inputControl));
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

    private void createPowerups() {
    	 //Set of the SpawnDelay and enumeration of PowerUpTypes
        this.spawnerSettings = new SpawnerSettingsImpl();
        this.spawnerSettings.enableAll();
	}

	private void createObstacles() {
    	this.keySetObstacles = new HashSet<>();
    	this.addedObstacles = new HashSet<>();
        this.mapObstacles = new HashMap<>();
        this.obstacleFactory = new ObstacleFactoryImpl();
        this.rand = new Random();
    	
    	Object[] arrayObstacles;
        Object a;
        int b, cont = 0;
        Pair<Integer, Integer> c;
        
    	
    	//Ostacolo fisso
        this.gameState.addObstacle(this.obstacleFactory.createUndestroyableObstacle(new Position(47, 47)));
        
        if(this.obstaclesBool) {
        	this.mapObstacles.put(new Pair<>(47, 27), new Pair<>(47, 67));
            this.mapObstacles.put(new Pair<>(47, 7), new Pair<>(47, 87));
            this.mapObstacles.put(new Pair<>(7, 47), new Pair<>(87, 47));
            this.mapObstacles.put(new Pair<>(27, 47), new Pair<>(67, 47));

            this.keySetObstacles = this.mapObstacles.keySet();
            arrayObstacles = this.keySetObstacles.toArray();
            b = arrayObstacles.length;


            while(cont < b/2) {
                a = arrayObstacles[rand.nextInt(arrayObstacles.length)];
                c = this.mapObstacles.get(a);

                @SuppressWarnings("unchecked")
                Pair<Integer,Integer> aPair = (Pair<Integer, Integer>) a;

                if(!this.addedObstacles.contains(new Pair<>(aPair.getX(), aPair.getY()))) {
                    this.gameState.addObstacle(this.obstacleFactory.createSimpleObstacle(new Position(aPair.getX(), aPair.getY())));
                    this.gameState.addObstacle(this.obstacleFactory.createSimpleObstacle(new Position(c.getX(),c.getY())));
                    this.addedObstacles.add(new Pair<>(aPair.getX(), aPair.getY()));
                    cont = cont + 1;
                }
            }
        }
        
	}
	
	private void createLasers() {
		this.gameState.addObstacle(this.obstacleFactory.createLaser(new Position(17, 47)));
		this.gameState.addObstacle(this.obstacleFactory.createLaser(new Position(77, 47)));
		this.gameState.addObstacle(this.obstacleFactory.createLaser(new Position(47, 17)));
		this.gameState.addObstacle(this.obstacleFactory.createLaser(new Position(47, 77)));
	}

	public void mainLoop() {
		this.createMap();
        Round round = new Round();
        round.start();
    }
    
	private class Round extends Thread{
		String winner;
		boolean flag = false;
		
        public void run() {
            double viewRefreshInterval = 1000/FPS;
            //long currentTime=0;
            double nextRefreshTime = viewRefreshInterval + System.currentTimeMillis();
            CopyOnWriteArrayList<PlayerId> a = new CopyOnWriteArrayList<>();
            
            if(powerupsBool) {
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

            inputControl.stop();
            if(powerupsBool) {
                spawnerSettings.startGame().stop();
            }

            gameState.getSpaceships().stream().forEach(s -> a.add(s.getId()));
            
            switch(a.get(0).getGameId().toString()) {
            case "Player1":
            	p1 = p1 + 1;
            	if(p1.equals(roundsGame)) {
            		this.winner = "Player1";
            		this.flag = true;
            	}
            	break;
            	
            case "Player2":
            	p2 = p2 + 1;
            	if(p2.equals(roundsGame)) {
            		this.winner = "Player2";
            		this.flag = true;
            	}
            	break;
            	
            case "Player3":
            	p3 = p3 + 1;
            	if(p3.equals(roundsGame)) {
            		this.winner = "Player3";
            		this.flag = true;
            	}
            	break;
            	
            case "Player4":
            	p4 = p4 + 1;
            	if(p4.equals(roundsGame)) {
            		this.winner = "Player4";
            		this.flag = true;
            	}
            	break;
            	
            	default:
            		throw new UnsupportedOperationException();
            }
            
        	Platform.runLater(new Runnable() {

                @Override
                public void run() {
                	if(flag) {
                		p1 = 0;
                    	p2 = 0;
                    	p3 = 0;
                    	p4 = 0;
                    	flag = false;
                    	try {
                    		view.renderScene(view.getSceneFactory().createOver(winner));
                    	} catch (IOException e) {
                    		e.printStackTrace();
                    	}
                	}else {
                		try {
                			view.renderScene(view.getSceneFactory().createScoreboard(List.of(p1,p2,p3,p4), roundsGame));
                		} catch (IOException e) {
                			e.printStackTrace();
                		}
                	}
                }
            });
        }
    }
    
    
    protected void processInput() {
        this.inputControl.computeAll(this.gameState.getSpaceships());
    }
    
    protected void updateGame(double timePassedCycle) {
        this.gameState.update(timePassedCycle);
    }
    
    protected void render() {
        ( (GameScene) this.view.getScene() ).renderAll(this.gameState.getEntities().stream().filter(e -> !(e instanceof Obstacle) || ((Obstacle) e).isActive()).map(e -> e.getGraphicComponent()).toList());
    }
}
