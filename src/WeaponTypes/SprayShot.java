package WeaponTypes;

import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Circle;

import Game.Bullet;
import Game.GameData;

public class SprayShot implements WeaponType {
	int power = 1;
	int ttl = 25;
	int dmg = 50;
	int delay = 0;
	Bullet[] bullet;
	@Override

	public String getName() {
		return "Spray Shot";
	}

	@Override
	public int getPower() {
		return 0;
	}



	@Override
	public void fire(Sound sound, int bulletDirection) {
		float x = GameData.player.getShape().getCenterX()+15;
		float y = GameData.player.getShape().getCenterY();
		int size = 4;
		if(delay==0) {
			switch(power) {
			case 1: 		
				bullet = new Bullet[2];
				bullet[0] = new Bullet(new Circle(x,y+7,size),20*bulletDirection,0,ttl,dmg,true);
				bullet[1] = new Bullet(new Circle(x,y-7,size),20*bulletDirection,0,ttl,dmg,true);
				break;
			case 2:
				bullet = new Bullet[3];
				bullet[0] = new Bullet(new Circle(x,y,size),20*bulletDirection,0,ttl,dmg,true);
				bullet[1] = new Bullet(new Circle(x,y+7,size),20*bulletDirection,-10,ttl,dmg,true);
				bullet[2] = new Bullet(new Circle(x,y-7,size),20*bulletDirection,10,ttl,dmg,true);
				break;
			case 3:
				bullet = new Bullet[5];
				bullet[0] = new Bullet(new Circle(x,y,size),20*bulletDirection,0,ttl,dmg,true);
				bullet[1] = new Bullet(new Circle(x,y+7,size),20*bulletDirection,-10,ttl,dmg,true);
				bullet[2] = new Bullet(new Circle(x,y-7,size),20*bulletDirection,10,ttl,dmg,true);
				bullet[3] = new Bullet(new Circle(x,y+3,size),20*bulletDirection,5,ttl,dmg,true);
				bullet[4] = new Bullet(new Circle(x,y-3,size),20*bulletDirection,-5,ttl,dmg,true);
				break;
			}
			for(int i =0; i < bullet.length;i++) {
				GameData.bullets.add(bullet[i]);	
			}
			sound.play();
			delay=5;
		}
		else {
			delay--;
		}
	}
	

	
	public void decreaseDelay() {
		delay--;
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
