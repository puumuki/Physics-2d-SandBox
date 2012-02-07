package fi.ringofsnake.world;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;

import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.Fixture;

import org.jbox2d.dynamics.World;

import fi.ringofsnake.entities.AEntity;
import fi.ringofsnake.entities.Ball;
import fi.ringofsnake.entities.Box;
import fi.ringofsnake.entities.StaticBox;

public class EntityFactory {
	
	private World world;
	
	public EntityFactory( World world ) {
		this.world = world;
	}
	
	public AEntity createDynamicBall( float x, float y, float radius ) {
		BodyDef ballDef = new BodyDef();
		
		ballDef.position.set(x, y);
		ballDef.type = BodyType.DYNAMIC;
		
		Body ballBody = world.createBody(ballDef);
		
		CircleShape ballShape = new CircleShape();
		ballShape.m_radius = radius;
		ballShape.m_p.set( new Vec2(0.0f, 0.0f));
		
		Fixture fixture = ballBody.createFixture(ballShape, 1.0f);
		
		fixture.setRestitution(0f);
		fixture.setFriction(0.1f);
		fixture.setDensity(0.01f);
	
		return new Ball(ballBody, ballShape, fixture);
	}
	
	public AEntity createDynamicBox(float x, float y, float width, float height ) {
		
		BodyDef dynamicBox = new BodyDef();
		
		dynamicBox.position.set(x, y);
		dynamicBox.type = BodyType.DYNAMIC;
		
		Body ceilingBody = world.createBody(dynamicBox);
		 
		PolygonShape boxShape = new PolygonShape();
		
		//Set as box method take parameters as "half"
		boxShape.setAsBox(width, height);
		
		Fixture fixture = ceilingBody.createFixture(boxShape,1.0f);
				
		fixture.setFriction(0.3f);
		fixture.setDensity(1f);
		
		Box box = new Box( ceilingBody, boxShape, fixture );
		return box;
	}
	
	public AEntity createStaticBox( float x, float y, 
							   		float width, float height ) {
		
		BodyDef ceilingBodyDef = new BodyDef();
		
		ceilingBodyDef.position.set(x, y);
		ceilingBodyDef.type = BodyType.STATIC;
		
		Body ceilingBody = world.createBody(ceilingBodyDef);
		System.out.println(ceilingBody.getPosition()); 
		PolygonShape ceilingShape = new PolygonShape();
		
		//Set as box method take parameters as "half"
		ceilingShape.setAsBox(width, height);
		
		Fixture boxFixture = ceilingBody.createFixture(ceilingShape, 0.0f);
				
		StaticBox box = new StaticBox( ceilingBody, ceilingShape, boxFixture);
		return box;  
	}
}
