import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;



public class GameState extends BasicGameState{

	public static Input in;
	private static Image background;
	private static int background_pos=0;
	private static int background2_pos=Setup.WIDTH;
	private static int background_scroll_speed=5;
	private int bulletdelay=0;
	ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	ArrayList<Bullet> ebullets = new ArrayList<Bullet>();
	ArrayList<Explosion> explosions = new ArrayList<Explosion>();
	public static int counter = 0;
	private Player player;
	CollisionHandler collisionHandler = new CollisionHandler();
	boolean pixelperfectcollision;
	private Enemy enemy;
	private Explosion explosion;
	private Random rnd;
	Music music;
	//private AnimationHandler animationHandler;

	
	@Override
	public void init(GameContainer gc, StateBasedGame gsm) throws SlickException {
		in = gc.getInput();
		//animationHandler = new AnimationHandler();
		background=new Image("res/starfield2.png");
		player = GameData.player;
		GameData.loadGameFiles();
		GameData.initializeGameData();
		player = GameData.player;
		bullets = GameData.bullets;
		enemies = GameData.enemies;
		rnd = new Random();
		music = GameData.music;
	}
	
	
	@Override
	public void update(GameContainer gc, StateBasedGame gsm, int delta) throws SlickException {
		if (GameData.restart) {
			GameData.score=0;
			GameData.restart=false;
		}
				
		if(!music.playing()) {
			music.loop();
		}
		
		boolean spawnenemy=true;
		
		//Spawn Enemies
		if (enemies.size() < 7) {
			enemy = new Enemy(rnd.nextInt(600)+Setup.WIDTH,rnd.nextInt(Setup.HEIGHT),48,24,-5,0,GameData.enemyImage);
			
			for(int i=0; i < enemies.size();i++) {
				if (enemies.get(i).getEnemyshape().intersects(enemy.getEnemyshape())) {
					spawnenemy=false;
				}
			}
			if (spawnenemy) {
				enemies.add(enemy);
				GameData.enemies = enemies;
			}		
		}
			

		
		//Background Movement
		if (background_pos >= 0-Setup.WIDTH) {
			background_pos-=background_scroll_speed;	
		}
		else {
			background_pos=0;
		}
		
		if (background2_pos >= 0) {
			background2_pos-=background_scroll_speed;
		}
		else {
			background2_pos=Setup.WIDTH;
		}
			
		// Player Controls
		if (in.isKeyDown(Input.KEY_LEFT)) {
			player.getPlayershape().setCenterX(player.getPlayershape().getCenterX()-10);
		}
		if (in.isKeyDown(Input.KEY_RIGHT)) {
			player.getPlayershape().setCenterX(player.getPlayershape().getCenterX()+10);
		}
		if (in.isKeyDown(Input.KEY_UP)) {
			player.getPlayershape().setCenterY(player.getPlayershape().getCenterY()-10);
		}
		if (in.isKeyDown(Input.KEY_DOWN)) {
			player.getPlayershape().setCenterY(player.getPlayershape().getCenterY()+10);
		}
	
		if (in.isKeyDown(Input.KEY_SPACE) && bulletdelay==0) {
			player.shoot();
			Bullet bullet = new Bullet(player.getPlayershape().getCenterX()+10,player.getPlayershape().getCenterY(),4,20);
			bullets.add(bullet);
			GameData.playerLaser.play();
			bulletdelay=5;
		}

		//Enemy Movement
		for (int i=0; i < enemies.size();i++) {
			enemies.get(i).getEnemyshape().setCenterX(enemies.get(i).getEnemyshape().getCenterX()+enemies.get(i).getVectorx());
			if (enemies.get(i).getEnemyshape().getCenterX()<=-100) {
				enemies.remove(i);
				GameData.enemies = enemies;
			}
		}
		
		//Collision Detection Player vs Enemies
		for (int i=0; i < enemies.size();i++) {
			if (enemies.get(i).getEnemyshape().intersects(player.getPlayershape())) {
					player.setCollided(true);
				
			}
		}
		
		//Player Bullet Movement
		for (int i=0; i < bullets.size(); i++) {
			bullets.get(i).getShape().setCenterX(bullets.get(i).getShape().getCenterX()+20);		
		}
		if (bulletdelay > 0) {
			bulletdelay--;
		}
		
		//Collision Detection Player Bullets vs Enemies
		for (int i=0; i < bullets.size(); i++) {
			for (int j=0; j < enemies.size(); j++) {
				if(bullets.get(i).getShape().intersects(enemies.get(j).getEnemyshape())) {
					bullets.get(i).setDelete(true);
					GameData.explosion.play();
					GameData.score++;
					explosion = new Explosion(enemies.get(j).getEnemyshape().getMinX()-16, enemies.get(j).getEnemyshape().getMinY()-16,background_scroll_speed);
					explosions.add(explosion);
					enemies.remove(j);
				}
			}
		}
			
	
		//Enemies Shoot
		for (int i=0; i < enemies.size(); i++) {
			if(enemies.get(i).getEnemyshape().getCenterX()<Setup.WIDTH && enemies.get(i).getEnemyshape().getCenterX() > 0 && !enemies.get(i).WasHit())
			enemies.get(i).shoot();
			ebullets = GameData.enemybullets;
		}
		
		//EnemyBulletsMovement & Collision vs Player
		for (int i=0; i < ebullets.size(); i++) {
			ebullets.get(i).getShape().setCenterX(ebullets.get(i).getShape().getCenterX()+ebullets.get(i).getVector_x());
			if (ebullets.get(i).getShape().intersects(player.getPlayershape())) {
				player.setCollided(true);
			}
		}
		
		//BulletCleanUp Check, setDeleter
		for (int i=0; i < bullets.size(); i++) {
			if (bullets.get(i).getShape().getCenterX() > Setup.WIDTH) {
				bullets.get(i).setDelete(true);
			}
		}
		
		//EnemyBulletCleanUp Check, setDeleter
		for (int i=0; i < ebullets.size(); i++) {
			if (ebullets.get(i).getShape().getCenterX() > Setup.WIDTH) {
				ebullets.get(i).setDelete(true);
			}
		}
		
		//BulletDeleter
		for (int i=0; i < bullets.size(); i++) {
			if (bullets.get(i).getDelete()==true) {
				bullets.remove(i);
			}
		}
		
		for (int i=0; i < ebullets.size(); i++) {
			if (ebullets.get(i).getDelete()==true) {
				ebullets.remove(i);
			}
		}
	
		for(int i=0; i < explosions.size(); i++) {
			explosions.get(i).update();
			if (explosions.get(i).isAnimationPlayed()) {
				explosions.remove(i);
			}
		}
		
		//GameData Updater
		GameData.player = player;
		GameData.enemies = enemies;
		GameData.bullets = bullets;
		
		if (player.isCollided()) {
			GameData.GameOver=true;
			GameData.explosion.play();
			enemies.clear();
			ebullets.clear();
			bullets.clear();

			explosions.clear();
			GameData.initializeGameData();

			in.clearKeyPressedRecord();
			music.stop();
			gsm.enterState(9,new FadeOutTransition(), new FadeInTransition());
		}
		
	
	}

