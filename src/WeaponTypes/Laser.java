package WeaponTypes;

import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;

import Game.Bullet;
import Game.GameData;

public class Laser implements WeaponType{
	
	int power=0;
	int ttl=100;
	int dmg=100;
	int delay=0;
	Bullet[] laser = new Bullet[2];
	
	public String getName() {
		return "Laser";
	}

	@Override
	public int getPower() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void fire(Sound sound, int bulletDirection){
		if(delay==0) {
			float x = GameData.player.getShape().getCenterX()+10;
			float y = GameData.player.getShape().getCenterY()+3;
			int size = 4 + power;
			laser[0] = new Bullet(new Rectangle(x,y+3,50,2),20*bulletDirection,0,ttl,dmg,false);
			laser[1] = new Bullet(new Rectangle(x,y-3,50,2),20*bulletDirection,0,ttl,dmg,false);
			for (int i=0; i < laser.length;i++) {
				GameData.bullets.add(laser[i]);
			}
			sound.play();
			delay=5;	
		}
		else {
			delay--;
		}
	}

	public void increasePower() {
		if (power < 3) {
			power++;
		}
		
	}
	public void decreasePower() {
		if (power > 1) {
			power--;
		}	
	}	
}