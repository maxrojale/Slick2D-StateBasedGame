package Tiles;

import java.io.Serializable;

import org.newdawn.slick.SlickException;

public class AnotherTile implements Tile, Serializable {
	

	@Override
	public int getID() throws SlickException {
		return 1;
	}

}
