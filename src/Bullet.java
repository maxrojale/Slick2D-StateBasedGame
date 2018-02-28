import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Bullet {

	private float position_x, position_y;
	private int vector_x,vector_y,ttl,damage;
	private Shape shape;
	boolean delete, destroyedOnContact;
	
	
	public Bullet(Shape shape, int vector_x, int vector_y, int ttl, int damage, boolean destroyedOnContact) {
		this.shape = shape;
		this.vector_x = vector_x;
		this.vector_y = vector_y;
		this.damage=damage;
		this.ttl = ttl;
		delete = false;
		this.destroyedOnContact = destroyedOnContact;
	}
	


	public void update() {
		shape.setCenterX(shape.getCenterX()+vector_x);
		shape.setCenterY(shape.getCenterY()+vector_y);
		ttl--;
		if (ttl==0) {
			delete = true;
		}
	}

	public int getTtl() {
		return ttl;
	}

	public boolean getDelete() {
		return delete;
	}

	public void setDelete(boolean delete) {
		this.delete = delete;
	}

	public Shape getShape() {
		return shape;
	}

	public int getVector_x() {
		return vector_x;
	}

	public int getVector_y() {
		return vector_y;
	}

	public void setVector_x(int vector_x) {
		this.vector_x = vector_x;
	}

	public float getPosition_x() {
		return position_x;
	}

	public void setPosition_x(float vector) {
		this.position_x = vector;
	}

	public float getPosition_y() {
		return position_y;
	}

	public void setPosition_y(int position_y) {
		this.position_y = position_y;
	}
	public int getDamage() {
		return damage;
	}

	public boolean isDestroyedOnContact() {
		return destroyedOnContact;
	}
}