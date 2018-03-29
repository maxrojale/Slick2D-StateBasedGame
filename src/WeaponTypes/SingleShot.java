package WeaponTypes;

import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

import Game.Bullet;
import Game.GameData;

public class SingleShot implements WeaponType {
	
	int power=1;
	int ttl = 25;
	int dmg = 50;
	int delay = 0;
	public String getName() {
		return "Single Shot";
	}

	@Override
	public int getPower() {
		return power;
	}
	
	public void fire(Sound sound, int bulletDirection) {
		
		if(delay==0) {
			Bullet[] bullet = new Bullet[power];
			float x = GameData.player.getShape().getCenterX()+10;
			float y = GameData.player.getShape().getCenterY();
			int size = 4 + power;
			bullet[0] = new Bullet(new Circle(x,y,size),20*bulletDirection,0,ttl,dmg,true);
			for (int i =0; i < bullet.length; i++) {
				GameData.bullets.add(bullet[i]);
			}
			sound.play();
			delay = 5;
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
