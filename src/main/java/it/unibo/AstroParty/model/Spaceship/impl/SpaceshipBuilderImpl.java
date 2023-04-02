package it.unibo.AstroParty.model.Spaceship.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
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
 * a concrete implementation of {@link SpaceshipBuilder} that can be used to create the same Spaceships for multiple matches inside a game
 */
public class SpaceshipBuilderImpl implements SpaceshipBuilder {
	
	// costanti per le impostazione basi di pos e dir dei 4 player
	
	private static double BorderDistance = 5.0;
	
	private static double angleP1 = 45;
	private static double angleP2 = 225;
	private static double angleP3 = 315;
	private static double angleP4 = 135;
	
	private static Position positionP1 = new Position(Spaceship.relativeSize + BorderDistance,
														Spaceship.relativeSize + BorderDistance);
	private static Position positionP2 = new Position( GameState.WIDTH - Spaceship.relativeSize - BorderDistance ,
														GameState.HEIGHT - Spaceship.relativeSize - BorderDistance);
	private static Position positionP3 = new Position(Spaceship.relativeSize + BorderDistance,
														GameState.HEIGHT - Spaceship.relativeSize - BorderDistance);
	private static Position positionP4 = new Position(GameState.WIDTH - Spaceship.relativeSize - BorderDistance ,
														Spaceship.relativeSize + BorderDistance);;
	
	private static Direction directionP1 = new Direction( 1 , 1 );
	private static Direction directionP2 = new Direction( -1 , -1 );
	private static Direction directionP3 = new Direction( 1 , -1 );
	private static Direction directionP4 = new Direction( -1 , 1 );

	// variabili usate per creare la Spaceship
	private double baseSpeed;
	private int maxBullets;
	private long rechargeTime;
	private boolean startingShield;
	
	//variabli usate per caricare i parametri dal file di config \\src\\main\\resources\\default_settings\\SpaceshipBuilder_config.yml
	public static final String SEP = File.separator ;
	private static final String SPEED = "speed: ";
	private static final String BULLETS = "maxBullets: ";
	private static final String SHIELD = "startingShield: ";
	private static final String TIME = "time: ";
	private final String FILE_NAME = System.getProperty ("user.dir") + SEP + "src" + SEP + "main" + SEP + "resources" + SEP + "default_settings" + SEP + "SpaceshipBuilder_config.yml ";
	private Collection<PlayerId> playerIds;
	
	/**
	 * uploads a basic configuration using {@link #uploadBasicConfig()}
	 */
	public SpaceshipBuilderImpl(){
		this.uploadBasicConfig();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setSpeed(double speed) {
		if ( this.stopInput() ) {
			return ;
		}
		this.baseSpeed = speed;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setMaxBullets(int maxBullets) {
		if ( this.stopInput() ) {
			return ;
		}

		this.maxBullets = maxBullets;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setRechargeTime(long time) {
		if ( this.stopInput() ) {
			return ;
		}

		this.rechargeTime = time ;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setStartingShield(boolean enable) {
		if ( this.stopInput() ) {
			return ;
		}

		this.startingShield = enable;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setids(Collection<PlayerId> playerIds) {
		if ( this.stopInput() ) {
			return ;
		}
		this.playerIds = playerIds;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setNames(Collection<String> playerNames) {
		
		if ( this.stopInput() ) {
			return ;
		}
		
		this.playerIds = new HashSet<>();
		
		for ( String name : playerNames ) {

			if( this.playerIds.stream()
				.map(p -> p.getPlayerName())
				.filter( e -> e.equals(name) ).findAny().isEmpty()	){

				switch( this.playerIds.size() ) {
			
					case 0:
						this.playerIds.add( new PlayerId( name, GameId.Player1 ));
						break;
						
					case 1:
						this.playerIds.add( new PlayerId( name, GameId.Player2 ));
						break;
						
					case 2:
						this.playerIds.add( new PlayerId( name, GameId.Player3 ));
						break;
						
					case 3:
						this.playerIds.add( new PlayerId( name, GameId.Player4 ));
						break;
				}
			}
		}
		
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Collection<Spaceship> create(GameState world) {
		
		return this.playerIds.stream()
				.map( id -> new SpaceshipImpl(  getPos(id),
												getDir(id),
												getAngle(id),
												world,
												baseSpeed,
												maxBullets,
												startingShield,
												id,
												rechargeTime))
				.collect( Collectors.toSet());
	}
	
	/**
	 * 
	 * @param id
	 * @return the Direction on spawn for the spaceship with the given Id
	 */
	private double getAngle(PlayerId id) {
		switch( id.getGameId() ){
			case Player1:
				return angleP1;

			case Player2:
				return angleP2;

			case Player3:
				return angleP3;

			case Player4:
				return angleP4;

			default:
				throw new UnsupportedOperationException();
		}
	}

	/**
	 * 
	 * @param id
	 * @return the Angle on spawn for the spaceship with the given Id
	 */
	private Direction getDir(PlayerId id) {
		switch( id.getGameId() ){
			case Player1:
				return directionP1;

			case Player2:
				return directionP2;

			case Player3:
				return directionP3;

			case Player4:
				return directionP4;

			default:
				throw new UnsupportedOperationException();
		}
	}

	/**
	 * 
	 * @param id
	 * @return the Position on spawn for the spaceship with the given Id
	 */
	private Position getPos(PlayerId id) {
		
		switch( id.getGameId() ){
			case Player1:
				return positionP1;

			case Player2:
				return positionP2;
				
			case Player3:
				return positionP3;
				
			case Player4:
				return positionP4;
				
			default:
				throw new UnsupportedOperationException();
		}
	}
	

	/**
	 * if {@link #setids(Collection)} or {@link #setNames(Collection)} have been called the input has to stop being taken
	 * only {@link #create(GameState)} can be used
	 * @return
	 */
	private boolean stopInput() {
		return  ! ( this.playerIds == null || this.playerIds.isEmpty() )  ;
	}

	/**
	 * set all the parameters to the basic ones, taking them from the SpaceshipBuilder_config.yaml file
	 */
	private void uploadBasicConfig() {
	String line ;
	int ind;
	
	try (
			final BufferedReader r = new BufferedReader (new FileReader ( FILE_NAME  ) )
	) {
		//TODO rendi sta cosa carina
		while ( ( line = r. readLine () ) != null ) {
			
			if( line.contains( SPEED )) {
				ind = line.indexOf( SPEED );
				this.baseSpeed = Double.parseDouble( line.substring( ind + SPEED.length() ) );
				
			} else if( line.contains( BULLETS )) {
				ind = line.indexOf( BULLETS );
				this.maxBullets  = Integer.parseInt( line.substring( ind + BULLETS.length() ) );
				
			}else if( line.contains( TIME )) {
				ind = line.indexOf( TIME );
				this.rechargeTime = Long.parseLong( line.substring( ind + TIME.length() ) );
				
			}else if( line.contains( SHIELD )) {
				ind = line.indexOf( SHIELD );
				this.startingShield = Boolean.parseBoolean( line.substring( ind + SHIELD.length() ) );
				
			}
			
		}
		
	} catch (FileNotFoundException e) {
		System.out.println(" file " + this.FILE_NAME + " non trovato ");
		e.printStackTrace();
	} catch (IOException e) {
		System.out.println(" errore nella lettura di " + this.FILE_NAME);
		e.printStackTrace();
	}
	}
	
}
