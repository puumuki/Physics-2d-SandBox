package eu.teemuki.sandbox.utils;

import java.awt.Color;
import java.util.Random;

import org.newdawn.slick.Font;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.GradientEffect;
import org.newdawn.slick.font.effects.OutlineEffect;

import eu.teemuki.sandbox.io.ResourceManager;

public class ResourceDispencer {
	
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
	
	private static Image duck = ResourceManager.fetchImage("DUCK");
	
	public static Image fetchRandomDuck() {
		return duck.copy();
	}
	
	public static UnicodeFont getDefaultFont() throws SlickException {
		java.awt.Font awtFont = new java.awt.Font("Ariel", java.awt.Font.PLAIN, 30);
        UnicodeFont font = new UnicodeFont(awtFont);
        font.addAsciiGlyphs();       
        
        java.awt.Color color = Color.BLACK;
        
        OutlineEffect outlineEffect = new OutlineEffect(5, Color.WHITE);                
        font.getEffects().add(new GradientEffect(color, color, 1f));
        
        font.loadGlyphs();
        
        return font;
	}
}
