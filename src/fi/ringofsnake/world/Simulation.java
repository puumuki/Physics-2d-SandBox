package fi.ringofsnake.world;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import fi.ringofsnake.entities.AEntity;
import fi.ringofsnake.entities.Ball;
import fi.ringofsnake.entities.Box;
import fi.sandbox.color.SandboxConstant;

public class Simulation {

	private List<AEntity> entities = new ArrayList<AEntity>();
	
	private World world;

	
	public Simulation() {
		createWorld();
	}
	
	public void createWorld() {		
		Vec2 grafity = new Vec2(0.0f, 10.0f);
		world = new World(grafity, true );
		
		EntityFactory factory = new EntityFactory(world);
		
		entities.add( factory.createStaticBox(0f, 0, 20f, 0.2f));
		entities.add( factory.createStaticBox(-20f, -5f, 0.2f, 4.8f));
		entities.add( factory.createStaticBox(20f, -5, 0.2f, 4.8f));
	}
	
	private void createDynamicItem() {
		
		EntityFactory factory  = new EntityFactory(world);
		AEntity box = factory.createDynamicBox(0, -20, 0.5f, 0.5f);
		box.color = Color.orange;
		
		entities.add(box);
	}

	private void createItemFromMouseClick( float x, float y,
									float dX, float dY, Class<?> classType ) {
		
		
		EntityFactory factory  = new EntityFactory(world);

		AEntity ball = null;
		
		if( classType.equals(Ball.class)) {
			ball= factory.createDynamicBox(x, y, 1f,1f);			
		} else if( classType.equals(Box.class)) {
			ball = factory.createDynamicBall(x, y, 1f);	
		}
		
		ball.body.setLinearVelocity( new Vec2( dX, dY ));
		entities.add(ball);
	}
	
	private void createHeavyItem( float x, float y, 
								 float dX, float dY, 
								 float density )  {
		
		EntityFactory factory  = new EntityFactory(world);
		
		AEntity ball = factory.createDynamicBall(x, y, 5.5f);
		ball.body.getFixtureList().setDensity(density);
		ball.body.setLinearVelocity( new Vec2( dX, dY ));
		
		entities.add(ball);
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
		
		if( input.isKeyPressed(Input.KEY_SPACE)) {
			createDynamicItem();
		}
		
		float x  = (input.getMouseX() - cont.getWidth() / 2) / SandboxConstant.ONE_METER_EQUALS_PX;
		float y = (input.getMouseY() - cont.getHeight() / 2) / SandboxConstant.ONE_METER_EQUALS_PX;
		
		if( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {		
			createItemFromMouseClick( x, y, -5f, 0, Ball.class);
		}

		if( input.isMousePressed(Input.MOUSE_MIDDLE_BUTTON)) {		
			createHeavyItem( x, y, 0, 100f, 100);
		}
		
		if( input.isMouseButtonDown(Input.MOUSE_RIGHT_BUTTON)) {		
			createItemFromMouseClick( x, y,5f, 0, Box.class);
		}
		
		cleanObjectOutSideOfScreen( cont.getWidth(), cont.getHeight() );
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
