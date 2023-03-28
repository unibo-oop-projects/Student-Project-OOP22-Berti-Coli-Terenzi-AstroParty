package it.unibo.AstroParty.graphics.impl;

import java.io.File;
import it.unibo.AstroParty.graphics.api.GraphicEntity;
import it.unibo.AstroParty.input.api.GameId;

public class ImgNames {
	private static final String SEP = File.separator ;
    private static final String P1 =  "p1";
    private static final String P2 = "p2";
    private static final String P3 = "p3";
    private static final String P4 = "p4";
    private static final String Shield = "Shield";				//TODO trova e aggiungi
    private static final String Immortality = "Immortality";		//TODO trova e aggiungi
    private static final String Doubleshot = "Doubleshot";			//TODO trova e aggiungi
    private static final String Speed = "Speed";				//TODO trova e aggiungi
    private static final String Laser = "Laser";
    private static final String BasicObstacle = "BasicObstacle";
    private static final String Projectile = "Projectile";


	public String getName( GraphicEntity entity ) {
		String s =null ;
		switch( entity.getType() ) {
		
			case SPACESHIP:
				s = this.getSpaceshipimg( entity.getId().get() );
				break;

			case SHIELD:
				s = Shield;
				break;

			case IMMORTALITY:
				s = Immortality;
				break;

			case DOUBLESHOT:
				s = Doubleshot;
				break;

			case UPGRADEDSPEED:
				s = Speed;
				break;

			case LASER:
				s = Laser;
				break;

			case SIMPLEOBSTACLE:
				s = BasicObstacle;
				break;

			case PROJECTILE:
				s = Projectile;
				break;
				
			default:
				throw( new UnsupportedOperationException() );
		}
		return s;           
		
	}
	
	private String getSpaceshipimg( GameId id) {

		switch( id ) {
			case Player1:
				return P1;
				
			case Player2:
				return P2;
				
			case Player3:
				return P3;
				
			case Player4:
				return P4;
				
			default:
				throw( new UnsupportedOperationException() );	
		}
	}

}