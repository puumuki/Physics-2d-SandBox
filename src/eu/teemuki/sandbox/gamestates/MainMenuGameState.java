package eu.teemuki.sandbox.gamestates;

import java.io.InputStream;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.Log;

import eu.teemuki.sandbox.color.SandboxConstant;
import eu.teemuki.sandbox.io.ResourceManager;
import eu.teemuki.sandbox.ui.menu.BasicMenuItem;
import eu.teemuki.sandbox.ui.menu.Menu;
import eu.teemuki.sandbox.world.AbstractSimulation;
import eu.teemuki.sandbox.world.ConveyorSimulation;
import eu.teemuki.sandbox.world.EmptySimulation;

public class MainMenuGameState extends BasicGameState {

		private int stateID = -1;
		
		private Menu menu;	
		
		private AbstractSimulation currentSimulation;
		
		public MainMenuGameState(int stateID) {
			this.stateID = stateID;
		}
		
		@Override
		public void init(GameContainer container, StateBasedGame game) throws SlickException {				
			loadResourceFile();			
			container.getGraphics().setBackground( SandboxConstant.BACK_GROUND_COLOR );
			currentSimulation = new EmptySimulation();
			
			menu = new Menu( container.getWidth() / 2 -200, 200 );
			menu.add("simulation1", new BasicMenuItem(0, 0, "Simulation - Conveyor Belt"));			
		}

		@Override
		public void render(GameContainer container, 
						  StateBasedGame game, 
						  Graphics g) throws SlickException {
											
			currentSimulation.render(container, g);
			menu.render(container, g);			
		}
		
		@Override

		public void update(GameContainer container, 
						   StateBasedGame game, 
						   int delta)	throws SlickException {
			
			Input input = container.getInput();
			
			if( input.isKeyPressed( Input.KEY_ENTER ) ) {
				if(menu.isActiveIndex("simulation1")) {
					currentSimulation = new ConveyorSimulation();
					menu.setEnabled(false);
				}
			}
			
			if( input.isKeyPressed(Input.KEY_ESCAPE)) {
				menu.setEnabled(true);
			}
			
			menu.update(container, delta);			
			currentSimulation.update(container, delta);			
		}
		
		@Override
		public int getID() {		
			return stateID;
		}		
		
		public void loadResourceFile() throws SlickException {
			
			Log.info("Loading resources.");
			
			String path = "resources/resources.xml";
			
			try {
				InputStream stream = getClass().getClassLoader().getResourceAsStream(path);			
				ResourceManager.getInstance().loadResources(stream); 
			} catch (Exception e) {
				e.printStackTrace();
				throw new SlickException("Sorry, I failed to load the resource file at " + path + ": ");
			}		 
		}
}
