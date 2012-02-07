package eu.teemuki.sandbox.world;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import eu.teemuki.sandbox.color.ImageDispencer;
import eu.teemuki.sandbox.color.SandboxConstant;
import eu.teemuki.sandbox.entities.AEntity;
import eu.teemuki.sandbox.entities.BasicEntity;
import eu.teemuki.sandbox.entities.IGameObject;
import eu.teemuki.sandbox.factories.EntityFactory;
import eu.teemuki.sandbox.factories.PhysicsFactory;
import eu.teemuki.sandbox.factories.RenderedFactory;
import eu.teemuki.sandbox.renderer.IRenderer;

public class Simulation implements IGameObject {

	private List<AEntity> entities = new ArrayList<AEntity>();
	
	private World world;
	
	private PhysicsFactory physicsFactory;
	private RenderedFactory renderedFactory;
	private EntityFactory entityFactory;
	
	public Simulation() {		
		initWorldAndFactories();		
		createWorld();
	}

	private void initWorldAndFactories() {		
		Vec2 grafity = new Vec2(0.0f, 10.0f);
		world = new World(grafity, true );		
		physicsFactory = new PhysicsFactory(world);
		renderedFactory = new RenderedFactory();
		entityFactory = new EntityFactory();
	}
	
	private void createWorld() {		
		Body body = physicsFactory.createDynamicBall(20, 0, 1.5f);
		IRenderer renderer = renderedFactory.createDynamicBallRendered(body, ImageDispencer.randomBallImage() );
		BasicEntity entity = entityFactory.createDynamicBall(body, renderer);		
		entities.add(entity);
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
		
		if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
			
			float x  = (input.getMouseX() - cont.getWidth() / 2) / SandboxConstant.ONE_METER_EQUALS_PX;
			float y = (input.getMouseY() - cont.getHeight() / 2) / SandboxConstant.ONE_METER_EQUALS_PX;
			
			Body body = physicsFactory.createDynamicBall(x , y, 1.5f);
			IRenderer renderer = renderedFactory.createDynamicBallRendered(body, ImageDispencer.randomBallImage() );
			entities.add(entityFactory.createDynamicBall(body, renderer));
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
