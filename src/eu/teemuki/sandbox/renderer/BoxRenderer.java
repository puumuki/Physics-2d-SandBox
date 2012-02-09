package eu.teemuki.sandbox.renderer;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Transform;

import eu.teemuki.sandbox.color.SandboxConstant;

public class BoxRenderer extends AbstractRendered {

	private Image image;
	
	public BoxRenderer( Body body, Image image ) {
		
		super( body );
		
		this.image = image;
		
		Polygon slickPolygonShape = new Polygon();
		this.rendeableShape = slickPolygonShape;
		
		Vec2 pos = this.body.getPosition();
		
		PolygonShape shape = (PolygonShape)body.m_fixtureList.m_shape;
		
		for( int i=0; i<shape.getVertexCount(); i++ ) {
			slickPolygonShape.addPoint( pos.x + shape.m_vertices[i].x, pos.y + shape.m_vertices[i].y);
		}

		double width =  SandboxConstant.SCALE * image.getWidth();
		width = shape.m_radius * 2 * width * SandboxConstant.SCALE;
		this.image = image.getScaledCopy((int)(Math.ceil(slickPolygonShape.getWidth())), 
										(int)(Math.ceil(slickPolygonShape.getHeight())));
				
		float x = (float)this.image.getWidth()/2;
		float y = (float)this.image.getHeight()/2;
		
		this.image.setCenterOfRotation( x, y );
	}

	@Override
	public Shape getBodyShape() {
		return body.m_fixtureList.m_shape;
	}

	@Override
	public void update(GameContainer cont, int delta) throws SlickException {
		rendeableShape.setCenterX( body.getPosition().x );
		rendeableShape.setCenterY( body.getPosition().y );	
		image.setRotation( (float)Math.toDegrees(body.getAngle()));
	}

	@Override
	public void render(GameContainer cont, Graphics g) throws SlickException {
		Vec2 pos = body.getPosition();
		g.drawImage( image, pos.x - rendeableShape.getWidth()/2 , 
				   			  	pos.y - rendeableShape.getWidth()/2 );		
	}

}
