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
public class IsPowerPillStillAvailable implements ICondition {

	int pillIndex;
	Game game;
	public IsPowerPillStillAvailable(Game _game, int _pillIndex) {
		game = _game;
		pillIndex = _pillIndex;
	}
	
	public IsPowerPillStillAvailable(int _pillIndex) {
		this(null, _pillIndex);
	}
	@Override
	public boolean test(Game _game){
		game = _game;
		return test();
	}
	
	@Override
	public boolean test() {
		return game.isPowerPillStillAvailable(pillIndex);
	}

}
