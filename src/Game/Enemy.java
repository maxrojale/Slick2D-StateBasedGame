package Game;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import WeaponTypes.SingleShot;
import WeaponTypes.WeaponType;


public class Enemy {
	private int posx, posy, sizex, sizey, vectorx, vectory, bulletdelay, bulletvector, ybulletsize,xbulletsize, framecounter;
	private boolean toBeDeleted, wasHit;
	Shape enemyshape;
	Image enemyImage;
	WeaponType mainWeapon;

	public Enemy (int posx, int posy,int sizex,int sizey, int vectorx, int vectory, Image enemyImage) {
		this.posx=posx;
		this.posy=posy;
		this.sizex=sizex;
		this.sizey=sizey;
		this.vectorx=vectorx;
		this.vectory=vectory;
		xbulletsize=15;
		ybulletsize=4;
		bulletdelay=0;
		framecounter=0;
		bulletvector=-20;
		toBeDeleted=false;
		mainWeapon = new SingleShot();
		enemyshape=new Rectangle(posx,posy,sizex,sizey);
		this.enemyImage = enemyImage;
		
	}
	
	public boolean isToBeDeleted() {
		return toBeDeleted;
	}

	public void setToBeDeleted(boolean toBeDeleted) {
		this.toBeDeleted = toBeDeleted;
	}
	
	public void update() {
		move();
		shoot();
	}

	public void move() {
		enemyshape.setCenterX(enemyshape.getCenterX()+vectorx);
		framecounter++;
		if(framecounter >=15) {
			framecounter=0;
			vectory = -1* vectory;
		}
		enemyshape.setCenterY(enemyshape.getCenterY()+vectory);
		if (enemyshape.getCenterX()<=-100) {
			toBeDeleted=true;
		}
	}
	
	public void shoot() {
		if (bulletdelay<=0 && enemyshape.getCenterX() > 0 && enemyshape.getCenterX() < Setup.WIDTH) {
			Bullet bullet = new Bullet(new Rectangle(enemyshape.getCenterX()-10,enemyshape.getCenterY(),xbulletsize,ybulletsize),bulletvector,0,100,25,true);
			GameData.enemybullets.add(bullet);
			GameData.enemy1Laser.play();
			bulletdelay=30;
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
	public boolean WasHit() {
		return wasHit;
		
	}

	public void setWasHit(boolean wasHit) {
		this.wasHit = wasHit;
		//toBeDeleted=true;
	}

}