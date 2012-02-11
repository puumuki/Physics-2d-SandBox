package eu.teemuki.sandbox.world;

import java.util.ArrayList;
import java.util.List;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

import eu.teemuki.sandbox.entities.AEntity;
import eu.teemuki.sandbox.entities.IGameObject;
import eu.teemuki.sandbox.factories.EntityFactory;
import eu.teemuki.sandbox.factories.PhysicsFactory;
import eu.teemuki.sandbox.factories.RenderedFactory;

public abstract class AbstractSimulation implements IGameObject {

	protected List<AEntity> entities = new ArrayList<AEntity>();
	
	protected World world;
	
	protected PhysicsFactory physicsFactory;
	
	protected RenderedFactory renderedFactory;
	
	protected EntityFactory entityFactory;
	
	public AbstractSimulation() {
		initWorldAndFactories();
	}
	
	private void initWorldAndFactories() {		
		Vec2 grafity = new Vec2(0.0f, 10.0f);
		world = new World(grafity, true );		
		physicsFactory = new PhysicsFactory(world);
		renderedFactory = new RenderedFactory();
		entityFactory = new EntityFactory();
	}

	public void finilizeSimulation() {
		for(AEntity entity : entities) {
			world.destroyBody(entity.body);
		}
	}
}