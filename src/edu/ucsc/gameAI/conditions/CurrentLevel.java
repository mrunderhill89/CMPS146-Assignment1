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
public class CurrentLevel implements ICondition {

	Game game;
	int min;
	int max;
	
	public CurrentLevel(Game _game, int _min, int _max) {
		
	}

	@Override
	public boolean test() {
		// TODO Auto-generated method stub
		return false;
	}

}
