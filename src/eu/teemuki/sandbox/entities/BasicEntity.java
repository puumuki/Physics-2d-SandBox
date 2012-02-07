package eu.teemuki.sandbox.entities;

import org.jbox2d.dynamics.Body;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import eu.teemuki.sandbox.renderer.IRenderer;

public class BasicEntity extends AEntity {

	public BasicEntity(Body body, IRenderer renderer) {
		super(body, renderer);
	}

	@Override
	public void render(GameContainer cont, Graphics grap) throws SlickException {
		rendered.render(cont, grap);
	}

	@Override
	public void update(GameContainer cont, int delta) throws SlickException {
		rendered.update(cont, delta);
	}

}
