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
public class TotalTime implements ICondition {

	Game game;
	int min;
	int max;
	
	public TotalTime(Game _game, int _min, int _max) {
		game = _game;
		min = _min;
		max = _max;
	}
	
	@Override
	public boolean test(Game _game){
		game = _game;
		return test();
	}
	
	@Override
	public boolean test() {
		// TODO Auto-generated method stub
		return false;
	}

}
