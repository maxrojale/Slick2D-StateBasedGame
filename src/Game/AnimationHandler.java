package Game;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;

public class AnimationHandler {
	private float x,y;
	private int framecounter;
	private SpriteSheet spriteSheet = GameData.explosionSheet; 
	private Animation animation = new Animation(spriteSheet,100);
	private boolean playAnimation=false;

	public boolean PlayAnimation() {
		return playAnimation;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

}
