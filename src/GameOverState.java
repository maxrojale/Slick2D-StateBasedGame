import org.newdawn.slick.Color;
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
	public void update(GameContainer gc, StateBasedGame gsm, int delta) throws SlickException {
		
		if (in.isKeyPressed(Input.KEY_ESCAPE)) {
			GameData.GameOver=false;
			GameData.score=0;
			in.clearKeyPressedRecord();
			gsm.enterState(1,new FadeOutTransition(), new FadeInTransition());
		}
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame gsm, Graphics g)
			throws SlickException {
		g.setColor(Color.white);
		g.drawString("Game Over.... Press ESC to Restart", Setup.WIDTH/2-150, Setup.HEIGHT/2);
		g.setColor(Color.orange);
		g.drawString("Final Score: " + GameData.score, Setup.WIDTH/2-150, Setup.HEIGHT/2+50);
	}

	@Override
	public int getID() {
		return 9;
	}
}