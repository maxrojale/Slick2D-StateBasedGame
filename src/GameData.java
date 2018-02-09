import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class GameData {
	public static Player player;
	public static ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	public static ArrayList<Bullet> enemybullets = new ArrayList<Bullet>();
	public static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	public static boolean GameOver = false;
	public static Music music;
	public static Image playerImage;
	public static Image enemyImage;
	public static Image enemyBullet;
	public static Sound playerLaser;
	public static Sound enemy1Laser;
	public static Sound explosion;
	public static Enemy enemy1;
	public static Enemy enemy2;
	public static Enemy enemy3;
	public static Enemy enemy4;
	public static Enemy enemy5;
	public static int score;
	public static String scoreString;
	
	public static void loadGameFiles() throws SlickException {
		playerImage = new Image("res/player.png");
		enemyImage = new Image("res/enemy1.png");
		enemyBullet = new Image("res/enemybullet.png");
		playerLaser = new Sound("res/laser.wav");
		enemy1Laser = new Sound ("res/enemy1laser.wav");
		explosion = new Sound("res/explosion.wav");
<<<<<<< HEAD
<<<<<<< HEAD
		//playerImage.rotate(90);
		//enemyImage.rotate(270);
=======
=======
>>>>>>> 484544b97d6d00c38d64d7df09c77cf15da971e3
		music = new Music("res/boss.ogg");
		playerImage.rotate(90);
		enemyImage.rotate(270);
		enemies.add(enemy1);
<<<<<<< HEAD
>>>>>>> 484544b97d6d00c38d64d7df09c77cf15da971e3
=======
>>>>>>> 484544b97d6d00c38d64d7df09c77cf15da971e3
		player = new Player(playerImage);
	}
	
	public static void initializeGameData() {
		enemies.clear();
		bullets.clear();
		player.setStartingPosition();
		player.setCollided(false);
		score=0;
		scoreString= "Score: " + score;
<<<<<<< HEAD
<<<<<<< HEAD
		enemy1 = new Enemy(800,20,64,64,0,0,enemyImage);
		enemy2 = new Enemy(800,120,64,64,-5,0,enemyImage);
		enemy3 = new Enemy(800,220,64,64,-5,0,enemyImage);
		enemy4 = new Enemy(800,320,64,64,-5,0,enemyImage);
		enemy5 = new Enemy(800,420,64,64,-5,0,enemyImage);
		enemies.add(enemy1);
		enemies.add(enemy2);
		enemies.add(enemy3);
		enemies.add(enemy4);
		enemies.add(enemy5);
=======
=======
>>>>>>> 484544b97d6d00c38d64d7df09c77cf15da971e3
		enemy1 = new Enemy(800,20,48,24,-5,0,enemyImage);
	//	enemy2 = new Enemy(800,120,48,24,-5,0,enemyImage);
	//	enemy3 = new Enemy(800,220,48,24,-5,0,enemyImage);
	//	enemy4 = new Enemy(800,320,48,24,-5,0,enemyImage);
	//	enemy5 = new Enemy(800,420,48,24,-5,0,enemyImage);
		//enemies.add(enemy1);
	//	enemies.add(enemy2);
	//	enemies.add(enemy3);
	//	enemies.add(enemy4);
	//	enemies.add(enemy5);
<<<<<<< HEAD
>>>>>>> 484544b97d6d00c38d64d7df09c77cf15da971e3
=======
>>>>>>> 484544b97d6d00c38d64d7df09c77cf15da971e3
	}
}
