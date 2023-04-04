package it.unibo.astroparty.game.spaceship.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import it.unibo.astroparty.common.Direction;
import it.unibo.astroparty.common.Position;
import it.unibo.astroparty.core.impl.PlayerId;
import it.unibo.astroparty.game.api.GameState;
import it.unibo.astroparty.game.spaceship.api.Spaceship;
import it.unibo.astroparty.game.spaceship.api.SpaceshipBuilder;
import it.unibo.astroparty.input.api.GameId;

/**
 * 
 * a concrete implementation of {@link SpaceshipBuilder} 
 * that can be used to create the same Spaceships for multiple matches inside a game.
 */
public class SpaceshipBuilderImpl implements SpaceshipBuilder {

// costanti per le impostazione basi di pos e dir dei 4 player
    private static final Logger LOGGER = Logger.getLogger("SpaceshipBuilderController");

    private static final double BORDER_DISTANCE = 5.0;

    private static final double ANGLE_P1 = 45;
    private static final double ANGLE_P2 = 225;
    private static final double ANGLE_P3 = 315;
    private static final double ANGLE_P4 = 135;

    private static final Position POSITION_P1 =
            new Position(Spaceship.RELATIVE_SIZE + BORDER_DISTANCE,
                Spaceship.RELATIVE_SIZE + BORDER_DISTANCE);
    private static final Position POSITION_P2 =
            new Position(GameState.WIDTH - Spaceship.RELATIVE_SIZE - BORDER_DISTANCE,
                GameState.HEIGHT - Spaceship.RELATIVE_SIZE - BORDER_DISTANCE);
    private static final Position POSITION_P3 =
            new Position(Spaceship.RELATIVE_SIZE + BORDER_DISTANCE,
                    GameState.HEIGHT - Spaceship.RELATIVE_SIZE - BORDER_DISTANCE);
    private static final Position POSITION_P4 =
            new Position(GameState.WIDTH - Spaceship.RELATIVE_SIZE - BORDER_DISTANCE,
                    Spaceship.RELATIVE_SIZE + BORDER_DISTANCE);

    private static final Direction DIRECTION_P1 = new Direction(1, 1);
    private static final Direction DIRECTION_P2 = new Direction(-1, -1);
    private static final Direction DIRECTION_P3 = new Direction(1, -1);
    private static final Direction DIRECTION_P4 = new Direction(-1, 1);

    // variabili usate per creare la Spaceship
    private double baseSpeed;
    private int maxBullets;
    private long rechargeTime;
    private boolean startingShield;

    //variabli usate per caricare i parametri dal file di config
    private static final String SEP = File.separator;
    private static final String SPEED = "speed: ";
    private static final String BULLETS = "maxBullets: ";
    private static final String SHIELD = "startingShield: ";
    private static final String TIME = "time: ";
    private static final String FILE_NAME = System.getProperty("user.dir") + SEP
            + "src" + SEP + "main" + SEP + "resources" + SEP + "default_settings" + SEP + "SpaceshipBuilder_config.yml ";

    private final Collection<PlayerId> playerIds = new HashSet<>();

