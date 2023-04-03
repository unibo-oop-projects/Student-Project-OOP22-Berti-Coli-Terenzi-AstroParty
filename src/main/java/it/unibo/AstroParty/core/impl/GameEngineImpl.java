package it.unibo.AstroParty.core.impl;

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
	private double x1 = 50,x2 = 50,y1 = 25,y2 = 0,lx = 0,ly = 0,a1 = 50,a2 = 50,b1 = 50,b2 = 35,c1 = 0,c2 = 0,d1 = 0,d2 = 0;
	private View view;
	private Collection<Spaceship> spaceships;
	private InputControl inputControl;
	private GameScene gameScene;
	private CollisionObserver collisionObserver;
	private Map<Pair<Integer, Integer>, Pair<Integer, Integer>> mapObstacles = new HashMap<>();
	
	//Constructor
	public GameEngineImpl(View view, List<String> players, boolean obstacle, boolean powerup, int rounds) {
		this.view = view;
		spaceshipBuilder = new SpaceshipBuilderImpl();
		spaceshipBuilder.setNames(players);
		this.init();
	}
	
	public void init() {
		gameState = new GameStateImpl();
		collisionObserver = new CollisionObserver();
		gameState.registerObserver(collisionObserver);
		
		
		
		/*
		//Set of the SpawnDelay and enumeration of PowerUpTypes
		spawnerSettings = new SpawnerSettingsImpl();
		spawnerSettings.enableAll();
		
		//Set of 2 PowerUps on the map on two different fixed positions
		powerUpFactory = new PowerUpFactoryImpl();
		gameState.addPowerUp(powerUpFactory.createPowerUp(EntityType.DOUBLESHOT, new Position(20,40)));
		gameState.addPowerUp(powerUpFactory.createPowerUp(EntityType.SHIELD, new Position(40,20)));
		gameState.addPowerUp(powerUpFactory.createPowerUp(EntityType.DOUBLESHOT, new Position(x1,y1)));
		gameState.addPowerUp(powerUpFactory.createPowerUp(EntityType.SHIELD, new Position(x2,y2)));
		*/
		
		
		/*/Set Obstacles
		obstacleFactory = new ObstacleFactoryImpl();
		gameState.addObstacle(obstacleFactory.createLaser(new Position(lx, ly)));
		gameState.addObstacle(obstacleFactory.createSimpleObstacle(new Position(a1, a2)));
		gameState.addObstacle(obstacleFactory.createSimpleObstacle(new Position(b1, b2)));
		gameState.addObstacle(obstacleFactory.createSimpleObstacle(new Position(c1, c2)));
		gameState.addObstacle(obstacleFactory.createSimpleObstacle(new Position(d1, d2))); */

		obstacleFactory = new ObstacleFactoryImpl();
		gameState.addObstacle(obstacleFactory.createUndestroyableObstacle(new Position(25, 25)));
		gameState.addObstacle(obstacleFactory.createSimpleObstacle(new Position(75, 75)));
		//gameState.addObstacle(obstacleFactory.createLaser(new Position(lx, ly)));
		
		//Ostacolo fisso
		gameState.addObstacle(obstacleFactory.createSimpleObstacle(new Position(50, 50)));
		
		
		this.mapObstacles.put(new Pair<>(50, 30), new Pair<>(50, 10));
		this.mapObstacles.put(new Pair<>(50, 10), new Pair<>(50, 90));
		this.mapObstacles.put(new Pair<>(10, 50), new Pair<>(90, 50));
		this.mapObstacles.put(new Pair<>(30, 50), new Pair<>(70, 50));
		
		Set<Pair<Integer, Integer>> keySetObstacles = new HashSet<>();
		Object[] arrayObstacles;
		Random rand = new Random();
		
		keySetObstacles = this.mapObstacles.keySet();
			
		arrayObstacles = keySetObstacles.toArray();
			
		//COSI' NE HO GENERATA SOLO UNA RANDOM, DA FARE UN WHILE CONT==4 + CON UN CONTROLLO DENTRO IL WHILE CHE NON ABBIA GENERATO DUE UGUALI
		Object a = arrayObstacles[rand.nextInt(arrayObstacles.length)];
		int b = arrayObstacles.length;
		Pair<Integer, Integer> c = mapObstacles.get(a);
		
		System.out.print("LUNGHEZZA DI ARRAYOBSTACLES"+b);
		System.out.print("VALORE RANDOM DELLA KEY"+a);	
		System.out.print("VALORE IN MAPPA DELLA KEY"+c);
		//ORA HO "a" CHE E' UNA KEY RANDOM, POI A TALE KEY PRENDO LA SUA CORRISPETTIVA VALUE LEGGENDO DA MAPOBSTACLES

		
		gameState.addObstacle(obstacleFactory.createSimpleObstacle(new Position(50, 10)));
		gameState.addObstacle(obstacleFactory.createSimpleObstacle(new Position(50, 30)));
		
		gameState.addObstacle(obstacleFactory.createSimpleObstacle(new Position(70, 50)));
		gameState.addObstacle(obstacleFactory.createSimpleObstacle(new Position(90, 50)));
		
		gameState.addObstacle(obstacleFactory.createSimpleObstacle(new Position(10, 50)));
		gameState.addObstacle(obstacleFactory.createSimpleObstacle(new Position(30, 50)));
		
		gameState.addObstacle(obstacleFactory.createSimpleObstacle(new Position(50, 70)));
		gameState.addObstacle(obstacleFactory.createSimpleObstacle(new Position(50, 90)));
		
		
		
		/*POSIZIONI POWERUPS
		gameState.addObstacle(obstacleFactory.createSimpleObstacle(new Position(10, 30)));
		gameState.addObstacle(obstacleFactory.createSimpleObstacle(new Position(90, 70)));
		
		gameState.addObstacle(obstacleFactory.createSimpleObstacle(new Position(30, 10)));
		gameState.addObstacle(obstacleFactory.createSimpleObstacle(new Position(70, 90)));
		
		gameState.addObstacle(obstacleFactory.createSimpleObstacle(new Position(30, 30)));
		gameState.addObstacle(obstacleFactory.createSimpleObstacle(new Position(70, 70)));
		
		gameState.addObstacle(obstacleFactory.createSimpleObstacle(new Position(70, 10)));
		gameState.addObstacle(obstacleFactory.createSimpleObstacle(new Position(30, 90)));

		gameState.addObstacle(obstacleFactory.createSimpleObstacle(new Position(70, 30)));
		gameState.addObstacle(obstacleFactory.createSimpleObstacle(new Position(30, 70)));
		
		gameState.addObstacle(obstacleFactory.createSimpleObstacle(new Position(90, 30)));
		gameState.addObstacle(obstacleFactory.createSimpleObstacle(new Position(10, 70)));
		*/
		
		
		
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
		Round round = new Round();
		round.start();
	}
	
	private class Round extends Thread{
		public void run() {
			double viewRefreshInterval = 1000/FPS;
			long currentTime=0;
			double nextRefreshTime = viewRefreshInterval + System.currentTimeMillis();
			
			//start of Spawner of PowerUps
			spawnerSettings.startGame().start(gameState);
			inputControl.start();
			while(!gameState.isOver()) {
				currentTime= System.currentTimeMillis();
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
		( (GameScene) this.view.getScene() ).renderAll(gameState.getEntities().stream().filter(e -> !(e instanceof Obstacle) || ((Obstacle) e).isActive()).map(e -> e.getGraphicComponent()).toList());
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.mainLoop();
	}

}
