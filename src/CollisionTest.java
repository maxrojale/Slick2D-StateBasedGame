import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class CollisionTest extends BasicGameState {
	
	private Input in;
	private Shape rect1;
	private Shape rect2;
	private Image playerImage;
	private Image enemyImage;
	private Point point;
	String RectangleCollision;
	String PCollision;
	public boolean pixelcollision;
	public Shape playerpolygon;
	float playerpolygonpoints[];
	boolean playerbitmask[][];
	boolean enemybitmask[][];
	float playerxcorrection;
	float playerycorrection;
	
	@Override
	public void init(GameContainer gc, StateBasedGame gsm) throws SlickException {
		in = gc.getInput();
		rect1=new Rectangle(550,250,64,64);
		rect2=new Rectangle(600,300,64,64);
		playerxcorrection=-6.5f;
		playerycorrection=-10f;
		playerImage=new Image("res/player.png");
		enemyImage=new Image("res/enemy1.png");
		playerImage.rotate(90);
		//playerbitmask = new boolean[playerImage.getWidth()][playerImage.getHeight()];
		//enemybitmask = new boolean[enemyImage.getWidth()][enemyImage.getHeight()];
		playerpolygonpoints = new float[]{100,148,124,100,148,148};
		
		
		RectangleCollision="Rectangle: No Collision";
		PCollision="Pixel: No Collision";

	/*	for(int i=0; i < playerImage.getHeight(); i++) {
			for(int j=0; j <playerImage.getWidth();j++) {
				if(playerImage.getColor(j, i).getAlpha()==255) {
					playerbitmask[j][i]=true;
				}
				else {
					playerbitmask[j][i]=false;
				}
				
			}
		}
		
		for(int i=0; i < enemyImage.getHeight(); i++) {
			for(int j=0; j <enemyImage.getWidth();j++) {
				if(enemyImage.getColor(j, i).getAlpha()==255) {
					enemybitmask[j][i]=true;
				}
				else {
					enemybitmask[j][i]=false;
				}		
			}
		}		
		
	*/
		
		playerpolygon = new Polygon(playerpolygonpoints);
		playerpolygon = playerpolygon.transform(Transform.createRotateTransform((float)Math.toRadians(90),playerpolygon.getCenterX(),playerpolygon.getCenterY()));
		playerxcorrection=-12f;
		playerycorrection=-6.5f;
	}
	
	
	
	@Override
	public void update(GameContainer gc, StateBasedGame gsm, int delta) throws SlickException {
		if (in.isKeyPressed(Input.KEY_ESCAPE)) {
			gsm.enterState(0);
		}
		if (in.isKeyDown(Input.KEY_RIGHT)) {
			rect1.setCenterX(rect1.getCenterX()+1);
		}
		if (in.isKeyDown(Input.KEY_LEFT)) {
			rect1.setCenterX(rect1.getCenterX()-1);
		}
		if (in.isKeyDown(Input.KEY_UP)) {
			rect1.setCenterY(rect1.getCenterY()-1);
		}
		if (in.isKeyDown(Input.KEY_DOWN)) {
			rect1.setCenterY(rect1.getCenterY()+1);
		}
		
		if(rect1.intersects(rect2)) {
			RectangleCollision = "Rectangle: Collision";
			pixelcollision=false;
			if (checkcollison()) {
				PCollision = "Pixel: Collision";
			}
			else {
				PCollision = "Pixel: No Collision";
			}
		}
		else {
			RectangleCollision = "Rectangle: No Collision";
			PCollision = "Pixel: No Collision";
		}
	}
	
	public boolean checkcollison() throws SlickException {
		int xstart = (int)rect1.getMinX();
		int xend = (int)rect1.getMaxX();
		int ystart = (int)rect1.getMinY();
		int yend = (int)rect1.getMaxY();
		int xstart2 = (int)rect2.getMinX();
		int xend2 = (int)rect2.getMaxX();
		int ystart2 = (int)rect2.getMinY();
		int yend2 = (int)rect2.getMaxY();
		
		
		
	
		for (int i = ystart; i < yend; i++) {
			for (int j = xstart; j < xend; j++) {
				point=new Point(j,i);
				if(rect2.contains(point)) {
					return true;
					//if (playerbitmask[j-xstart][i-ystart]==true && enemybitmask[j-xstart2][i-ystart2]==true) {
						//pixelcollision=true;
						//return pixelcollision;
					//}
				}
			}
		}
		return pixelcollision;
	}
		
		
	
	
	@Override
	public void render(GameContainer gc, StateBasedGame gsm, Graphics g) throws SlickException {
		g.setColor(Color.cyan);
		g.fill(playerpolygon);
		//g.fill(rect1);
		enemyImage.draw(rect2.getMinX(),rect2.getMinY());
		playerImage.draw(playerpolygon.getMinX()+playerxcorrection,playerpolygon.getMinY()+playerycorrection);
		//g.setColor(Color.magenta);
		//g.fill(rect2);
		g.setColor(Color.white);
		g.drawString(RectangleCollision, 10, 10);
		g.drawString(PCollision, 10, 50);
	}



	@Override
	public int getID() {
		return 8;
	}

}
