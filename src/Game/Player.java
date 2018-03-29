package Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import WeaponTypes.*;

public class Player  {
	
		private boolean destroyed;
		private Shape shape;
		private Image playerImage;
		private float[] position;
		int hp, shield, shieldregen, shieldregendelay, speed, maxshield, maxhp, weapontype, bulletdelay, bulletdelay2;
		Bullet bullet;
		private String weaponname;
		private WeaponType mainweapon;
		private WeaponType auxweapon;
		
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
			mainweapon = new SingleShot();
			auxweapon = new SideShot();
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
		
		public String getWeaponname() {
			return weaponname;
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
			mainweapon.fire(GameData.playerLaser,1);
			auxweapon.fire(GameData.playerLaser,1);
		}
		
		public WeaponType getMainWeapon() {
			return mainweapon;
		}

		public void setMainWeapon(WeaponType mainweapon) {
			this.mainweapon = mainweapon;
		}
		public WeaponType getAuxWeapon() {
			return auxweapon;
		}

		public void setAuxWeapon(WeaponType auxweapon) {
			this.auxweapon = auxweapon;
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
	