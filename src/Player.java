import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Player  {
	

		boolean collided;
		Shape playershape;
		Image playerImage;
		float[] position;
		int hp,shield;
		
		public Player (Image playerImage, int hp, int shield) {
			this.playerImage = playerImage;
			this.hp = hp;
			this.shield = shield;
			setStartingPosition();
		}
				
	

		public void shoot() {
			
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
			playershape = new Polygon(position);
		}
		
		public boolean isCollided() {
			return collided;
		}

		public void setCollided(boolean collided) {
			this.collided = collided;
		}

		public Shape getPlayershape() {
			return playershape;
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
	