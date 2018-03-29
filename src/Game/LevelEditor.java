package Game;
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
	private int scroller;
	private GameMap map;
	private Image background;
	Input in;
	int position;
	
	public void init(GameContainer gc, StateBasedGame gsm) throws SlickException {
		in = gc.getInput();
		background=new Image("res/starfield2.png");
		GameData.loadGameFiles();
		GameData.initializeGameData();
		map=GameData.level1;
		scroller = 0;
		position=0;
	}

	public void update(GameContainer gc, StateBasedGame gsm, int delta) throws SlickException {
		if (in.isKeyPressed(Input.KEY_ESCAPE)) {
			gsm.enterState(0,new FadeOutTransition(), new FadeInTransition());
		}

		if (in.isKeyDown(Input.KEY_RIGHT)) {
			if(position == 44 && scroller == 64);
			
			else {
			scroller+=2;
				if(scroller>64) {
					scroller=2;
					position++;
				}
			}
				
		}

		if (in.isKeyDown(Input.KEY_LEFT)) {
			if(position == 0 && scroller ==0);
			
			else {
				scroller-=2;
				if(scroller<0) {
					scroller=62;
					position--;
				}
			}
				
		}
		
			
		
		if (in.isKeyPressed(Input.KEY_S)) {
			saveMap();
		}
		if (in.isKeyPressed(Input.KEY_L)) {
			loadMap();
		}
		
		if (in.isMousePressed(0)) {
			int x = (in.getAbsoluteMouseX() + scroller) / tilesize + position;
			int y = in.getAbsoluteMouseY() / tilesize;
			map.setMapTile(x, y, new AnotherTile());
		}
		if (in.isMousePressed(1)) {
			int x = (in.getAbsoluteMouseX() + scroller) / tilesize + position;
			int y = in.getAbsoluteMouseY() / tilesize;
			map.setMapTile(x, y, new EmptyTile());
		}
	}
	
	public void render(GameContainer gc, StateBasedGame gsm, Graphics g) throws SlickException {
		background.draw(0,0);
		for (int i = 0; i < map.getMap().length;i++) {
			for (int j =0; j < Setup.WIDTH/tilesize;j++) {
				g.setColor(Color.orange);
				g.drawRect(j*tilesize-scroller, i*tilesize, tilesize, tilesize);
				g.setColor(Color.white);
				String coords = "x: " +(j+position);
				g.drawString(coords, j*tilesize-scroller, i*tilesize);
				

				if (map.getMap()[i][j+position].getID()==0) {
					g.drawImage(GameData.playerImage, j*tilesize-scroller,i*tilesize);
				}
				if (map.getMap()[i][j+position].getID()==1) {
					g.drawImage(GameData.enemyImage, j*tilesize-scroller, i*tilesize);				
				}
				
				
				if (j==Setup.WIDTH/tilesize-1) {
					g.setColor(Color.orange);
					g.drawRect(j*tilesize-scroller+tilesize, i*tilesize, tilesize, tilesize);
					g.setColor(Color.white);
					coords = "x: " +(j+position+1);
					g.drawString(coords, j*tilesize-scroller+tilesize, i*tilesize);
					if (map.getMap()[i][j+position+1].getID()==0) {
						g.drawImage(GameData.playerImage, j*tilesize-scroller+tilesize,i*tilesize);
					}
					if (map.getMap()[i][j+position+1].getID()==1) {
						g.drawImage(GameData.enemyImage, j*tilesize-scroller+tilesize, i*tilesize);				
					}
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
