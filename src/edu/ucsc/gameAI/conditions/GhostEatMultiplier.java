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
public class GhostEatMultiplier implements ICondition {

	Game game;
	int min;
	int max;
	
	public GhostEatMultiplier(Game _game, int _min, int _max) {
		
	}
	
	@Override
	public boolean test() {
		// TODO Auto-generated method stub
		return false;
	}

}
