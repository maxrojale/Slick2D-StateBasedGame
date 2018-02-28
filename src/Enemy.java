import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;


public class Enemy {
	private int posx, posy, sizex, sizey, vectorx, vectory, bulletdelay, bulletvector, ybulletsize,xbulletsize;
	private boolean toBeDeleted, wasHit;
	Shape enemyshape;
	Image enemyImage;

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