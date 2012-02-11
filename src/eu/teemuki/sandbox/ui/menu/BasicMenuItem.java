package eu.teemuki.sandbox.ui.menu;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.geom.Vector2f;

public class BasicMenuItem implements IMenuItem {

	/**
	 * Reference to menu that item is belonging.
	 */
	protected Menu menu;
	
	/**
	 * Index in menu
	 */
	protected int index;
	
	/**
	 * Menu item text
	 */
	protected String text;
	
	/**
	 * Flag indicates that menu item is currently selected
	 */
	protected boolean isActive;
	
	/**
	 * Font used to render menu item text
	 */
	protected UnicodeFont font;

	/**
	 * This absolute position that is relative to game screen;
	 */
	public Vector2f position;	
	
	/**
	 * Basic value
	 */
	private Object value = false;
	
	public BasicMenuItem( int posX, int posY, String text ) {
		this.text = text;
		this.position = new Vector2f(posX, posY);
	}
	
	@Override
	public void update(GameContainer cont, int delta) throws SlickException {

	}

	@Override
	public void render(GameContainer cont, Graphics g) throws SlickException {			
		if( isActive ) {
			font.drawString(menu.position.x  + position.x, 
							menu.position.y + position.y, ">> " +  text);
		}
		else {
			font.drawString(menu.position.x + position.x, 
							menu.position.y + position.y, text  );
		}
	}

	@Override
	public void setFont(UnicodeFont font) {
		this.font = font;
	}

	@Override
	public void setText(String text) {
		this.text = text;
	}

	@Override
	public void setIndex(int index) {
		this.index = index;
	}

	@Override
	public int getIndex() {
		return index;
	}

	@Override
	public boolean getActive() {
		return isActive;
	}

	@Override
	public String getText() {
		return text;
	}
	
	@Override
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public int compareTo(IMenuItem item) {
		
		if(item.getIndex() > index ) {
			return 1;
		}
		else if( item.getIndex() < index ) {
			return -1;
		}
		
		return 0;
	}

	@Override
	public void setMenuItemOffset(Vector2f pos) {
		this.position = pos;
	}
	
	@Override	
	public Vector2f getMenuItemOffset() {	
		return position;
	}
	
	@Override
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	
	@Override
	public Menu getMenu() {
		return menu;
	}
}
