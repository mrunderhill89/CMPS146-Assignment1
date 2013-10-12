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
		
	}

	
	@Override
	public boolean test() {
		// TODO Auto-generated method stub
		return false;
	}

}
