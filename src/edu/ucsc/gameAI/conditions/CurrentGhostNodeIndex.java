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
public class CurrentGhostNodeIndex implements ICondition {

	Game game;
	GHOST ghost;
	int index;
	
	public CurrentGhostNodeIndex(Game _game, GHOST _ghost, int _index) {
		game = _game;
		ghost = _ghost;
		index = _index;
	}

	public CurrentGhostNodeIndex(GHOST _ghost, int _index){
		this(null, _ghost, _index);
	}
	
	@Override
	public boolean test(Game _game){
		game = _game;
		return test();
	}
	
	@Override
	public boolean test() {
		return index == game.getGhostCurrentNodeIndex(ghost);
	}

}
