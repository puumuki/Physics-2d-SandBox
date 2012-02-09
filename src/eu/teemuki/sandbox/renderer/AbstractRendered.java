package eu.teemuki.sandbox.renderer;

import org.jbox2d.dynamics.Body;
import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Shape;

public abstract class AbstractRendered implements IRenderer {

	protected Body body;
	
	protected Shape rendeableShape;
	
	protected Color debugColor = Color.red;
	
	public AbstractRendered( Body body ) {
		this.body = body;
	}
	
	@Override
	public Body getBody() {	
		return body;
	}
	
	public Color getDebugShapeColor() {
		return debugColor;
	}

	public void setDebugShapeColor(Color color) {
		this.debugColor = color;
	}

}
