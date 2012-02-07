package fi.sandbox.color;

import java.math.BigInteger;
import java.util.Random;

import org.newdawn.slick.Color;

public class ColorUtils {

	private static final Random random = new Random();
	
	public static final Color[] BASIC_COLORS;
	
	static {
		String[] colorString = {"BABA21", 	"C8B400",	"DFA800", 	"DB9900", 	"FFB428", 	"FF9331", "FF800D" };

		
		BASIC_COLORS = createColors(colorString);
	}
	
	private static Color[] createColors( String ... colors ) {
	
		Color[] colorSet = new Color[ colors.length ];
		
		for( int i=0; i< colors.length; i++ ) {			
			BigInteger color = new BigInteger(colors[i], 16);				
			Color col = new Color( color.intValue() );						
			colorSet[i] = col;
		}
		
		return colorSet;
	}
	
	public static Color randomColor() {
		int index = random.nextInt(BASIC_COLORS.length);
		System.out.println( "I" + index );
		return BASIC_COLORS[index];
	}
}
