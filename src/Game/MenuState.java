package Game;
import org.newdawn.slick.Color;
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
	private int highlightedMenuItem;
	private Image menuImage;
	private Music menuMusic;
	
	@Override
	public void init(GameContainer gc, StateBasedGame gsm)
			throws SlickException {
		in = gc.getInput();
		menuImage = new Image("res/mainMenue.png");
		menuMusic = new Music("res/boss.ogg");
		highlightedMenuItem=1;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame gsm, int delta) throws SlickException {
		if (!menuMusic.playing()) {
			menuMusic.loop();
		}
		if (in.isKeyPressed(Input.KEY_UP)) {
			highlightedMenuItem--;
			if(highlightedMenuItem==0) {
				highlightedMenuItem=3;
			}
		}
		if (in.isKeyPressed(Input.KEY_DOWN)) {
			highlightedMenuItem++;
			if(highlightedMenuItem==4) {
				highlightedMenuItem=1;
			}
		}
		if (in.isKeyPressed(Input.KEY_SPACE) || in.isKeyPressed(Input.KEY_RETURN)) {
			switch(highlightedMenuItem) {
				case 1: 
					menuMusic.stop();
					gsm.enterState(1,new FadeOutTransition(), new FadeInTransition());
					break;
				case 2:
					menuMusic.stop();
					gsm.enterState(7,new FadeOutTransition(), new FadeInTransition());
					break;
				case 3:
					menuMusic.stop();
					System.exit(0);
				
				default:
					break;
			}
			
		}
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame gsm, Graphics g)
			throws SlickException {
			//g.drawImage(menuImage, 0, 0, 960, 540,0,0,320,240);
			g.drawImage(menuImage,0,0);
			if(highlightedMenuItem==1) {
				g.setColor(Color.orange);
				g.drawString("Game",Setup.WIDTH/2, Setup.HEIGHT/2-100);
				g.setColor(Color.white);
			}
			else {
				g.drawString("Game",Setup.WIDTH/2, Setup.HEIGHT/2-100);
			}
			
			if(highlightedMenuItem==2) {
				g.setColor(Color.orange);
				g.drawString("Editor",Setup.WIDTH/2, Setup.HEIGHT/2-50);
				g.setColor(Color.white);
			}
			else {
				g.drawString("Editor",Setup.WIDTH/2, Setup.HEIGHT/2-50);
			}
			
			if(highlightedMenuItem==3) {
				g.setColor(Color.orange);
				g.drawString("Quit",Setup.WIDTH/2, Setup.HEIGHT/2);
				g.setColor(Color.white);
			}
			else {
				g.drawString("Quit",Setup.WIDTH/2, Setup.HEIGHT/2);
			}
			
	
	}

	@Override
	public int getID() {
		return 0;
	}

}