import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;


public class GameOverState extends BasicGameState {

	private Input in;
	
	@Override
	public void init(GameContainer gc, StateBasedGame gsm)
			throws SlickException {
		in = gc.getInput();
	}

	@Override
	public void update(GameContainer gc, StateBasedGame gsm, int delta)
		
			throws SlickException {
<<<<<<< HEAD
		if (in.isKeyPressed(Input.KEY_SPACE)) {
=======
		if (in.isKeyPressed(Input.KEY_ESCAPE)) {
			GameData.GameOver=false;
			in.clearKeyPressedRecord();
>>>>>>> b707260b4b4698ee0e752cd20c457dea8379d89c
			gsm.enterState(1,new FadeOutTransition(), new FadeInTransition());
		}
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame gsm, Graphics g)
			throws SlickException {
<<<<<<< HEAD
		g.drawString("Game Over. Press Space", 50, 50);
=======
		g.drawString("Game Over.... Press ESC to Restart", Setup.WIDTH/2-150, Setup.HEIGHT/2);
>>>>>>> b707260b4b4698ee0e752cd20c457dea8379d89c
	}

	@Override
	public int getID() {
		return 9;
	}
}