package eu.teemuki.sandbox.factories;

import org.jbox2d.dynamics.Body;
import org.newdawn.slick.Image;

import eu.teemuki.sandbox.entities.AEntity;
import eu.teemuki.sandbox.entities.BasicEntity;
import eu.teemuki.sandbox.renderer.BallRenderer;
import eu.teemuki.sandbox.renderer.IRenderer;

public class RenderedFactory {

	public IRenderer createStaticBoxRendered( Body body ) {
		return null;
	}
	
	public IRenderer createDynamicBoxRendered( Body body ) {
		return null;
	}
	
	public IRenderer createDynamicBallRendered( Body body, Image image ) {		
		return new BallRenderer(body, image);		
	}
	
	
}
