import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;


public class Enemy {
	private int posx, posy, sizex, sizey, vectorx, vectory, bulletdelay, bulletvector, bulletradius;
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
			GameData.enemybullets.add(bullet);
			GameData.enemy1Laser.play();
			bulletdelay=20;
		}
		else {
			bulletdelay--;
		}
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