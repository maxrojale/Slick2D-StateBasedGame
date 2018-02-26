import org.newdawn.slick.Animation;

public class Explosion {

	private float x, y;
	int frame, vector;
	boolean animationPlayed;
	Animation animation;
	
	
	public Explosion(float x, float y, int vector) {
		this.x = x;
		this.y = y;
		this.vector=vector;
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
