import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

public class Bullet {

	private float position_x, position_y;
	@SuppressWarnings("unused")
	private int vector, radius;
	private Shape shape;
	boolean delete;
	
	public Bullet(float position_x, float position_y, int radius, int vector) {
		this.position_x = position_x;
		this.position_y = position_y;
		this.radius = radius;
		this.vector = vector;
		delete = false;
		shape = new Circle(position_x,position_y,radius);
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
		return vector;
	}

	public void setVector_x(int vector_x) {
		this.vector = vector_x;
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
}