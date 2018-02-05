import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
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
	public static int counter = 0;
	private Player player;
	

	@Override
	public void init(GameContainer gc, StateBasedGame gsm) throws SlickException {
		in = gc.getInput();
		background=new Image("res/starfield2.png");
		player = GameData.player;
		GameData.loadGameFiles();
		GameData.initializeGameData();
		player = GameData.player;
		bullets = GameData.bullets;
		enemies = GameData.enemies;
		//GameData.music.loop();
	}
	
	
	@Override
	public void update(GameContainer gc, StateBasedGame gsm, int delta) throws SlickException {
		
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
			Bullet bullet = new Bullet(player.getPlayershape().getCenterX()+10,player.getPlayershape().getCenterY(),4,20);
			bullets.add(bullet);
			GameData.playerLaser.play();
			bulletdelay=5;
		}

		//Enemy Movement
		for (int i=0; i < enemies.size();i++) {
			enemies.get(i).getEnemyshape().setCenterX(enemies.get(i).getEnemyshape().getCenterX()+enemies.get(i).getVectorx());
			if (enemies.get(i).getEnemyshape().getCenterX()<=50) {
				enemies.get(i).setVectorx(5);
				enemies.get(i).getEnemyshape().setCenterY(enemies.get(i).getEnemyshape().getCenterY()+10);
			}
			if (enemies.get(i).getEnemyshape().getCenterX() >= Setup.WIDTH-50) {
				enemies.get(i).setVectorx(-5);
				enemies.get(i).getEnemyshape().setCenterY(enemies.get(i).getEnemyshape().getCenterY()-10);
			}
		}
		
		//Collision Detection Player vs Enemies
		for (int i=0; i < enemies.size();i++) {
			if (enemies.get(i).getEnemyshape().intersects(player.getPlayershape())) {
				player.setCollided(true);
			}
		}
		
		//Collision Detection Player Bullets vs Enemies
		for (int i=0; i < bullets.size(); i++) {
			for (int j=0; j < enemies.size(); j++) {
				if(bullets.get(i).getShape().intersects(enemies.get(j).getEnemyshape())) {
					bullets.get(i).setDelete(true);
					GameData.explosion.play();
					GameData.score++;
					GameData.scoreString = "Score: " + GameData.score;
					enemies.remove(j);				}
			}
		}
		
		//Player Bullet Movement
		for (int i=0; i < bullets.size(); i++) {
			bullets.get(i).getShape().setCenterX(bullets.get(i).getShape().getCenterX()+20);		
		}
		if (bulletdelay > 0) {
			bulletdelay--;
		}
		
		//Enemies Shoot
		for (int i=0; i < enemies.size(); i++) {
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
			GameData.initializeGameData();
			gsm.enterState(9,new FadeOutTransition(), new FadeInTransition());
			in.clearKeyPressedRecord();
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame gsm, Graphics g) throws SlickException {
		if (!GameData.GameOver) {
			g.drawImage(background, background_pos, 0);
			g.drawImage(background, background2_pos, 0);		
	
			g.drawImage(player.getPlayerImage(),player.getPlayershape().getX()-10,player.getPlayershape().getY()-6);		
			g.setColor(Color.cyan);
			for (int i=0; i < bullets.size(); i++) {
				g.fill(bullets.get(i).getShape());
			}
			
			for (int i=0; i < ebullets.size(); i++) {
				//g.setColor(Color.orange);
				//g.fill(ebullets.get(i).getShape());
				g.drawImage(GameData.enemyBullet, ebullets.get(i).getShape().getMinX()-5, ebullets.get(i).getShape().getMinY()-10);
			}
			
			for (int i=0; i < enemies.size(); i++) {
				if (!enemies.get(i).isToBeDeleted()) {
					//g.setColor(Color.red);
					//g.fill(enemies.get(i).getEnemyshape());
					g.drawImage(enemies.get(i).getEnemyImage(),enemies.get(i).getEnemyshape().getX()-5,enemies.get(i).getEnemyshape().getY()-19);
				}
			}
			g.setColor(Color.orange);
			g.drawString(GameData.scoreString, 10, 10);
			
			
			//System.out.println(GameData.enemyImage.get
		}		
	}
		
	
	
	@Override
	public int getID() {
		return 1;
	}
}