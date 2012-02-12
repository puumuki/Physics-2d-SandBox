package eu.teemuki.sandbox.world;

import java.util.Iterator;

import org.jbox2d.common.MathUtils;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import eu.teemuki.sandbox.entities.AEntity;
import eu.teemuki.sandbox.renderer.IRenderer;
import eu.teemuki.sandbox.renderer.StaticBoxRenderer;
import eu.teemuki.sandbox.utils.ResourceDispencer;
import eu.teemuki.sandbox.utils.SandboxConstant;

public class ConveyorSimulation extends AbstractSimulation  {

	private Vector2f cameraOffset;
	
	public ConveyorSimulation() {		
		super();		
		createWorld();		
		cameraOffset = new Vector2f(0,0);
	}
	
	private void createWorld() {
		
		Body body = physicsFactory.createStaticBox( -42.5f, 0, 20, 0.1f, MathUtils.PI / 2);
		StaticBoxRenderer renderer = renderedFactory.createStaticBoxRendered(body);
		renderer.setDebugShapeColor(Color.red);
		entities.add( entityFactory.createBasicEntity(body, renderer));
		
		body = physicsFactory.createStaticBox( 42.5f, 0, 20, 0.1f, MathUtils.PI / 2);
		renderer = renderedFactory.createStaticBoxRendered(body);
		renderer.setDebugShapeColor(Color.red);
		entities.add( entityFactory.createBasicEntity(body, renderer));
		
		float y = -10;
		
		for( float x=-40; x<35; x += 2.5f ) {
			body = physicsFactory.createDynamicBall(x, y, 1.0f);
			body.getFixtureList().m_friction = 1f;
			physicsFactory.createRevoluteJoin(body, x, y, 2.0f * MathUtils.PI);		
			IRenderer ballRenderer = renderedFactory.createDynamicBallRendered(body, ResourceDispencer.randomBallImage());
			entities.add(entityFactory.createBasicEntity(body, ballRenderer));
		}
		
		y = 0;
		
		for( float x=-30; x<45; x += 2.5f ) {
			body = physicsFactory.createDynamicBall(x, y, 1);
			body.getFixtureList().m_friction = 1f;
			physicsFactory.createRevoluteJoin(body, x, y, -2.0f * MathUtils.PI);		
			IRenderer ballRenderer = renderedFactory.createDynamicBallRendered(body, ResourceDispencer.randomBallImage());
			entities.add(entityFactory.createBasicEntity(body, ballRenderer));
		}
		
		y = 10;
		
		for( float x=-40; x<35; x += 2.5f ) {
			body = physicsFactory.createDynamicBall(x, y, 1);
			body.getFixtureList().m_friction = 1f;
			physicsFactory.createRevoluteJoin(body, x, y, 2.0f * MathUtils.PI);		
			IRenderer ballRenderer = renderedFactory.createDynamicBallRendered(body, ResourceDispencer.randomBallImage());
			entities.add(entityFactory.createBasicEntity(body, ballRenderer));
		}
		

		y = 20;
		
		for( float x=-40; x<45; x += 2.5f ) {
			body = physicsFactory.createDynamicBall(x, y, 1);
			body.getFixtureList().m_friction = 1f;
			physicsFactory.createRevoluteJoin(body, x, y, -2.0f * MathUtils.PI);		
			IRenderer ballRenderer = renderedFactory.createDynamicBallRendered(body, ResourceDispencer.randomBallImage());
			entities.add(entityFactory.createBasicEntity(body, ballRenderer));
		}
	}
			
	public void render(GameContainer cont, Graphics g) throws SlickException {				
	
		g.setColor(Color.orange);
		g.drawString("Total item count: " + entitiesCurrentCount(), 10, 10 );
		
		g.translate(cont.getWidth()/2 + cameraOffset.x, cont.getHeight()/2 + cameraOffset.y);
		g.scale( SandboxConstant.ONE_METER_EQUALS_PX, 
				 SandboxConstant.ONE_METER_EQUALS_PX);
				
		for (AEntity item : entities) {
			item.render(cont, g);
		}
		
		g.resetTransform();
	}
	
	public void update( GameContainer cont, int delta ) throws SlickException {			
		
		world.step( (float)delta / 1000 , 
					SandboxConstant.VELOCITY_ITERATIONS, 
					SandboxConstant.POSITION_ITERATIONS);		
				
		for( AEntity item : entities ) {
			item.update(cont, delta);
		}
		
		Input input = cont.getInput();
		
		handleMouseInput(cont, input);
		handleCameraMovements( input, delta );
		
		cleanObjectOutSideOfScreen( cont.getWidth(), cont.getHeight() );
	}

	public void handleMouseInput(GameContainer cont, Input input) {
		
		float x  = (input.getMouseX() - cont.getWidth() / 2) / SandboxConstant.ONE_METER_EQUALS_PX;
		x -= cameraOffset.x / SandboxConstant.ONE_METER_EQUALS_PX; 
		
		float y = (input.getMouseY() - cont.getHeight() / 2) / SandboxConstant.ONE_METER_EQUALS_PX; 
		y -= cameraOffset.y / SandboxConstant.ONE_METER_EQUALS_PX;
		
		if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
			Body body = physicsFactory.createDynamicBall(x , y, 1.5f);
			body.getFixtureList().m_friction = 0.6f;
			IRenderer renderer = renderedFactory.createDynamicBallRendered(body, ResourceDispencer.fetchRandomDuck() );
			entities.add(entityFactory.createBasicEntity(body, renderer));
		}
		
		if( input.isMouseButtonDown(Input.MOUSE_RIGHT_BUTTON)) {
			Body body = physicsFactory.createDynamicBox(x , y, 1.5f, 1.5f);
			IRenderer renderer = renderedFactory.createDynamicBoxRendered(body, ResourceDispencer.randomBoxImage() );
			entities.add(entityFactory.createBasicEntity(body, renderer));
		}
	}
	
	private void handleCameraMovements(Input input, int delta) {
		
		float step = 0.1f * delta;
		
		if( input.isKeyDown(Input.KEY_LEFT)) {
			cameraOffset.x -= step; 
		}
		
		if( input.isKeyDown(Input.KEY_RIGHT)) {
			cameraOffset.x += step; 
		}
		
		if( input.isKeyDown(Input.KEY_DOWN)) {
			cameraOffset.y += step;		
		}
		
		if( input.isKeyDown(Input.KEY_UP)) {
			cameraOffset.y -= step;
		}
	}
	
	private void cleanObjectOutSideOfScreen( int screenWidth, int screenHeight ) {
		
		float xOffset = screenWidth / 2;
		float yOffset = screenHeight / 2;

		Iterator<AEntity> iterator = entities.iterator();
		
		while( iterator.hasNext() ) {
			
			AEntity entity = iterator.next();
			
			Vec2 pos = entity.body.getPosition();
			
			boolean notInsideScreen  = !((pos.x + xOffset >= 0 && pos.x + xOffset <= screenWidth) && 
										 (pos.y + yOffset >= 0 && pos.y + yOffset <= screenHeight));
			
			if( notInsideScreen ) {
				entity.body.destroyFixture(entity.fixture);
				world.destroyBody(entity.body);
				iterator.remove();
			}
		}
	}
	
	public int entitiesCurrentCount() {
		return entities.size();
	}
}
