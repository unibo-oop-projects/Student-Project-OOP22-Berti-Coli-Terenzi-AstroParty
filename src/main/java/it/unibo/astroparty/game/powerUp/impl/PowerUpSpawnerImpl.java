package it.unibo.astroparty.game.powerUp.impl;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.astroparty.common.Position;
import it.unibo.astroparty.game.EntityType;
import it.unibo.astroparty.game.hitbox.api.CircleHitBox;
import it.unibo.astroparty.game.hitbox.impl.CircleHitBoxImpl;
import it.unibo.astroparty.game.logics.api.GameState;
import it.unibo.astroparty.game.powerUp.api.PowerUp;
import it.unibo.astroparty.game.powerUp.api.PowerUpFactory;
import it.unibo.astroparty.game.powerUp.api.PowerUpSpawner;

/**
 * concrete implementation of {@link PowerUpSpawner}.
 */
public class PowerUpSpawnerImpl implements PowerUpSpawner {

    private final Collection<EntityType> possiblePowerUpTypes = EnumSet.noneOf(EntityType.class);
    private final long spawnDelay;
    private GameState world;
    private final PowerUpFactory pUPfactory = new PowerUpFactoryImpl();

    private final Random random = new Random();

    private final Timer timer = new Timer();
    /**
     * 
     * @param possiblePowerUpTypes a collection of the possible types of PowerUPs.
     * @param spawnDelay the delay between spawns.
     */
    public PowerUpSpawnerImpl(final Collection<EntityType> possiblePowerUpTypes, final long spawnDelay) {
        this.possiblePowerUpTypes.addAll(possiblePowerUpTypes);
        this.spawnDelay = spawnDelay;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressFBWarnings(
        value = { 
            "EI_EXPOSE_REP2"
        },
        justification = "the powerUp needs to be added to the gamestate"
        + "and there is always a check before generating the powerUp"
    )
    @Override
    public void start(final GameState world) {
        this.world = world;
        this.timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                generate();
            }
        }, 2 * spawnDelay, spawnDelay);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop() {
        timer.cancel();
    }

    /**
     * creates and adds a new powerUp to the world.
     */
    private void generate() {
        //System.out.println("spawn");
        if (this.world != null && this.world.getPowerUps().size() < PowerUp.MAX_ON_SCREEN) {
            this.world.addPowerUp(this.pUPfactory.createPowerUp(this.generateType(), this.generatePos()));
        }
    }

    /**
     * generate the type of the new Power Up between the active ones in the world.
     * @return the {@link PowerUpTypes}
     */
    private EntityType generateType() {

        final int rand = random.nextInt(this.possiblePowerUpTypes.size());
        final var it = this.possiblePowerUpTypes.iterator();

        for (int i = 0; i < rand; i++) {
            it.next();    //non controllo perche' ho preso un num minore di size quindi deve esserci qualcosa;
        }

        return it.next();
    }

    /**
     * generates a possible {@link Position} for the new PowerUp.
     * @return the position.
     */
    private Position generatePos() {

        Position pos;

        do {
            pos = new Position(random.nextDouble(GameState.WIDTH - PowerUp.RELATIVE_SIZE * 2) + PowerUp.RELATIVE_SIZE,
                    random.nextDouble(GameState.HEIGHT - PowerUp.RELATIVE_SIZE * 2) + PowerUp.RELATIVE_SIZE);

        } while (canExist(pos));

        return pos;
    }

    private boolean canExist(final Position position) {
        final CircleHitBox hbox = new CircleHitBoxImpl(position, PowerUp.RELATIVE_SIZE);
        return  this.world.getEntities().stream()
                .map(entity -> entity.getHitBox())
                .anyMatch(e -> e.checkCircleCollision(hbox));
    }
}
