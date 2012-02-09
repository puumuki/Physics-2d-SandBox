package eu.teemuki.sandbox.factories;

import org.jbox2d.dynamics.Body;
import org.newdawn.slick.Image;

import eu.teemuki.sandbox.renderer.BallRenderer;
import eu.teemuki.sandbox.renderer.BoxRenderer;
import eu.teemuki.sandbox.renderer.IRenderer;
import eu.teemuki.sandbox.renderer.StaticBoxRenderer;

public class RenderedFactory {

	public StaticBoxRenderer createStaticBoxRendered( Body body ) {
		return new StaticBoxRenderer(body);
	}
	
	public IRenderer createDynamicBoxRendered( Body body, Image image ) {
		return new BoxRenderer(body, image);
	}
	
	public IRenderer createDynamicBallRendered( Body body, Image image ) {		
		return new BallRenderer(body, image);		
	}
	
	
}
