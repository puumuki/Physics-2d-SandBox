package eu.teemuki.sandbox.entities;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.Fixture;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;

public class StaticBox {

//	private static final Color BOX_COLOR = new Color(Integer.valueOf("b7e5f6", 16));
//	private Polygon drawableShape;
//	
//	public StaticBox(Body body, PolygonShape shape, Fixture fixture) {
//		super(body, shape, fixture);
//		this.color = BOX_COLOR;
//		
//		this.drawableShape = new Polygon();
//		
//		Vec2 pos = this.body.getPosition();
//		
//		for( int i=0; i<shape.getVertexCount(); i++ ) {
//			this.drawableShape.addPoint( pos.x + shape.m_vertices[i].x, pos.y + shape.m_vertices[i].y);
//		}
//	}
//
//	@Override
//	public void render(GameContainer cont, Graphics grap) throws SlickException {		
//		grap.setColor(this.color);
//		grap.draw(drawableShape);	
//	}
//
//	@Override
//	public void update(GameContainer cont, int delta) throws SlickException {
//		drawableShape.setCenterX( body.getPosition().x );
//		drawableShape.setCenterY( body.getPosition().y );
//	}
}
