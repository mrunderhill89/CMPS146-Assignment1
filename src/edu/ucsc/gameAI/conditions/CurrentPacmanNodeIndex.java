/**
 * 
 */
package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

/**
 * @author Ed Ramirez
 *
 */
public class CurrentPacmanNodeIndex implements ICondition {

	Game game;
	int index;
	
	public CurrentPacmanNodeIndex(Game _game, int _index) {
		
	}

	@Override
	public boolean test() {
		// TODO Auto-generated method stub
		return false;
	}

}
