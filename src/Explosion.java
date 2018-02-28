import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;

public class Explosion {

	private float x, y;
	int frame, vector;
	boolean animationPlayed;
	SpriteSheet spritesheet;
	Animation animation;
	
	
	public Explosion(float x, float y, int vector) {
		this.x = x;
		this.y = y;
		this.vector=vector;
		animationPlayed = false;
		frame=0;
		spritesheet = GameData.explosionSheet;
		animation = new Animation(spritesheet, 60);
	}
	
	public Animation getAnimation() {
		return animation;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
	
	public void update() {
		x-=vector;
		if (animation.getFrame() == animation.getFrameCount()-1) {
			animationPlayed=true;
			animation.restart();
		}
	}

	public boolean isAnimationPlayed() {
		return animationPlayed;
	}
}
