package eu.teemuki.sandbox.entities;

import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.Fixture;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import eu.teemuki.sandbox.renderer.IRenderer;

/**
 * Base class for all the objects of the game. 
 * So where is the encapsulation? 
 * Class fields are public for performance reasons.
 */
public abstract class AEntity implements Comparable<AEntity>, IGameObject {
					
	public Fixture fixture;
		
	/**
	 * Entity shape
	 */
	public Shape shape;
		
	/**
	 * Entity body
	 */
	public Body body;
				
	/**
	 * Layer number means a drawing order.
	 * Entities with smallest numbers are drawn first.
	 */
	public int layer;	
		
	public IRenderer rendered;
		
	public AEntity( Body body, IRenderer renderer  ) {
		this.body = body;		
		this.fixture = body.getFixtureList();
		this.shape = this.fixture.getShape();
		this.rendered = renderer;
	}
	
	/**
	 * The entity is rendered to the screen when this method is called.
	 * 
	 * @param cont Don't read any input or update physics in this method.
	 * @param grap Use this Graphics object to draw the entity so the drawing order is right.
	 * @throws SlickException if something goes wrong
	 */
	public abstract void render(GameContainer cont, 					    
					   			Graphics grap) 
					    		throws SlickException;
	
	/**
	 * In this method an entity's physics are updated, input from user
	 * are handled and so on. 
	 * 
	 * @param cont the container of the game, can give us the input of the player for example
	 * @param delta 
	 * @throws SlickException if something goes wrong
	 */
	public abstract void update(GameContainer cont, 
								int delta) throws SlickException ;
		
	
	@Override
	public int compareTo(AEntity o) {
		
		int compare = 0;
		
		if( o.layer > this.layer )
			compare = 1;
		else if( o.layer < this.layer )
			compare = -1;
		
		return compare;
	}
}
