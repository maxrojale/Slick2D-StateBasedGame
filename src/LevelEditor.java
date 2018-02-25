import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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

import Tiles.AnotherTile;
import Tiles.EmptyTile;


public class LevelEditor extends BasicGameState {
	
	private int tilesize = 64;
	private GameMap map;
	private Image background;
	private Player player;
	Input in;
	int position;
	
	public void init(GameContainer gc, StateBasedGame gsm) throws SlickException {
		in = gc.getInput();
		background=new Image("res/starfield2.png");
		player = GameData.player;
		GameData.loadGameFiles();
		GameData.initializeGameData();
		map=GameData.level1;
		position=0;
	}

	public void update(GameContainer gc, StateBasedGame gsm, int delta) throws SlickException {
		if (in.isKeyPressed(Input.KEY_ESCAPE)) {
			gsm.enterState(0,new FadeOutTransition(), new FadeInTransition());
		}
		if (in.isKeyDown(Input.KEY_RIGHT) && position < 45) {
			position++;
		}
		if (in.isKeyDown(Input.KEY_LEFT) && position > 0) {
			position--;
		}
		if (in.isKeyPressed(Input.KEY_S)) {
			saveMap();
		}
		if (in.isKeyPressed(Input.KEY_L)) {
			loadMap();
		}
		
		if (in.isMousePressed(0)) {
			int x = in.getAbsoluteMouseX() / tilesize + position;
			int y = in.getAbsoluteMouseY() / tilesize;
			map.setMapTile(x, y, new AnotherTile());
		}
		if (in.isMousePressed(1)) {
			int x = in.getAbsoluteMouseX() / tilesize + position;
			int y = in.getAbsoluteMouseY() / tilesize;
			map.setMapTile(x, y, new EmptyTile());
		}
	}
	
	public void render(GameContainer gc, StateBasedGame gsm, Graphics g) throws SlickException {
		background.draw(0,0);
		for (int i = 0; i < map.getMap().length;i++) {
			for (int j =0; j < Setup.WIDTH/tilesize;j++) {
				g.setColor(Color.orange);
				g.drawRect(j*tilesize, i*tilesize, tilesize, tilesize);
				g.setColor(Color.white);
				String coords = "x: " +(j+position);
				g.drawString(coords, j*tilesize, i*tilesize);
				if (map.getMap()[i][j+position].getID()==0) {
					g.drawImage(GameData.playerImage, j*tilesize,i*tilesize);
				}
				if (map.getMap()[i][j+position].getID()==1) {
					g.drawImage(GameData.enemyImage, j*tilesize, i*tilesize);
					
				}
			}
		}
	}
	
	public void saveMap() {
		try (FileOutputStream fos = new FileOutputStream ("res/level.map");
			     ObjectOutputStream oos = new ObjectOutputStream (fos)) {
				oos.writeObject (map);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadMap() {
		try (FileInputStream fis = new FileInputStream ("res/level.map");
			    ObjectInputStream ois = new ObjectInputStream (fis)) {
			 	map = (GameMap) ois.readObject ();
		
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	
	public int getID() {
		return 7;
	}

}
