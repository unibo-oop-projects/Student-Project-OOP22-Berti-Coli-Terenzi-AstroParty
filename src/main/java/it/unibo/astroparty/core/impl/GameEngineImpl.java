package it.unibo.astroparty.core.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
import javafx.application.Platform;

/**
 * class for the core of the game: the gameLoop.
 * @author dario
 *
 */

public class GameEngineImpl implements GameEngine {
    private static final Logger LOGGER = Logger.getLogger("GameEngineImpl");
    private static final int FPS = 60;
    private GameStateImpl gameState;
    private SpawnerSettings spawnerSettings;
    private SpaceshipBuilder spaceshipBuilder;
    private final GameView view;
    private InputControl inputControl;
    private CollisionEventQueue collisionObserver;
    private int roundsGame;
    private boolean obstaclesBool;
    private boolean powerupsBool;
    private ObstacleFactory obstacleFactory;
    private Integer p1, p2, p3, p4;
    private static final Integer O1_X = 47, O1_Y = 27, O2_X = 47, O2_Y = 67, O3_Y = 7,
            O4_Y = 87, L1_X = O3_Y + 10, L2_X = O2_Y + 10;

    /**
     * Contructor of {@link GameEngine}.
     * @param view
     */
    public GameEngineImpl(final GameView view) {
        this.view = view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(final List<String> players, final boolean obstacle, final boolean powerup, final int rounds) {
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

    /**
     * creates the map every round based on the input of the settings of the Main Page.
     * it is called at the very beginning of the match and not after every round
     */
    private void createMap() {
        this.gameState = new GameStateImpl();
        this.inputControl = new InputControlImpl();
        this.collisionObserver = new CollisionEventQueue();
        //this.spaceshipBuilder = new SpaceshipBuilderImpl();

        createObstacles();

        if (this.powerupsBool) {
            createPowerups();
        }

        if (this.obstaclesBool) {
            createLasers();
        }

        this.gameState.registerObserver(this.collisionObserver);

        this.spaceshipBuilder.create(this.gameState).forEach(s -> this.gameState.addSpaceship(s));

        this.view.renderScene(this.view.getSceneFactory().createGame(this.inputControl));
    }

    /**
     * Sets the {@link SpawnerSettings}.
     */
    private void createPowerups() {
        this.spawnerSettings = new SpawnerSettingsImpl();
        this.spawnerSettings.enableAll();
    }

    /**
     * creates on the map the {@link Obstacle} by combining randomly from a collection of pairs of Obstacles.
     */
    private void createObstacles() {

        Set<Pair<Integer, Integer>> keySetObstacles;

        Set<Pair<Integer, Integer>> addedObstacles;
        Map<Pair<Integer, Integer>, Pair<Integer, Integer>> mapObstacles;
        Random rand;

        addedObstacles = new HashSet<>();
        mapObstacles = new HashMap<>();

        this.obstacleFactory = new ObstacleFactoryImpl();
        rand = new Random();

        Object[] arrayObstacles;
        Object a;
        int b, cont = 0;
        Pair<Integer, Integer> c;

        //Ostacolo fisso
        this.gameState.addObstacle(this.obstacleFactory.createUndestroyableObstacle(new Position(O1_X, O1_X)));

        if (this.obstaclesBool) {
            mapObstacles.put(new Pair<>(O1_X, O1_Y), new Pair<>(O2_X, O2_Y));
            mapObstacles.put(new Pair<>(O1_X, O3_Y), new Pair<>(O1_X, O4_Y));
            mapObstacles.put(new Pair<>(O3_Y, O1_X), new Pair<>(O4_Y, O1_X));
            mapObstacles.put(new Pair<>(O1_Y, O1_X), new Pair<>(O2_Y, O1_X));

            keySetObstacles = mapObstacles.keySet();
            arrayObstacles = keySetObstacles.toArray();
            b = arrayObstacles.length;
            while (cont < b / 2 && keySetObstacles.size() == b) {
                a = arrayObstacles[rand.nextInt(arrayObstacles.length)];
                c = mapObstacles.get(a);

                @SuppressWarnings("unchecked")
                final Pair<Integer, Integer> aPair = (Pair<Integer, Integer>) a;

                if (!addedObstacles.contains(new Pair<>(aPair.getX(), aPair.getY()))) {
                    this.gameState.addObstacle(this.obstacleFactory.
                    createSimpleObstacle(new Position(aPair.getX(), aPair.getY())));
                    this.gameState.addObstacle(this.obstacleFactory.
                    createSimpleObstacle(new Position(c.getX(), c.getY())));
                    addedObstacles.add(new Pair<>(aPair.getX(), aPair.getY()));
                    cont = cont + 1;
                }
            }
        }
    }

    /**
     *creates lasers on the map. 
     */
    private void createLasers() {
        this.gameState.addObstacle(this.obstacleFactory.createLaser(new Position(L1_X, O1_X)));
        this.gameState.addObstacle(this.obstacleFactory.createLaser(new Position(L2_X, O1_X)));
        this.gameState.addObstacle(this.obstacleFactory.createLaser(new Position(O1_X, L1_X)));
        this.gameState.addObstacle(this.obstacleFactory.createLaser(new Position(O1_X, L2_X)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mainLoop() {
        this.createMap();
        final Round round = new Round();
        round.start();
    }

    /**
     * round class.
     * @author dario
     *
     */
    private class Round extends Thread {
        private String winner;
        private boolean flag;

        /**
         * method that handles real time changes and everything that happen between rounds.
         */
        @Override
        public void run() {
            final double viewRefreshInterval = 1000 / FPS;
            //long currentTime=0;
            double nextRefreshTime = viewRefreshInterval + System.currentTimeMillis();
            final CopyOnWriteArrayList<PlayerId> a = new CopyOnWriteArrayList<>();

            if (powerupsBool) {
                spawnerSettings.startGame().start(gameState);
            }

            inputControl.start();
            while (!gameState.isOver()) {
                //currentTime= System.currentTimeMillis();
                //System.out.println("current time:"+currentTime);

                processInput();

                updateGame(viewRefreshInterval);

                collisionObserver.manageEvents(gameState);

                render();

                try {
                    double surplusTime = nextRefreshTime - System.currentTimeMillis();

                    if (surplusTime < 0) {
                        surplusTime = 0;
                    }
                    Thread.sleep((long) surplusTime);
                    nextRefreshTime += viewRefreshInterval;
                } catch (InterruptedException e) {
                    LOGGER.log(Level.SEVERE, "Error during thread sleep: ", e);
                }
            }

            inputControl.stop();
            if (powerupsBool) {
                spawnerSettings.startGame().stop();
            }

            gameState.getSpaceships().stream().forEach(s -> a.add(s.getId()));

            switch (a.get(0).getGameId().toString()) {
            case "Player1":
                p1 = p1 + 1;
                if (p1.equals(roundsGame)) {
                    this.winner = "Player1";
                    this.flag = true;
                }
                break;

            case "Player2":
                p2 = p2 + 1;
                if (p2.equals(roundsGame)) {
                    this.winner = "Player2";
                    this.flag = true;
                }
                break;

            case "Player3":
                p3 = p3 + 1;
                if (p3.equals(roundsGame)) {
                    this.winner = "Player3";
                    this.flag = true;
                }
                break;

            case "Player4":
                p4 = p4 + 1;
                if (p4.equals(roundsGame)) {
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
                    if (flag) {
                        p1 = 0;
                        p2 = 0;
                        p3 = 0;
                        p4 = 0;
                        flag = false;
                        try {
                            view.renderScene(view.getSceneFactory().createOver(winner));
                        } catch (IOException e) {
                            LOGGER.log(Level.SEVERE, "Error  during render of the game over scene: ", e);
                        }
                    } else {
                        try {
                            view.renderScene(view.getSceneFactory().createScoreboard(List.of(p1, p2, p3, p4), roundsGame));
                        } catch (IOException e) {
                            LOGGER.log(Level.SEVERE, "Error during render of the scoreboard scene", e);
                        }
                    }
                }
            });
        }
    }

    /**
     * decides the right time to pass all the {@link Spaceship to the {@link InputControl}.
     */
    protected void processInput() {
        this.inputControl.computeAll(this.gameState.getSpaceships());
    }

    /**
     * updates the time of {@link GameState} with real time.
     * @param timePassedCycle real time passed
     */
    protected void updateGame(final double timePassedCycle) {
        this.gameState.update(timePassedCycle);
    }

    /**
     * render to the {@link GameView} the active elements in the map.
     */
    protected void render() {
        ((GameScene) this.view.getScene()).renderAll(this.gameState.getEntities().stream()
                .filter(e -> !(e instanceof Obstacle) || ((Obstacle) e)
                .isActive())
                .map(e -> e.getGraphicComponent())
                .toList());
    }
}
