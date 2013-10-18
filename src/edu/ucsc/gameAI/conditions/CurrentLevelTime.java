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
public class CurrentLevelTime implements ICondition {

	Game game;
	int min;
	int max;
	
	public CurrentLevelTime(Game _game, int _min, int _max) {
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
		
		int time = game.getCurrentLevelTime(); 
		
		return time < max && time > min;
	}

}
