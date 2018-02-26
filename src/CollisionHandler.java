import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;

public class CollisionHandler {
	
	public boolean CheckPlayerEnemyCollision(Enemy enemy, Player player) {
		
		Point collisionPoint;

		Image playerImage = player.getPlayerImage();
		Image enemyImage = enemy.getEnemyImage();
		
		int xstart_player=(int)player.getPlayershape().getMinX();
		int xend_player=(int)player.getPlayershape().getMaxX();
		int ystart_player=(int)player.getPlayershape().getMinY();
		int yend_player=(int)player.getPlayershape().getMaxY();
		
		int xstart_enemy=(int)enemy.getEnemyshape().getMinX();
		int xend_enemy=(int)enemy.getEnemyshape().getMaxX();
		int ystart_enemy=(int)enemy.getEnemyshape().getMinY();
		int yend_enemy=(int)enemy.getEnemyshape().getMaxY();
		
		System.out.println(xstart_enemy);
		System.out.println(ystart_enemy);
		
		for (int i = ystart_player; i <= yend_player;i++) {
			for (int j=xstart_player;j<=xend_player;j++) {
				collisionPoint = new Point(j,i);
				System.out.println("j :" +(j-xstart_enemy));
				System.out.println("i :" +(i-ystart_enemy));
				
				if (enemy.getEnemyshape().contains(collisionPoint)) {
					if(enemyImage.getColor(j-xstart_enemy, i-xstart_enemy).getAlpha()==255) {
						return true;
					}
				}
			
			}
		}
		return false;
	}
}
