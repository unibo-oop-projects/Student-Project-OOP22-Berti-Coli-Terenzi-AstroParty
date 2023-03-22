package it.unibo.AstroParty.model.Spaceship.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

import it.unibo.AstroParty.core.impl.PlayerId;
import it.unibo.AstroParty.input.api.GameId;
import it.unibo.AstroParty.model.Spaceship.api.SpaceshipBuilder;
import it.unibo.AstroParty.model.api.GameState;
import it.unibo.AstroParty.model.api.Spaceship;

/**
 * 
 * @author Alessandro Coli
 * a concrete implementation of {@link SpaceshipBuilder}
 */
public class SpaceshipBuilderImpl implements SpaceshipBuilder {
	
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
	
	public SpaceshipBuilderImpl(){
		this.uploadBasicConfig();
	}

	@Override
	public void setSpeed(double speed) {
		this.baseSpeed = speed;
	}

	@Override
	public void setMaxBullets(int maxBullets) {

		this.maxBullets = maxBullets;
	}

	@Override
	public void setRechargeTime(long time) {

		this.rechargeTime = time ;
	}

	@Override
	public void setStartingShield(boolean enable) {

		this.startingShield = enable;
	}

	@Override
	public void setids(Collection<PlayerId> playerIds) {
		this.playerIds = playerIds;
	}

	@Override
	public void setNames(Collection<String> playerNames) {
		
		if( ! ( this.playerIds == null || this.playerIds.isEmpty() ) ) {
			return ;
		}
		
		this.playerIds = new HashSet<>();
		
		for ( String name : playerNames ) {
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

	@Override
	public Collection<Spaceship> create(GameState world) {
		
		return this.playerIds.stream()
				.distinct() 	// controlla se ci sono due nomi o GameId uguali
				.map( id -> new SpaceshipImpl(world,baseSpeed , maxBullets, startingShield, id, rechargeTime))
				.collect( Collectors.toSet());
	}
	
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
