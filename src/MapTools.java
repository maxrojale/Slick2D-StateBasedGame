import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class MapTools {
	
	public void saveMap(GameMap map) throws IOException {
		try (FileOutputStream fos = new FileOutputStream ("res/level.map");
			     ObjectOutputStream oos = new ObjectOutputStream (fos)) {
				oos.writeObject (map);
		}
	}
}
