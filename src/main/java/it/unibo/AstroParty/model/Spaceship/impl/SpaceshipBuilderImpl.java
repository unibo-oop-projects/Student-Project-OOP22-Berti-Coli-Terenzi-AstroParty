package it.unibo.AstroParty.model.Spaceship.impl;

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

import it.unibo.AstroParty.common.Direction;
import it.unibo.AstroParty.common.Position;
import it.unibo.AstroParty.core.impl.PlayerId;
import it.unibo.AstroParty.input.api.GameId;
import it.unibo.AstroParty.model.Spaceship.api.SpaceshipBuilder;
import it.unibo.AstroParty.model.api.GameState;
import it.unibo.AstroParty.model.api.Spaceship;

/**
 * 
 * a concrete implementation of {@link SpaceshipBuilder} 
 * that can be used to create the same Spaceships for multiple matches inside a game.
 */
public class SpaceshipBuilderImpl implements SpaceshipBuilder {

// costanti per le impostazione basi di pos e dir dei 4 player
    private final Logger logger = Logger.getLogger("SpaceshipBuilderController");

    private final double borderDistance = 5.0;

    private final double angleP1 = 45;
    private final double angleP2 = 225;
    private final double angleP3 = 315;
    private final double angleP4 = 135;

    private final Position positionP1 =
            new Position(Spaceship.RELATIVE_SIZE + borderDistance,
                Spaceship.RELATIVE_SIZE + borderDistance);
    private final Position positionP2 =
            new Position(GameState.WIDTH - Spaceship.RELATIVE_SIZE - borderDistance,
                GameState.HEIGHT - Spaceship.RELATIVE_SIZE - borderDistance);
    private final Position positionP3 =
            new Position(Spaceship.RELATIVE_SIZE + borderDistance,
                    GameState.HEIGHT - Spaceship.RELATIVE_SIZE - borderDistance);
    private final Position positionP4 =
            new Position(GameState.WIDTH - Spaceship.RELATIVE_SIZE - borderDistance,
                    Spaceship.RELATIVE_SIZE + borderDistance);

    private final Direction directionP1 = new Direction(1, 1);
    private final Direction directionP2 = new Direction(-1, -1);
    private final Direction directionP3 = new Direction(1, -1);
    private final Direction directionP4 = new Direction(-1, 1);

    // variabili usate per creare la Spaceship
    private double baseSpeed;
    private int maxBullets;
    private long rechargeTime;
    private boolean startingShield;

    //variabli usate per caricare i parametri dal file di config
    private final String sep = File.separator;
    private final String speed = "speed: ";
    private final String bullets = "maxBullets: ";
    private final String shield = "startingShield: ";
    private final String time = "time: ";
    private final String fileName = System.getProperty("user.dir") + sep
            + "src" + sep + "main" + sep + "resources" + sep + "default_settings" + sep + "SpaceshipBuilder_config.yml ";

    private Collection<PlayerId> playerIds= new HashSet<>();

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
                return angleP1;

            case PLAYER2:
                return angleP2;

            case PLAYER3:
                return angleP3;

            case PLAYER4:
                return angleP4;

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
                return directionP1;

            case PLAYER2:
                return directionP2;

            case PLAYER3:
                return directionP3;

            case PLAYER4:
                return directionP4;

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
                return positionP1;

            case PLAYER2:
                return positionP2;

            case PLAYER3:
                return positionP3;

            case PLAYER4:
                return positionP4;

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
    @SuppressWarnings("PMD.AssignmentInOperand") 
    private void uploadBasicConfig() {
        String line;
        int ind;

        try (
                BufferedReader r = new BufferedReader(new FileReader(fileName, StandardCharsets.UTF_8))
       ) {
            while ((line = r.readLine()) != null) {

                if (line.contains(speed)) {
                    ind = line.indexOf(speed);
                    this.baseSpeed = Double.parseDouble(line.substring(ind + speed.length()));

                } else if (line.contains(bullets)) {
                    ind = line.indexOf(bullets);
                    this.maxBullets  = Integer.parseInt(line.substring(ind + bullets.length()));

                } else if (line.contains(time)) {
                    ind = line.indexOf(time);
                    this.rechargeTime = Long.parseLong(line.substring(ind + time.length()));

                } else if (line.contains(shield)) {
                    ind = line.indexOf(shield);
                    this.startingShield = Boolean.parseBoolean(line.substring(ind + shield.length()));
                }
            }
        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, " file " + this.fileName + " non trovato");
        } catch (IOException e) {
            logger.log(Level.SEVERE, " errore nella lettura di " + this.fileName);
        }
    }
}
