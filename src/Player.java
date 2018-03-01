import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Player  {
	
		boolean destroyed;
		Shape shape;
		Image playerImage;
		float[] position;
		int hp, shield, shieldregen, shieldregendelay, speed, maxshield, maxhp, weapontype, bulletdelay;
		Bullet bullet;
		
		public Player (Image playerImage, int hp, int shield, int shieldregen) {
			this.playerImage = playerImage;
			this.hp = hp;
			this.shield = shield;
			this.shieldregen = shieldregen;
			this.maxhp = hp;
			this.maxshield = shield;
			speed = 10;
			shieldregendelay=0;
			weapontype=2;
			setStartingPosition();
		}
				
		public void update() {
			if(shieldregendelay > 0) {
				shieldregendelay--;
			}
			if(shield < maxshield && shieldregendelay==0 ) {
				shield+=shieldregen;
				shieldregendelay=5;
			}
		}
		
		public void moveleft() {
			if(shape.getCenterX() - speed > 0) {
			shape.setCenterX(shape.getCenterX()-speed);
			}
		}
		
		public void moveright() {
			if(shape.getCenterX() + speed < Setup.WIDTH) {
				shape.setCenterX(shape.getCenterX()+speed);
			}
			
		}
		
		public void moveup() {
			if(shape.getCenterY() - speed > 0)
			shape.setCenterY(shape.getCenterY()-speed);
		}
		
		public void movedown() {
			if(shape.getCenterY() + speed < Setup.HEIGHT)
			shape.setCenterY(shape.getCenterY()+speed);
		}

		public void shoot() {
			if (bulletdelay==0) {
				switch(weapontype) {
				case 0:
					Bullet bullet = new Bullet(new Circle(shape.getCenterX()+10,shape.getCenterY(),4),20,0,100,100,true);
					GameData.bullets.add(bullet);
					GameData.playerLaser.play();
					bulletdelay=5;
					break;
				//spray shot
				case 1:
					Bullet spraybullet1 = new Bullet(new Rectangle(shape.getCenterX()+10,shape.getCenterY()+3,50,2),20,0,50,100,false);;
					Bullet spraybullet2 = new Bullet(new Circle(shape.getCenterX()+10,shape.getCenterY(),4),20,3,25,100,true);
					Bullet spraybullet3 = new Bullet(new Circle(shape.getCenterX()+10,shape.getCenterY(),4),20,-3,25,100,true);
					Bullet spraybullet4 = new Bullet(new Circle(shape.getCenterX()+10,shape.getCenterY(),4),20,5,25,100,true);
					Bullet spraybullet5 = new Bullet(new Circle(shape.getCenterX()+10,shape.getCenterY(),4),20,-5,25,100,true);
					Bullet spraybullet6 = new Bullet(new Circle(shape.getCenterX()+10,shape.getCenterY(),4),20,7,25,100,true);
					Bullet spraybullet7 = new Bullet(new Circle(shape.getCenterX()+10,shape.getCenterY(),4),20,-7,25,100,true);
					Bullet sideshot1 = new Bullet(new Circle(shape.getCenterX()+10,shape.getCenterY(),4),0,-20,50,100,true);
					Bullet sideshot2 = new Bullet(new Circle(shape.getCenterX()+10,shape.getCenterY(),4),0,20,50,100,true);
					GameData.bullets.add(spraybullet1);
					GameData.bullets.add(spraybullet2);
					GameData.bullets.add(spraybullet3);
					GameData.bullets.add(spraybullet4);
					GameData.bullets.add(spraybullet5);
					GameData.bullets.add(spraybullet6);
					GameData.bullets.add(spraybullet7);
					GameData.bullets.add(sideshot1);
					GameData.bullets.add(sideshot2);
					GameData.playerLaser.play();
					bulletdelay=5;
					break;
				//double lasers
				case 2:
					Bullet laser1 = new Bullet(new Rectangle(shape.getCenterX()+10,shape.getCenterY()+3,50,2),20,0,50,100,false);
					Bullet laser2 = new Bullet(new Rectangle(shape.getCenterX()+10,shape.getCenterY()-3,50,2),20,0,50,100,false);
					GameData.bullets.add(laser1);
					GameData.bullets.add(laser2);
					GameData.playerLaser.play();
					bulletdelay=5;
					break;
				default:
					break;
				}

			}
			else {
				bulletdelay--;
			}
		}
		
		public int getHp() {
			return hp;
		}

		public void setHp(int hp) {
			this.hp = hp;
		}

		public void init() throws SlickException {
			playerImage = new Image("res/player.png");
		}
		
		public void setStartingPosition() {
			position = new float[] {50,50,50,98,98,75};
			shape = new Polygon(position);
		}
		
		public boolean isDestroyed() {
			return destroyed;
		}

		public void setDestroyed(boolean destroyed) {
			this.destroyed = destroyed;
		}

		public Shape getShape() {
			return shape;
		}
		public int getWeapontype() {
			return weapontype;
		}

		public void setWeapontype(int weapontype) {
			this.weapontype = weapontype;
		}

		public Image getPlayerImage() {
			return playerImage;
		}
		public int getShield() {
			return shield;
		}

		public void setShield(int shield) {
			this.shield = shield;
		}

}
	