package eu.teemuki.sandbox.renderer;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;

public class StaticBoxRenderer extends AbstractRendered {
	
	private static final Color BOX_COLOR = new Color(Integer.valueOf("b7e5f6", 16));	
	
	public StaticBoxRenderer( Body body ) {
		super( body );
		
		this.debugColor = BOX_COLOR;		
		Polygon rendeableShape = new Polygon();
		PolygonShape shape = (PolygonShape)body.m_fixtureList.m_shape;
		
		Vec2 pos = this.body.getPosition();
		
		for( int i=0; i<shape.getVertexCount(); i++ ) {
			rendeableShape.addPoint( pos.x + shape.m_vertices[i].x, 
									pos.y + shape.m_vertices[i].y);
		}
		
		this.rendeableShape = rendeableShape;
	}
		
	@Override
	public Shape getBodyShape() {
		return null;
	}

	@Override
	public void update(GameContainer cont, int delta) throws SlickException {
		rendeableShape.setCenterX( body.getPosition().x );
		rendeableShape.setCenterY( body.getPosition().y );		
	}

	@Override
	public void render(GameContainer cont, Graphics g) throws SlickException {
		g.setColor(this.debugColor);
		g.draw(rendeableShape);		
	}

}