	@Override
	public void render(GameContainer gc, StateBasedGame gsm, Graphics g) throws SlickException {
		if (!GameData.GameOver) {
			g.drawImage(background, background_pos, 0);
			g.drawImage(background, background2_pos, 0);			
			//g.setColor(Color.magenta);
			//g.fill(player.getPlayershape());
			g.drawImage(player.getPlayerImage(),player.getPlayershape().getMinX()-8,player.getPlayershape().getMinY()-7);		
			g.setColor(Color.cyan);
		
			for (int i=0; i < bullets.size(); i++) {
				g.fill(bullets.get(i).getShape());
			}
			
			for (int i=0; i < ebullets.size(); i++) {
				//g.setColor(Color.orange);
				//g.fill(ebullets.get(i).getShape());
				g.drawImage(GameData.enemyBullet, ebullets.get(i).getShape().getMinX()-8, ebullets.get(i).getShape().getMinY()-10);
			}
			
			for (int i=0; i < enemies.size(); i++) {
				if (!enemies.get(i).isToBeDeleted() && !enemies.get(i).WasHit()) {
					//g.setColor(Color.green);
					//g.fill(enemies.get(i).getEnemyshape());
					g.drawImage(enemies.get(i).getEnemyImage(),enemies.get(i).getEnemyshape().getMinX()-5,enemies.get(i).getEnemyshape().getMinY()-20);
				}
			}
			for (int i=0; i < explosions.size(); i++) {
				explosions.get(i).getAnimation().draw(explosions.get(i).getX(),explosions.get(i).getY());
			}
			g.setColor(Color.orange);
			g.drawString("Score: " + GameData.score, 10, 10);
		}		
	}
		
	
	@Override
	public int getID() {
		return 1;
	}
}