import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;


public class Enemy {
	private int posx, posy, sizex, sizey, vectorx, vectory, bulletdelay, bulletvector, bulletradius;
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	private boolean toBeDeleted;
	Shape enemyshape;
	Image enemyImage;

	public Enemy (int posx, int posy,int sizex,int sizey, int vectorx, int vectory, Image enemyImage) {
		this.posx=posx;
		this.posy=posy;
		this.sizex=sizex;
		this.sizey=sizey;
		this.vectorx=vectorx;
		this.vectory=vectory;
		bulletradius=4;
		bulletdelay=0;
		bulletvector=-20;
		toBeDeleted=false;
		enemyshape=new Rectangle(posx,posy,sizex,sizey);
		this.enemyImage = enemyImage;
		
	}
	
	public boolean isToBeDeleted() {
		return toBeDeleted;
	}

	public void setToBeDeleted(boolean toBeDeleted) {
		this.toBeDeleted = toBeDeleted;
	}

	public void shoot() {
		if (bulletdelay<=0) {
			Bullet bullet = new Bullet(enemyshape.getCenterX()-10,enemyshape.getCenterY(),bulletradius,bulletvector);
			bullets.add(bullet);
			bulletdelay=10;
		}
		else {
			bulletdelay--;
		}
		collisioncheck();
	}
	
	private void collisioncheck() {
		for (int i=0; i < bullets.size();i++) {
			if(bullets.get(i).getShape().intersects(GameData.player.getPlayershape())) {
				bullets.get(i).getShape().setCenterX(GameData.player.getPlayershape().getCenterX()-100);
				bullets.get(i).setDelete(true);
			}
		}
	}
	
	public void updateBullets() {
		
		//Bullet Movement and Clean Up Checker
		for (int i=0; i < bullets.size(); i++) {
			bullets.get(i).getShape().setCenterX(bullets.get(i).getShape().getCenterX()+bulletvector);
			if (bullets.get(i).getShape().getCenterX() <= 1 || bullets.get(i).getShape().getCenterX() >= 950) {
				bullets.get(i).setDelete(true);
			}
		}
		
		//Bullet Cleanup
		for (int i=0; i < bullets.size(); i++) {
			if (bullets.get(i).getDelete()==true) {
				bullets.remove(i);
			}
		}
	}
	
	public ArrayList<Bullet> getBullets() {
		return bullets;
	}

	public int getSizex() {
		return sizex;
	}
	
	public int getVectorx() {
		return vectorx;
	}

	public void setVectorx(int vectorx) {
		this.vectorx = vectorx;
	}

	public int getBulletdelay() {
		return bulletdelay;
	}

	public void setBulletdelay(int bulletdelay) {
		this.bulletdelay = bulletdelay;
	}

	public int getVectory() {
		return vectory;
	}

	public void setVectory(int vectory) {
		this.vectory = vectory;
	}

	public void setSizex(int sizex) {
		this.sizex = sizex;
	}

	public int getSizey() {
		return sizey;
	}

	public void setSizey(int sizey) {
		this.sizey = sizey;
	}

		
	public Shape getEnemyshape() {
		return enemyshape;
	}

	public int getPosx() {
		return posx;
	}

	public void setPosx(int posx) {
		this.posx = posx;
	}

	public int getPosy() {
		return posy;
	}

	public void setPosy(int posy) {
		this.posy = posy;
	}
	public Image getEnemyImage() {
		return enemyImage;
	}

}