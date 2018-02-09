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

}
	