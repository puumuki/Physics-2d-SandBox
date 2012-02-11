package eu.teemuki.sandbox.ui.menu;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.geom.Vector2f;

public class ImageMenuItem implements IMenuItem {

	private Menu menu;
	
	private int index;
	
	public Image menuImage;
	
	public float scale = 1;
		
	public static final float MAX_SIZE = 0.1f;
	
	private static final Color filterColor = new Color(1f,1f,1f,0.5f);
	
	public boolean isActive = false;
		
	private Vector2f position;
	
	public ImageMenuItem( Vector2f position, Image menuItemImage ) {
		this.position = position;		
		this.menuImage = menuItemImage;
	}

	@Override
	public void update(GameContainer cont, int delta) throws SlickException {
		if( isActive && scale < (1 + MAX_SIZE) ) {
			scale = (float) (delta * 0.001 + scale);
		}		

		else if( !isActive && scale > (1 - MAX_SIZE)) {
			scale = (float) (scale - delta * 0.001 );
		}
	}

	@Override
	public void render(GameContainer cont, Graphics g) throws SlickException {
		if( isActive )
			menuImage.draw(position.x - ((menuImage.getWidth() / 2 * scale) - (menuImage.getWidth() / 2)), position.y, scale);
		else {
			menuImage.draw(position.x - ((menuImage.getWidth() / 2 * scale) - (menuImage.getWidth() / 2)), position.y, scale, filterColor);
		}
	}

	@Override
	public void setFont(UnicodeFont font) {	
		//Not really need to be implements
	}

	@Override
	public void setText(String text) {
		//Not really need to be implements
	}

	@Override
	public String getText() {
		return "";
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
	public void setActive(boolean isActive) {	
		this.isActive = isActive;		
	}

	@Override
	public Object getValue() {
		return false;
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
