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
public class CurrentPacmanNodeIndex implements ICondition {

	Game game;
	int index;
	
	public CurrentPacmanNodeIndex(Game _game, int _index) {
		game = _game;
		index = _index;
	}
	
	@Override
	public boolean test(Game _game){
		game = _game;
		return test();
	}
	
	@Override
	public boolean test() {
		
		return index == game.getPacmanCurrentNodeIndex();
	}

}
