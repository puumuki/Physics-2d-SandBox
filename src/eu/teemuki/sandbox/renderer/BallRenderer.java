package eu.teemuki.sandbox.renderer;

import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Ellipse;

import eu.teemuki.sandbox.color.SandboxConstant;


public class BallRenderer extends AbstractRendered implements IRenderer {
	
	protected Image image;		
	
	public BallRenderer( Body body, Image image ) {
		
		super( body );
		
		this.image = image;
				
		//Init rendeable shape that can be used to debugging drawing
		float radius = body.m_fixtureList.m_shape.m_radius;		
		Vec2 pos = body.getPosition();
		rendeableShape = new Ellipse(pos.x, pos.y, radius, radius);
		
		//Down scaling image to size
		double width =  SandboxConstant.SCALE * image.getWidth();
		width = radius * 2 * width * SandboxConstant.SCALE;
		
		
		this.image = image.getScaledCopy((int)(Math.ceil(rendeableShape.getWidth())), 
										(int)(Math.ceil(rendeableShape.getWidth())));
		
		//Calculating center of rotation
		float x = (float)this.image.getWidth()/2;
		float y = (float)this.image.getHeight()/2;		
		this.image.setCenterOfRotation( x, y );
	}

	@Override
	public void update(GameContainer cont, int delta) throws SlickException {
		image.setRotation( (float) Math.toDegrees(body.getAngle()));
		rendeableShape.setCenterX(body.getPosition().x);
		rendeableShape.setCenterY(body.getPosition().y);
	}	
	
	@Override
	public void render(GameContainer cont, Graphics g) throws SlickException {
		Vec2 pos = body.getPosition();
		Shape shape = body.m_fixtureList.m_shape;		
		g.drawImage( image, pos.x - shape.m_radius , 
			   		 		pos.y - shape.m_radius ); 
		g.draw(rendeableShape);
	}

	@Override
	public Body getBody() {
		return body;
	}

	@Override
	public Shape getBodyShape() {
		return body.getFixtureList().getShape();
	}
}
