import org.newdawn.slick.Animation;

public class Explosion {

	private float x, y;
	int frame;
	boolean animationPlayed;
	Animation animation;
	
	public Explosion(float x, float y) {
		this.x = x;
		this.y = y;
		animationPlayed = false;
		frame=0;
		animation = GameData.explosionAnimation;
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
		if (animation.getFrame() == animation.getFrameCount()-1) {
			animationPlayed=true;
			animation.restart();
		}
	}

	public boolean isAnimationPlayed() {
		return animationPlayed;
	}
}
