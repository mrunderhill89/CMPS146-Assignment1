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
	
	public IsPillStillAvailable(int _pillIndex) {
		this(null, _pillIndex);
	}

	@Override
	public boolean test(Game _game){
		game = _game;
		return test();
	}
	
	@Override
	public boolean test() {
		return game.isPillStillAvailable(pillIndex);
	}

}
