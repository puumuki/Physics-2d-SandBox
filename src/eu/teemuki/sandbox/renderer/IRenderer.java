package eu.teemuki.sandbox.renderer;

import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.dynamics.Body;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import eu.teemuki.sandbox.entities.IGameObject;

public interface IRenderer extends IGameObject{
	
	Body getBody();
	
	Shape getBodyShape();
}
