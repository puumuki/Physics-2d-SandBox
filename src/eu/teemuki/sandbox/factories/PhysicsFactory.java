package eu.teemuki.sandbox.factories;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;

import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.Fixture;

import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.joints.DistanceJointDef;
import org.jbox2d.dynamics.joints.Joint;
import org.jbox2d.dynamics.joints.RevoluteJointDef;

public class PhysicsFactory {
	
	private World world;
	
	public PhysicsFactory( World world ) {
		this.world = world;
	}
	
	public Body createDynamicBall( float x, float y, float radius ) {
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
	
		return ballBody;
	}
	
	public Body createDynamicBox(float x, float y, float width, float height ) {
		
		BodyDef dynamicBox = new BodyDef();
		
		dynamicBox.position.set(x, y);
		dynamicBox.type = BodyType.DYNAMIC;
		
		Body boxBody = world.createBody(dynamicBox);
		 
		PolygonShape boxShape = new PolygonShape();
		
		//Set as box method take parameters as "half"
		boxShape.setAsBox(width, height);
		
		Fixture fixture = boxBody.createFixture(boxShape,1.0f);
				
		fixture.setFriction(0.3f);
		fixture.setDensity(1f);
		
		return boxBody;
	}
	
	public Body createStaticBox( float x, float y, 
							   	 float width, float height,
							   	 float angle ) {
		
		BodyDef boxBodyDef = new BodyDef();
		
		boxBodyDef.position.set(x, y);
		boxBodyDef.type = BodyType.STATIC;
		
		Body boxBody = world.createBody(boxBodyDef);		
		PolygonShape ceilingShape = new PolygonShape();
		
		//Set as box method take parameters as "half"
		ceilingShape.setAsBox(width, height, new Vec2(0f,0f), angle);
						
		boxBody.createFixture(ceilingShape, 0.0f);
				
		return boxBody;
	}
	
	public Joint createDistanceJoint( Body body, Body secondBody ) {
		
		DistanceJointDef distanceJoinDef = new DistanceJointDef();
		distanceJoinDef.initialize(body, secondBody, new Vec2(0, 0), new Vec2(0,0));
		distanceJoinDef.collideConnected = true;
		
		return world.createJoint(distanceJoinDef);
	}
}
