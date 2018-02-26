

import java.io.Serializable;

import org.newdawn.slick.SlickException;

import Tiles.*;

public class GameMap implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Tile[][] map;
	
	public GameMap(int sizex, int sizey) throws SlickException {
		map = new Tile[sizey][sizex];
		initializeMap();
	}
	
	public void initializeMap() throws SlickException {
		for(int i=0; i < map.length; i++) {
			for(int j=0; j < map[0].length; j++) {
				map[i][j] = new EmptyTile();
			}
		}
		map[3][3] = new AnotherTile();
	}

	public Tile[][] getMap() {
		return map;
	}

	public void setMap(Tile[][] map) {
		this.map = map;
	}
	
	public void setMapTile(int x, int y, Tile tile) {
		map[y][x] = tile;
	}
	
	public Tile getMapTile(int x, int y) {
		return map[y][x];
	}
}