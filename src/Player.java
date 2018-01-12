import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Player {

		private int posx;
		private int posy;
		boolean collided;
		Shape playershape;
		
		public Player (int posx, int posy) {
			this.posx = posx;
			this.posy = posy;
			collided=false;
			init();
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

		public void init() {
			playershape = new Rectangle(posx,posy,30,30);
		}
		
		public int getPosy() {
			return posy;
		}
		public void setPosy(int posy) {
			this.posy = posy;
		}
		public int getPosx() {
			return posx;
		}
		public void setPosx(int posx) {
			this.posx = posx;
		}		
}