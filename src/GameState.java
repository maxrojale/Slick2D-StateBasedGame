import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;


public class GameState extends BasicGameState{

	public static Input in;
	private static Image background;
	private static Image playerImage;
	private static int background_pos=0;
	private static int background2_pos=Setup.WIDTH;
	private static int background_scroll_speed=5;
	private int bulletdelay=0;
	ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	public static int counter = 0;
	public static String score = "test";
	public Enemy enemy1;
	public Enemy enemy2;
	public Enemy enemy3;
	public Enemy enemy4;
	public Enemy enemy5;
	private Player player;

	@Override
	public void init(GameContainer gc, StateBasedGame gsm) throws SlickException {
		in = gc.getInput();
		background=new Image("res/starfield2.png");
		playerImage = new Image("res/player.png");
		playerImage.rotate(90);
		enemy1 = new Enemy(800,20,32,32,-5,0);
		enemy2 = new Enemy(800,120,32,32,-5,0);
		enemy3 = new Enemy(800,220,32,32,-5,0);
		enemy4 = new Enemy(800,320,32,32,-5,0);
		enemy5 = new Enemy(800,420,32,32,-5,0);
		player = GameData.player;
		enemies.add(enemy1);
		enemies.add(enemy2);
		enemies.add(enemy3);
		enemies.add(enemy4);
		enemies.add(enemy5);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame gsm, int delta) {
		
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
			bulletdelay=5;
		}
		if (in.isKeyPressed(Input.KEY_ESCAPE)) {
			gsm.enterState(9, new FadeOutTransition(), new FadeInTransition());
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
				gsm.enterState(9);
			}
		}
		//Collision Detection Player Bullets vs Enemies
		for (int i=0; i < bullets.size(); i++) {
			for (int j=0; j < enemies.size(); j++) {
				if(bullets.get(i).getShape().intersects(enemies.get(j).getEnemyshape())) {
					bullets.get(i).setDelete(true);
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
			enemies.get(i).updateBullets();
		}
		
		//BulletCleanUp Check, setDeleter
		for (int i=0; i < bullets.size(); i++) {
			if (bullets.get(i).getShape().getCenterX() > Setup.WIDTH) {
				bullets.get(i).setDelete(true);
			}
		}
		
		//BulletDeleter
		for (int i=0; i < bullets.size(); i++) {
			if (bullets.get(i).getDelete()==true) {
				bullets.remove(i);
			}
		}
		
		//Enemy Deleter
		
		//GameData Updater
		GameData.player = player;
		test
	}

	@Override
	public void render(GameContainer gc, StateBasedGame gsm, Graphics g) {
		g.drawImage(background, background_pos, 0);
		g.drawImage(background, background2_pos, 0);		
		//g.setColor(Color.magenta);
		//g.fill(player.getPlayershape());
		g.drawImage(playerImage,player.getPlayershape().getX()-10,player.getPlayershape().getY()-6);
		
		//playerImage.draw(player.getPlayershape().getMinX()-4,player.getPlayershape().getMinY()-6);
		
		g.setColor(Color.cyan);
		for (int i=0; i < bullets.size(); i++) {
			g.fill(bullets.get(i).getShape());
		}
		
		for (int i=0; i < enemies.size(); i++) {
			if (!enemies.get(i).isToBeDeleted()) {
				g.setColor(Color.red);
				g.fill(enemies.get(i).getEnemyshape());
			}
			for (int j=0; j < enemies.get(i).getBullets().size();j++) {
				g.setColor(Color.orange);
				g.fill(enemies.get(i).getBullets().get(j).getShape());
			}
		}
		g.setColor(Color.white);
		g.drawString(score, 10, 20);
	}
	
	@Override
	public int getID() {
		return 1;
	}
}