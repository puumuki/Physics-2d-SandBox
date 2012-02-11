package eu.teemuki.sandbox.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * Simple interface with defines a game object. 
 * Game object is class with has render and update method.
 * 
 * @author TeeMuki
 */
public interface IGameObject {
	
	/**
	 * Updates game object
	 *  
	 * @param container
	 * @param delta
	 */
	public void update(GameContainer container, int delta) throws SlickException;
	
	/**
	 * Renders game object
	 * 
	 * @param container
	 * @param g
	 */
	public void render(GameContainer container, Graphics g) throws SlickException;
}
