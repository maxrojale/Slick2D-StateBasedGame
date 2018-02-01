import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class GameData {
	public static Player player;
	public static ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	public static ArrayList<Bullet> enemybullets = new ArrayList<Bullet>();
	public static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	public static boolean GameOver = false;
	public static Image playerImage;
	public static Image enemyImage;
	public static Sound playerLaser;
	public static Sound enemy1Laser;
	public static Sound explosion;
	public static Enemy enemy1;
	public static Enemy enemy2;
	public static Enemy enemy3;
	public static Enemy enemy4;
	public static Enemy enemy5;
	
	public static void loadGameFiles() throws SlickException {
		playerImage = new Image("res/player.png");
		enemyImage = new Image("res/enemy1.png");
		playerLaser = new Sound("res/laser.wav");
		enemy1Laser = new Sound ("res/enemy1laser.wav");
		explosion = new Sound("res/explosion.wav");
		playerImage.rotate(90);
		enemyImage.rotate(270);
		player = new Player(playerImage);
	}
	
	public static void initializeGameData() {
		enemies.clear();
		bullets.clear();
		player.setStartingPosition();
		player.setCollided(false);
		enemy1 = new Enemy(800,20,48,24,-5,0,enemyImage);
		enemy2 = new Enemy(800,120,48,24,-5,0,enemyImage);
		enemy3 = new Enemy(800,220,48,24,-5,0,enemyImage);
		enemy4 = new Enemy(800,320,48,24,-5,0,enemyImage);
		enemy5 = new Enemy(800,420,48,24,-5,0,enemyImage);
		enemies.add(enemy1);
		enemies.add(enemy2);
		enemies.add(enemy3);
		enemies.add(enemy4);
		enemies.add(enemy5);
	}
}