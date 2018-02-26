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


public class MenuState extends BasicGameState {
	
	private Input in;
	private Image menuImage;
	private Music menuMusic;
	
	@Override
	public void init(GameContainer gc, StateBasedGame gsm)
			throws SlickException {
		in = gc.getInput();
		menuImage = new Image("res/mainMenue.png");
		menuMusic = new Music("res/boss.ogg");
	}

	@Override
	public void update(GameContainer gc, StateBasedGame gsm, int delta) throws SlickException {
		if (!menuMusic.playing()) {
			menuMusic.loop();
		}
		if (in.isKeyPressed(Input.KEY_SPACE)) {
			menuMusic.stop();
			gsm.enterState(1,new FadeOutTransition(), new FadeInTransition());
		}
		if (in.isKeyPressed(Input.KEY_ESCAPE)) {
			menuMusic.stop();
			gsm.enterState(7,new FadeOutTransition(), new FadeInTransition());
		}
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame gsm, Graphics g)
			throws SlickException {
			//g.drawImage(menuImage, 0, 0, 960, 540,0,0,320,240);
			g.drawImage(menuImage,0,0);
			g.drawString("Press Space to start the Game", Setup.WIDTH/2-150, Setup.HEIGHT/2);
	}

	@Override
	public int getID() {
		return 0;
	}

}