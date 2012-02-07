package eu.teemuki.sandbox.gamestates;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.Log;


import eu.teemuki.sandbox.color.SandboxConstant;
import eu.teemuki.sandbox.entities.AEntity;
import eu.teemuki.sandbox.io.ResourceManager;
import eu.teemuki.sandbox.world.Simulation;

public class MainMenuGameState extends BasicGameState {

		private int stateID = -1;
		
		private List<AEntity> entities = new ArrayList<AEntity>();
		
		private Simulation simulation;
		
		public MainMenuGameState(int stateID) {
			this.stateID = stateID;
		}
		
		@Override
		public void init(GameContainer container, StateBasedGame game) throws SlickException {				
			loadResourceFile();			
			container.getGraphics().setBackground( SandboxConstant.BACK_GROUND_COLOR );
			simulation = new Simulation();
		}

		@Override
		public void render(GameContainer container, 
						  StateBasedGame game, 
						  Graphics g) throws SlickException {
			
			simulation.render(container, g);
		}
		
		@Override

		public void update(GameContainer container, 
						   StateBasedGame game, 
						   int delta)	throws SlickException {
			
			simulation.update(container, delta);
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
