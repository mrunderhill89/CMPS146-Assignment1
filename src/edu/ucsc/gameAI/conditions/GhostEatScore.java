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
public class GhostEatScore implements ICondition {

	Game game;
	int min;
	int max;
	
	public GhostEatScore(Game _game, int _min, int _max) {
		game = _game;
		min = _min;
		max = _max;
	}
	
	public GhostEatScore(int _min, int _max) {
		this(null, _min, _max);
	}

	@Override
	public boolean test(Game _game){
		game = _game;
		return test();
	}
	
	@Override
	public boolean test() {
		int score = game.getGhostCurrentEdibleScore();
		return score < max && score > min;
	}

}
