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
public class NumberOfLivesRemaining implements ICondition {

	Game game;
	int min;
	int max;
	
	public NumberOfLivesRemaining(Game _game, int _min, int _max) {
		
	}
	
	public NumberOfLivesRemaining(int _min) {
		this(null, _min, Integer.MAX_VALUE);
	}

	@Override
	public boolean test(Game _game){
		game = _game;
		return test();
	}
	
	@Override
	public boolean test() {
		int lives = game.getPacmanNumberOfLivesRemaining();
		return lives <= max && lives >= min;
	}

}
