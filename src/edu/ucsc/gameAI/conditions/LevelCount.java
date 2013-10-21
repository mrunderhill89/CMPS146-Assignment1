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
public class LevelCount implements ICondition {

	Game game;
	int level;	
	
	public LevelCount(Game _game, int _level) {
		game = _game;
		level = _level;
	}

	public LevelCount(int _level) {
		this(null, _level);
	}

	@Override
	public boolean test(Game _game){
		game = _game;
		return test();
	}
	
	@Override
	public boolean test() {
		if (level == game.getCurrentLevel())
			return true;
		return false;
	}

}
