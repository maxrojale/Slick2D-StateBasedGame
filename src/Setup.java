import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class Setup extends StateBasedGame {

	public static final int WIDTH = 960;
	public static final int HEIGHT = 540;
	public static final int FPS = 30;
	public static final boolean FULLSCREEN = false;
	public static final String title = "Test";
	
	public Setup(String name) {
		super(name);
		
	}

	public static void main(String[] args) throws SlickException {
		AppGameContainer appgc = new AppGameContainer(new Setup(title));
		appgc.setDisplayMode(WIDTH, HEIGHT, FULLSCREEN);
		appgc.setTargetFrameRate(FPS);
		appgc.setShowFPS(false);
		appgc.start();
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.addState(new MenuState()); // ID 0
		this.addState(new GameState()); // ID 1
		this.addState(new CollisionTest()); // ID 8
		this.addState(new GameOverState()); // ID 9
		
	}
}