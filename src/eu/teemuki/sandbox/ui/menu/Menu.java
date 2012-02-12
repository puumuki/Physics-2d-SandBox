package eu.teemuki.sandbox.ui.menu;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.GradientEffect;
import org.newdawn.slick.font.effects.OutlineEffect;
import org.newdawn.slick.geom.Vector2f;

import eu.teemuki.sandbox.entities.IGameObject;
import eu.teemuki.sandbox.io.ResourceManager;
import eu.teemuki.sandbox.utils.ResourceDispencer;

/**
 * Simple UI-component to creating cool looking menus. 
 */
public class Menu implements IGameObject, Iterable<IMenuItem> {
	
	/**
	 * Menu position relative to screen
	 */
	public Vector2f position;

	/**
	 * Currently selected index
	 */
	private int activeIndex = 0;
		
	private List<IMenuItem> menuitems = new ArrayList<IMenuItem>();
	
	/**
	 * Font used in text based menu items
	 */
	private UnicodeFont font;
	
	/**
	 * Sound that is played when selection changes
	 */
	private Sound sound;
	
	/**
	 * Menu backgound image
	 */
	private Image background;
	
	/**
	 * Indicates is menu shown.
	 */
	private boolean enabled = true;	
	
	/**
	 * Map contains menu item name and index number
	 */
	private Map<String, Integer> menuItemIndexes = new HashMap<String, Integer>();		
	
	public Menu( Sound clickSound ) throws SlickException {			
		java.awt.Color topColor = new java.awt.Color( 0xeeee00 );
        java.awt.Color bottomColor = new java.awt.Color( 0xbbff00 );
		this.sound = clickSound;
		this.font = ResourceDispencer.getDefaultFont();
        initDefaultFont(topColor, bottomColor);        
	}
		
	public Menu( Vector2f position, Sound sound ) throws SlickException {
		this( sound );
		this.position = position.copy();
	}
	
	/**
	 * Constructor for creating menu with an arial font that is filled with a gradient color.
	 * @param fontTopColor
	 * @param fontBottomColor
	 * @throws SlickException
	 */
	public Menu( Color fontTopColor, Color fontBottomColor ) throws SlickException {
		initDefaultFont(fontTopColor, fontBottomColor);
		sound = ResourceManager.getInstance().getSound("MENU_CLICK_SOUND");
		enabled = false;
	}

	private void initDefaultFont( Color topColor, Color bottomColor ) throws SlickException {

	}
	
	public void setBackground(Image image) {
		this.background = image;
	}
	
	public Image getBackground() {
		return background;
	}
	
	public void setSound(Sound sound) {
		this.sound = sound;
	}
	
	/**
	 * Insert a new menu item to end off the menu
	 * 
	 * @param menuItemName name can be used to fetch menu item index later
	 * @param item
	 */
	public void add( String menuItemName, IMenuItem item ) {
		this.menuitems.add(item);
		item.setIndex( menuitems.size() -1  );
		item.setFont(font);
		item.setMenu(this);
		menuItemIndexes.put(menuItemName, item.getIndex());		
	}	
	
	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		for( IMenuItem item : menuitems ) {
			item.update(container, delta);
		}
		
		Input input = container.getInput();
		
		menuitems.get(activeIndex).setActive(false);
		
		if( input.isKeyPressed(Input.KEY_DOWN)){
			if( activeIndex < menuitems.size() - 1 ) {
				activeIndex++;
			} else {
				activeIndex = 0;
			}
			
			sound.play();
		}
		
		if( input.isKeyPressed(Input.KEY_UP)) {
			if( activeIndex > 0) {
				activeIndex--;
			} else {
				activeIndex = menuitems.size() - 1;
			}
			
			sound.play();
		}
		
		menuitems.get(activeIndex).setActive(true);		
	}	
	
	/**
	 * @return count of menu items
	 */
	public int size() {
		return menuitems.size();
	}
	
	/**
	 * @return witch index is selected
	 */
	public int getActiveIndex() {
		return activeIndex;
	}
	
	/**
	 * Return is the index with given name active one. 
	 * @param menuItemName
	 * @return true if menu item is selected otherwise false. 
	 * 		  If a null reference or a non existing menu item name is passes return false.
	 */
	public boolean isActiveIndex( String menuItemName ) {
				
		if( menuItemIndexes.containsKey(menuItemName) ) {
			return menuItemIndexes.get(menuItemName) == getActiveIndex();
		}
		
		return false;
	}
	
	/**
	 * Return a menu item or null if a menu item was not found with given name.
	 * 
	 * @param menuItemName
	 * @return a menu item or null if a menu item was not found with given name.
	 */
	public IMenuItem getMenuItem( String menuItemName ) {
		
		Integer index = menuItemIndexes.get(menuItemName);
		
		if( index != null ) {
			for( IMenuItem item : menuitems ) {
				if( item.getIndex() == index ) {
					return item;
				}
			}
		}
		
		return null;
	}
	
	public IMenuItem getMenuItem( int index ) {
		return menuitems.get(index);
	}
	
	@Override
	public void render(GameContainer cont, Graphics g) throws SlickException {
		if( enabled ) {
			for( IMenuItem item : menuitems ) {
				item.render(cont, g);
			}
		}
	}

	@Override
	public Iterator<IMenuItem> iterator() {
		return menuitems.iterator();
	}
	
	public void setFont(UnicodeFont font) {
		this.font = font;
		
		for( IMenuItem item : menuitems ) {
			item.setFont(font);
		}
	}
	
	public UnicodeFont getFont() {
		return font;
	}
	
	public void setPosition(Vector2f position) {
		this.position = position;
	}
	
	public Vector2f getPosition() {
		return position;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
}
