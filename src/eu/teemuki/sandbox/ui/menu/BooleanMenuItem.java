package eu.teemuki.sandbox.ui.menu;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class BooleanMenuItem extends BasicMenuItem implements IMenuItem {	
	
	private boolean value = false;
	
	public BooleanMenuItem(int posX, int posY, String text) {
		super(posX, posY, text);
	}
	
	public void setValue( boolean value ) {
		this.value = value;
	}

	@Override
	public Object getValue() {
		return value;
	}
	
	@Override
	public void update(GameContainer cont, int delta) throws SlickException {
		
		Input input = cont.getInput();	
		if( isActive ) {
			if( input.isKeyPressed(Input.KEY_RIGHT) 
				|| input.isKeyPressed(Input.KEY_ENTER) 
				|| input.isKeyPressed(Input.KEY_LEFT) ) {
				
				//Toggle
				value ^= true;
			}
		}
	}
	
	@Override
	public void render(GameContainer cont, Graphics g) throws SlickException {
		
		String textValue = text;
		
		if( value ) {
			textValue += " Enabled"; 
		} else {
			textValue += " Disabled";
		}		 
		
		if( isActive ) {
			font.drawString(menu.position.x + position.x, 
							menu.position.y + position.y, 
							">> " +  textValue);
		}
		else {
			font.drawString(menu.position.x + position.x, 
							menu.position.y + position.y, 
							textValue  );
		}
	}
}
