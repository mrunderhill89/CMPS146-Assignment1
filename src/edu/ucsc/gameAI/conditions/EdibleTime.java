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
public class EdibleTime implements ICondition {

	Game game;
	GHOST ghost;
	int min;
	int max;
	
	public EdibleTime(Game _game, GHOST _ghost, int _min, int _max) {
		
	}

	@Override
	public boolean test() {
		// TODO Auto-generated method stub
		return false;
	}

}
