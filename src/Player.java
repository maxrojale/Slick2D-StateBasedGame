import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;

public class Player {

		boolean collided;
		Shape playershape;
<<<<<<< HEAD
		public Player (int posx, int posy) {
			this.posx = posx;
			this.posy = posy;
=======
		float[] position;
		Image playerImage;
		
		public Player (Image playerImage) {
			setStartingPosition();	
>>>>>>> b707260b4b4698ee0e752cd20c457dea8379d89c
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