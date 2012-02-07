package eu.teemuki.sandbox.color;

import java.util.Random;

import org.newdawn.slick.Image;

import eu.teemuki.sandbox.io.ResourceManager;

public class ImageDispencer {
	
	private static Random random = new Random();
	
	private static Image[] ballImages = { ResourceManager.fetchImage("BALL_1"),  
										  ResourceManager.fetchImage("BALL_2"), 
										  ResourceManager.fetchImage("BALL_3"),
										  ResourceManager.fetchImage("BALL_4"),
										  ResourceManager.fetchImage("BALL_5"),
										  ResourceManager.fetchImage("BALL_6")};
	
	public static Image randomBallImage() {
		return ballImages[random.nextInt(ballImages.length)].copy();
	}
	
	private static Image[] boxImages = { ResourceManager.fetchImage("BOX_1"),
										 ResourceManager.fetchImage("BOX_2"),
										 ResourceManager.fetchImage("BOX_3")};
	
	public static Image randomBoxImage() {

		int index = random.nextInt(boxImages.length);
		System.out.println(boxImages.length + " : " + index);
		return boxImages[index].copy();
	}
}
