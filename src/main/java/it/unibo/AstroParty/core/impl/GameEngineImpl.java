package it.unibo.AstroParty.core.impl;

import java.util.Collection;
import java.util.List;

import it.unibo.AstroParty.common.Position;
import it.unibo.AstroParty.core.api.GameEngine;
import it.unibo.AstroParty.core.api.View;
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
import it.unibo.AstroParty.model.api.Spaceship;
import it.unibo.AstroParty.model.impl.GameStateImpl;

/**
 * class for the core of the game: the gameLoop
 * @author dario
 *
 */

public class GameEngineImpl implements GameEngine, Runnable {
	
	private static final int FPS = 60;
	private GameState gameState;
	private SpawnerSettings spawnerSettings;
	private PowerUpFactory powerUpFactory;
	private SpaceshipBuilder spaceshipBuilder;
	private ObstacleFactory obstacleFactory;
	private double x1 = 25,x2 = 0,y1 = 25,y2 = 0,lx = 0,ly = 0,a1 = 0,a2 = 0,b1 = 0,b2 = 0,c1 = 0,c2 = 0,d1 = 0,d2 = 0;
	private View view;
	private Collection<Spaceship> spaceships;
	private InputControl inputControl;
	private GameScene gameScene;
	
	//Constructor
	public GameEngineImpl(View view, List<String> players, boolean obstacle, boolean powerup, int rounds) {
		this.view = view;
		spaceshipBuilder = new SpaceshipBuilderImpl();
		spaceshipBuilder.setNames(players);
		this.init();
	}
	
	public void init() {
		gameState = new GameStateImpl();
		
		/*
		//Set of the SpawnDelay and enumeration of PowerUpTypes
		spawnerSettings = new SpawnerSettingsImpl();
		spawnerSettings.enableAll();
		spawnerSettings.setSpawnDelay(20);
		
		//Set of 2 PowerUps on the map on two different fixed positions
		powerUpFactory = new PowerUpFactoryImpl();
		gameState.addPowerUp(powerUpFactory.createPowerUp(EntityType.DOUBLESHOT, new Position(x1,y1)));
		gameState.addPowerUp(powerUpFactory.createPowerUp(EntityType.SHIELD, new Position(x2,y2)));
		
		//Set Obstacles
		obstacleFactory = new ObstacleFactoryImpl();
		gameState.addObstacle(obstacleFactory.createLaser(new Position(lx, ly)));
		gameState.addObstacle(obstacleFactory.createSimpleObstacle(new Position(a1, a2)));
		gameState.addObstacle(obstacleFactory.createSimpleObstacle(new Position(b1, b2)));
		gameState.addObstacle(obstacleFactory.createSimpleObstacle(new Position(c1, c2)));
		gameState.addObstacle(obstacleFactory.createSimpleObstacle(new Position(d1, d2))); */
		
		//TODO set spaceship 
		this.spaceships = spaceshipBuilder.create(gameState);
		this.spaceships.forEach(s -> gameState.addSpaceship(s));
		this.inputControl = new InputControlImpl( this.spaceships);
		
		//TODO set view with gameScene as input
		try {
			view.switchScene(view.getSceneFactory().createGame(inputControl));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		gameScene = (GameScene) this.view.getScene();
	}

	public void mainLoop() {
		double viewRefreshInterval = 1000/FPS;
		long currentTime=0;
		double nextRefreshTime = viewRefreshInterval + System.currentTimeMillis();
		
		//start of Spawner of PowerUps
		//spawnerSettings.startGame().start(gameState);
		this.inputControl.start();
		while(!gameState.isOver()) {
			currentTime= System.currentTimeMillis();
			//System.out.println("current time:"+currentTime);
			
			
			processInput();
			
			updateGame(viewRefreshInterval);
			
			render();
			
			try {
				double surplusTime = nextRefreshTime - System.currentTimeMillis();
				
				if(surplusTime < 0) {
					surplusTime = 0;
				}
				 //surplusTime += 500;
				Thread.sleep((long) surplusTime);
				nextRefreshTime += viewRefreshInterval;
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	
	public InputControl getController() {
		return this.inputControl;
	}
	
	protected void processInput() {
		this.inputControl.compute();
	}
	
	protected void updateGame(double timePassedCycle) {
		gameState.update(timePassedCycle);
	}
	
	protected void render() {
		( (GameScene) this.view.getScene() ).renderAll(gameState.getEntities().stream().map(e -> e.getGraphicComponent()).toList());
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.mainLoop();
	}

}
