package eu.teemuki.sandbox.factories;

import org.jbox2d.dynamics.Body;

import eu.teemuki.sandbox.entities.AEntity;
import eu.teemuki.sandbox.entities.BasicEntity;
import eu.teemuki.sandbox.renderer.IRenderer;

public class EntityFactory {

	public BasicEntity createBasicEntity( Body body, IRenderer renderer ) {
		return new BasicEntity(body, renderer);
	}
}
