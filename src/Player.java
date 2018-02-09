import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
<<<<<<< HEAD
=======
import org.newdawn.slick.geom.Polygon;
>>>>>>> 484544b97d6d00c38d64d7df09c77cf15da971e3
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Player  {
	

		boolean collided;
		Shape playershape;
		Image playerImage;
		
		public Player (int posx, int posy, Image playerImage) {
			this.playerImage = playerImage;
			setStartingPosition();
		}
		
		
		public Player (Image playerImage) {
			setStartingPosition();	
			collided=false;
			this.playerImage = playerImage;
		}
		
	
		public void init() throws SlickException {
			playerImage = new Image("res/player.png");
		}
		
		public void setStartingPosition() {
<<<<<<< HEAD
			playershape=new Rectangle(50,50,64,64);
=======
		position = new float[] {50,50,50,98,98,75};
		playershape = new Polygon(position);
>>>>>>> 484544b97d6d00c38d64d7df09c77cf15da971e3
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

}
	