    /**
     * uploads a basic configuration using {@link #uploadBasicConfig()}.
     */
    public SpaceshipBuilderImpl() {
        this.uploadBasicConfig();
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public void setSpeed(final double speed) {
        if (this.stopInput()) {
            return;
        }
        this.baseSpeed = speed;
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public void setMaxBullets(final int maxBullets) {
        if (this.stopInput()) {
            return;
        }

        this.maxBullets = maxBullets;
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public void setRechargeTime(final long time) {
        if (this.stopInput()) {
            return;
        }

        this.rechargeTime = time;
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public void setStartingShield(final boolean enable) {
        if (this.stopInput()) {
            return;
        }

        this.startingShield = enable;
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public void setids(final Collection<PlayerId> playersId) {
        if (this.stopInput()) {
            return;
        }
        this.playerIds.addAll(playersId);
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public void setNames(final Collection<String> playerNames) {

        if (this.stopInput()) {
            return;
        }

        for (final String name : playerNames) {

            if (this.playerIds.stream()
                .map(p -> p.getPlayerName())
                .filter(e -> e.equals(name)).findAny().isEmpty()) {

                switch (this.playerIds.size()) {

                    case 0:
                        this.playerIds.add(new PlayerId(name, GameId.PLAYER1));
                        break;

                    case 1:
                        this.playerIds.add(new PlayerId(name, GameId.PLAYER2));
                        break;

                    case 2:
                        this.playerIds.add(new PlayerId(name, GameId.PLAYER3));
                        break;

                    case 3:
                        this.playerIds.add(new PlayerId(name, GameId.PLAYER4));
                        break;

                        default:
                        throw new UnsupportedOperationException();
                }
            }
        }
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public Collection<Spaceship> create(final GameState world) {

        return this.playerIds.stream()
                .map(id -> new SpaceshipImpl(getPos(id),
                                            getDir(id),
                                            getAngle(id),
                                            world,
                                            baseSpeed,
                                            maxBullets,
                                            startingShield,
                                            id,
                                            rechargeTime))
                .collect(Collectors.toSet());
    }

    /**
     * 
     * @param id
     * @return the Direction on spawn for the spaceship with the given Id.
     */
    private double getAngle(final PlayerId id) {
        switch (id.getGameId()) {
            case PLAYER1:
                return ANGLE_P1;

            case PLAYER2:
                return ANGLE_P2;

            case PLAYER3:
                return ANGLE_P3;

            case PLAYER4:
                return ANGLE_P4;

            default:
                throw new UnsupportedOperationException();
        }
    }

    /**
     * 
     * @param id
     * @return the Angle on spawn for the spaceship with the given Id.
     */
    private Direction getDir(final PlayerId id) {
        switch (id.getGameId()) {
            case PLAYER1:
                return DIRECTION_P1;

            case PLAYER2:
                return DIRECTION_P2;

            case PLAYER3:
                return DIRECTION_P3;

            case PLAYER4:
                return DIRECTION_P4;

            default:
                throw new UnsupportedOperationException();
        }
    }

    /**
     * 
     * @param id
     * @return the Position on spawn for the spaceship with the given Id.
     */
    private Position getPos(final PlayerId id) {

        switch (id.getGameId()) {
            case PLAYER1:
                return POSITION_P1;

            case PLAYER2:
                return POSITION_P2;

            case PLAYER3:
                return POSITION_P3;

            case PLAYER4:
                return POSITION_P4;

            default:
                throw new UnsupportedOperationException();
        }
    }

    /**
     * if {@link #setids(Collection)} or {@link #setNames(Collection)} have been called the input has to stop being taken.
     * only {@link #create(GameState)} can be used.
     * @return true if this builder has to spo reciving input
     */
    private boolean stopInput() {
        return !(this.playerIds == null || this.playerIds.isEmpty());
    }

    /**
     * set all the parameters to the basic ones, taking them from the SpaceshipBuilder_config.yaml file.
     */
    private void uploadBasicConfig() {
        String line;
        int ind;

        try (
                BufferedReader r = new BufferedReader(new FileReader(FILE_NAME, StandardCharsets.UTF_8))
       ) {
            while ((line = r.readLine()) != null) { // NOPMD
                //suppressed as it is a false positive

                if (line.contains(SPEED)) {
                    ind = line.indexOf(SPEED);
                    this.baseSpeed = Double.parseDouble(line.substring(ind + SPEED.length()));

                } else if (line.contains(BULLETS)) {
                    ind = line.indexOf(BULLETS);
                    this.maxBullets  = Integer.parseInt(line.substring(ind + BULLETS.length()));

                } else if (line.contains(TIME)) {
                    ind = line.indexOf(TIME);
                    this.rechargeTime = Long.parseLong(line.substring(ind + TIME.length()));

                } else if (line.contains(SHIELD)) {
                    ind = line.indexOf(SHIELD);
                    this.startingShield = Boolean.parseBoolean(line.substring(ind + SHIELD.length()));
                }
            }
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, " file " + FILE_NAME + " non trovato");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, " errore nella lettura di " + FILE_NAME);
        }
    }
}
