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
public class GhostEaten implements ICondition {

	Game game;
	GHOST ghost;
	
	public GhostEaten(Game _game, GHOST _ghost) {
		game = _game;
		ghost = _ghost;
	}
	
	public GhostEaten(GHOST _ghost) {
		this(null, _ghost);
	}

	@Override
	public boolean test(Game _game){
		game = _game;
		return test();
	}
	
	@Override
	public boolean test() {
		return game.wasGhostEaten(ghost);
	}

}
