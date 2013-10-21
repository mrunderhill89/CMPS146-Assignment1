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
public class LairTime implements ICondition {

	Game game;
	GHOST ghost;
	int min;
	int max;

	public LairTime(Game _game, GHOST _ghost, int _min, int _max) {
		game = _game;
		ghost = _ghost;
		min = _min;
		max = _max;
	}
	
	public LairTime(GHOST _ghost, int _min, int _max){
		this(null, _ghost, _min, _max);
	}
	
	@Override
	public boolean test(Game _game){
		game = _game;
		return test();
	}
	
	@Override
	public boolean test() {
		int time = game.getGhostLairTime(ghost);
		return time < max && time > min;
	}

}
