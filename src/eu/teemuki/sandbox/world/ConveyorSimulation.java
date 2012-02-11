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

import eu.teemuki.sandbox.color.ImageDispencer;
import eu.teemuki.sandbox.color.SandboxConstant;
import eu.teemuki.sandbox.entities.AEntity;
import eu.teemuki.sandbox.renderer.IRenderer;
import eu.teemuki.sandbox.renderer.StaticBoxRenderer;

public class ConveyorSimulation extends AbstractSimulation  {

	public ConveyorSimulation() {		
		super();		
		createWorld();
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
			IRenderer ballRenderer = renderedFactory.createDynamicBallRendered(body, ImageDispencer.randomBallImage());
			entities.add(entityFactory.createBasicEntity(body, ballRenderer));
		}
		
		y = 0;
		
		for( float x=-30; x<45; x += 2.5f ) {
			body = physicsFactory.createDynamicBall(x, y, 1);
			body.getFixtureList().m_friction = 1f;
			physicsFactory.createRevoluteJoin(body, x, y, -2.0f * MathUtils.PI);		
			IRenderer ballRenderer = renderedFactory.createDynamicBallRendered(body, ImageDispencer.randomBallImage());
			entities.add(entityFactory.createBasicEntity(body, ballRenderer));
		}
		
		y = 10;
		
		for( float x=-40; x<35; x += 2.5f ) {
			body = physicsFactory.createDynamicBall(x, y, 1);
			body.getFixtureList().m_friction = 1f;
			physicsFactory.createRevoluteJoin(body, x, y, 2.0f * MathUtils.PI);		
			IRenderer ballRenderer = renderedFactory.createDynamicBallRendered(body, ImageDispencer.randomBallImage());
			entities.add(entityFactory.createBasicEntity(body, ballRenderer));
		}
		

		y = 20;
		
		for( float x=-40; x<45; x += 2.5f ) {
			body = physicsFactory.createDynamicBall(x, y, 1);
			body.getFixtureList().m_friction = 1f;
			physicsFactory.createRevoluteJoin(body, x, y, -2.0f * MathUtils.PI);		
			IRenderer ballRenderer = renderedFactory.createDynamicBallRendered(body, ImageDispencer.randomBallImage());
			entities.add(entityFactory.createBasicEntity(body, ballRenderer));
		}
	}
			
	public void render(GameContainer cont, Graphics g) throws SlickException {				
	
		g.setColor(Color.orange);
		g.drawString("Total item count: " + entitiesCurrentCount(), 10, 10 );
		
		g.translate(cont.getWidth()/2, cont.getHeight()/2);
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
		
		cleanObjectOutSideOfScreen( cont.getWidth(), cont.getHeight() );
	}

	public void handleMouseInput(GameContainer cont, Input input) {
					
		if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
			
			float x  = (input.getMouseX() - cont.getWidth() / 2) / SandboxConstant.ONE_METER_EQUALS_PX;
			float y = (input.getMouseY() - cont.getHeight() / 2) / SandboxConstant.ONE_METER_EQUALS_PX;
			
			Body body = physicsFactory.createDynamicBall(x , y, 1.5f);
			body.getFixtureList().m_friction = 0.6f;
			IRenderer renderer = renderedFactory.createDynamicBallRendered(body, ImageDispencer.randomBallImage() );
			entities.add(entityFactory.createBasicEntity(body, renderer));
		}
		
		if( input.isMouseButtonDown(Input.MOUSE_RIGHT_BUTTON)) {
			float x  = (input.getMouseX() - cont.getWidth() / 2) / SandboxConstant.ONE_METER_EQUALS_PX;
			float y = (input.getMouseY() - cont.getHeight() / 2) / SandboxConstant.ONE_METER_EQUALS_PX;
			
			Body body = physicsFactory.createDynamicBox(x , y, 1.5f, 1.5f);
			IRenderer renderer = renderedFactory.createDynamicBoxRendered(body, ImageDispencer.randomBoxImage() );
			entities.add(entityFactory.createBasicEntity(body, renderer));
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
