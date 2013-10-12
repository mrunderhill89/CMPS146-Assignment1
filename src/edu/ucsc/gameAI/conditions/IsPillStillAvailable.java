/**
 * 
 */
package edu.ucsc.gameAI.conditions;

import edu.ucsc.gameAI.ICondition;
import pacman.game.Game;

/**
 * @author Ed Ramirez
 *
 */
public class IsPillStillAvailable implements ICondition {

	Game game;
	int pillIndex;
	
	public IsPillStillAvailable(Game _game, int _pillIndex) {
		
	}

	@Override
	public boolean test() {
		// TODO Auto-generated method stub
		return false;
	}

}
