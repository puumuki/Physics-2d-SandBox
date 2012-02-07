package eu.teemuki.sandbox.renderer;

import org.jbox2d.dynamics.Body;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Shape;

public abstract class AbstractRendered implements IRenderer {

	protected Shape rendeableShape;
	
	protected Color debugColor = Color.red;
	
	public Color getDebugShapeColor() {
		return debugColor;
	}

	public void setDebugShapeColor(Color color) {
		this.debugColor = color;
	}

}
