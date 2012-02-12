package eu.teemuki.sandbox.entities;

import org.jbox2d.collision.shapes.PolygonShape;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.Fixture;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

import eu.teemuki.sandbox.utils.ResourceDispencer;
import eu.teemuki.sandbox.utils.SandboxConstant;


public class Box  {

//
//	
//	private Image image;
//	
//	protected Polygon drawableShape;
//	
//	public Box(Body body, PolygonShape shape, Fixture fixture) {
//		
//		super(body, shape, fixture);
//		
//		this.image = ImageDispencer.randomBoxImage();
//		
//		this.drawableShape = new Polygon();
//		
//		Vec2 pos = this.body.getPosition();
//		
//		for( int i=0; i<shape.getVertexCount(); i++ ) {
//			this.drawableShape.addPoint( pos.x + shape.m_vertices[i].x, pos.y + shape.m_vertices[i].y);
//		}
//
//		double width =  SandboxConstant.SCALE * image.getWidth();
//		width = shape.m_radius * 2 * width * SandboxConstant.SCALE;
//		this.image = image.getScaledCopy((int)(Math.ceil(drawableShape.getWidth())), 
//										(int)(Math.ceil(drawableShape.getHeight())));
//		
//		
//		float x = (float)image.getWidth()/2;
//		float y = (float)image.getHeight()/2;
//		
//		image.setCenterOfRotation( x, y );
//	}
//	
//	@Override
//	public void render(GameContainer cont, Graphics grap) throws SlickException {		
//		Vec2 pos = body.getPosition();
//		grap.drawImage( image, pos.x - drawableShape.getWidth()/2 , 
//				   			  	pos.y - drawableShape.getWidth()/2 );
//		grap.draw(drawableShape);
//	}
//
//	@Override
//	public void update(GameContainer cont, int delta) throws SlickException {
//		drawableShape.setCenterX( body.getPosition().x );
//		drawableShape.setCenterY( body.getPosition().y );
//		image.setRotation( (float)Math.toDegrees(body.getAngle()));
//	}
}
