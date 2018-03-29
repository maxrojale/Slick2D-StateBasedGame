package WeaponTypes;

import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Circle;

import Game.Bullet;
import Game.GameData;

public class SideShot implements WeaponType {

	int power=1;
	int ttl = 25;
	int dmg = 50;
	int delay = 0;
	public String getName() {
		return "Side Shot";
	}

	@Override
	public int getPower() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void fire(Sound sound, int bulletDirection) {
		if(delay==0) {
			Bullet[] bullet = new Bullet[2];
			float x = GameData.player.getShape().getCenterX();
			float y = GameData.player.getShape().getCenterY();
			int size = 4 + power;
			bullet[0] = new Bullet(new Circle(x,y+10,size),0,20,ttl,dmg,true);
			bullet[1] = new Bullet(new Circle(x,y-10,size),0,-20,ttl,dmg,true);
			for (int i=0; i < bullet.length;i++) {
				GameData.bullets.add(bullet[i]);
			}
			delay=10;
			sound.play();
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
