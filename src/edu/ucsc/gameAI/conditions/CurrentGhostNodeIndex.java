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
public class CurrentGhostNodeIndex implements ICondition {

	Game game;
	GHOST ghost;
	int index;
	
	public CurrentGhostNodeIndex(Game _game, GHOST _ghost, int _index) {
		
	}

	@Override
	public boolean test() {
		// TODO Auto-generated method stub
		return false;
	}

}
