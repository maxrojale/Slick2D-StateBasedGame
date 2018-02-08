import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Shape;

public class CollisionHandler {
	
	public boolean collisionPlayerEnemy(Player player, Enemy enemy) {
		
		Point point;
		Shape rect1=player.getPlayershape();
		Shape rect2=enemy.getEnemyshape();
		Image playerImage=player.getPlayerImage();
		Image enemyImage=enemy.getEnemyImage();
		
		
		int xstart = (int)rect1.getMinX();
		int xend = (int)rect1.getMaxX();
		int ystart = (int)rect1.getMinY();
		int yend = (int)rect1.getMaxY();
		int xstart2 = (int)rect2.getMinX();
		int ystart2 = (int)rect2.getMinY();
		
		for (int i = ystart; i < yend; i++) {
			for (int j = xstart; j < xend; j++) {
				point=new Point(j,i);
				if(rect2.contains(point)) {
					if (playerImage.getColor(j-xstart,i-ystart).getAlpha()>128 && enemyImage.getColor(j-xstart2, i-ystart2).getAlpha()>128) {
						return true;
					}
				}
			}
		}
	
		return false;
	}
	
}
