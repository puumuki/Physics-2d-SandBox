package eu.teemuki.sandbox.ui.menu;



import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.geom.Vector2f;

import eu.teemuki.sandbox.entities.IGameObject;

public interface IMenuItem extends Comparable<IMenuItem>, IGameObject {
	
	public abstract void setMenu( Menu menu );
	
	public abstract Menu getMenu();
	
	public abstract void setFont(UnicodeFont font);

	public abstract void setText(String text);
	
	public abstract String getText();

	public abstract void setIndex(int index);

	public abstract int getIndex();

	public abstract boolean getActive();

	public abstract void setActive(boolean isActive);

	public abstract Object getValue();
	
	/**
	 * Set item position relative to menu's position.
	 * 
	 * @param pos
	 */
	public abstract void setMenuItemOffset( Vector2f pos );
	
	/**
	 * Return menu item position relative to menu's position.
	 * @return
	 */
	public abstract Vector2f getMenuItemOffset();
}