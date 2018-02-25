package Tiles;

import java.io.Serializable;

import org.newdawn.slick.SlickException;

public class EmptyTile implements Tile, Serializable {
	

	public int getID() throws SlickException {
		return 0;
	}
	
}
