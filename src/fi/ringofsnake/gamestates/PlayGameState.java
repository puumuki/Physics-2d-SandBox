
package fi.ringofsnake.gamestates;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class PlayGameState extends BasicGameState {

	private int stateID = -1;

	
	public PlayGameState(int stateID) {
		this.stateID = stateID;		
	}

	public int getID() {
		return stateID;
	}
	
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {


	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)	throws SlickException {
	}
}