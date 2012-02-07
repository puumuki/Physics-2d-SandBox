package fi.ringofsnake.entities;

import java.util.Random;

import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.Fixture;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Ellipse;

import fi.ringofsnake.io.ResourceManager;
import fi.sandbox.color.ImageDispencer;
import fi.sandbox.color.SandboxConstant;


public class Ball extends AEntity {

	private Ellipse drawableShape;
	
	private Image ball ;
	
	public Ball( Body body, Shape shape, Fixture fixture ) {
		super( body, shape, fixture );		

		ball = ImageDispencer.randomBallImage();
		
		Vec2 pos = body.getPosition();
		drawableShape = new Ellipse(pos.x, pos.y, shape.m_radius, shape.m_radius);
		
		double width =  SandboxConstant.SCALE * ball.getWidth();
		width = shape.m_radius * 2 * width * SandboxConstant.SCALE;
		this.ball = ball.getScaledCopy((int)(Math.ceil(drawableShape.getWidth())), 
										(int)(Math.ceil(drawableShape.getWidth())));
		
		System.out.println( width + " O " + ball.getWidth() + " R " + drawableShape.getWidth() + " B " + ball.getWidth() + " " + shape.m_radius);
		
		float x = (float)ball.getWidth()/2;
		float y = (float)ball.getHeight()/2;
		
		ball.setCenterOfRotation( x, y );
		
		
	}
	
	@Override
	public void render(GameContainer cont, Graphics grap) throws SlickException {		
		Vec2 pos = body.getPosition();
		grap.drawImage( ball, pos.x - shape.m_radius , 
				   			  pos.y - shape.m_radius ); 
	}

	@Override
	public void update(GameContainer cont, int delta) throws SlickException {
		ball.setRotation( (float) Math.toDegrees(body.getAngle()));
	}
}
