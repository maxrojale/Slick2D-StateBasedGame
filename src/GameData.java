import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;

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
	public static Animation explosionAnimation;
	public static SpriteSheet explosionSheet;
	
	public static void loadGameFiles() throws SlickException {
		playerImage = new Image("res/player.png");
		enemyImage = new Image("res/Enemy1.png");
		enemyBullet = new Image("res/enemybullet.png");
		playerLaser = new Sound("res/laser.wav");
		enemy1Laser = new Sound ("res/enemy1laser.wav");
		explosion = new Sound("res/explosion.wav");
		music = new Music("res/boss.ogg");
		explosionSheet=new SpriteSheet("res/explosion_spritesheet.png",64,64);
		explosionAnimation = new Animation(explosionSheet,60);
		playerImage.rotate(90);
		enemies.add(enemy1);

		enemyImage.rotate(270);
		player = new Player(playerImage);
	}
	
	public static void initializeGameData() {
		enemies.clear();
		bullets.clear();
		player.setStartingPosition();
		player.setCollided(false);
		score=0;
		scoreString= "Score: " + score;

	}
}
