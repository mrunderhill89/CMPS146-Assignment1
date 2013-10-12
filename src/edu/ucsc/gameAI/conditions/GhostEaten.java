/**
 * 
 */
package edu.ucsc.gameAI.conditions;

import edu.ucsc.gameAI.ICondition;
import pacman.game.Constants.GHOST;
import pacman.game.Game;

/**
 * @author Ed Ramirez
 *
 */
public class GhostEaten implements ICondition {

	Game game;
	GHOST ghost;
	
	public GhostEaten(Game _game, GHOST _ghost) {
		
	}

	@Override
	public boolean test() {
		// TODO Auto-generated method stub
		return false;
	}

}
