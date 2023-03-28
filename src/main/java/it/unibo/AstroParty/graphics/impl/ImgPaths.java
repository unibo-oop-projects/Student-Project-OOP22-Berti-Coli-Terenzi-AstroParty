package it.unibo.AstroParty.graphics.impl;

import it.unibo.AstroParty.graphics.api.GraphicEntity;
import it.unibo.AstroParty.input.api.GameId;

public class ImgPaths {
	// gli ostacoli sono un problema mio? (credo di si)
	//TODO trova le immagini e metti i path
	private static final String PathToP1 = null;
	private static final String PathToP2 = null;
	private static final String PathToP3 = null;
	private static final String PathToP4 = null;
	private static final String PathToShield = null;
	private static final String PathToImmortality = null;
	private static final String PathToDoubleshot = null;
	private static final String PathToSpeed = null;
	private static final String PathToLaser = null;
	private static final String PathToBasicObstacle = null;
	

	
	public String getImg( GraphicEntity entity ) {
		String s = null;
		
		switch( entity.getType() ) {
		
			case SPACESHIP:
				s = this.getSpaceshipimg( entity.getId().get() );
				break;
				
			case SHIELD:
				s = PathToShield;
				break;
				
			case IMMORTALITY:
				s = PathToImmortality;
				break;
				
			case DOUBLESHOT:
				s = PathToDoubleshot;
				break;
				
			case UPGRADEDSPEED:
				s = PathToSpeed;
				break;
				
			case LASER:
				s = PathToLaser;
				break;
				
			case SIMPLEOBSTACLE:
				s = PathToBasicObstacle;
				break;
				
			default:
				throw( new UnsupportedOperationException() );	
		}
		return s;
		
		
	}
	
	private String getSpaceshipimg( GameId id) {
		String s = null;
		
		switch( id ) {
			case Player1:
				s = PathToP1;
				break;
				
			case Player2:
				s = PathToP2;
				break;
				
			case Player3:
				s = PathToP3;
				break;
				
			case Player4:
				s = PathToP4;
				break;
				
			default:
				throw( new UnsupportedOperationException() );	
		}
		
		return s;
	}

}