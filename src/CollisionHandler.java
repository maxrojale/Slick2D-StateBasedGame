import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;
<<<<<<< HEAD
<<<<<<< HEAD


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
				//	if(enemyImage.getColor(j-xstart_enemy, i-xstart_enemy).getAlpha()==255) {
						return false;
					}
				}
			
			}
		//}
		return false;
	}
}
=======
=======
>>>>>>> 484544b97d6d00c38d64d7df09c77cf15da971e3
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
<<<<<<< HEAD
>>>>>>> 484544b97d6d00c38d64d7df09c77cf15da971e3
=======
>>>>>>> 484544b97d6d00c38d64d7df09c77cf15da971e3
