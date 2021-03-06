/**
 * 
 */
package edu.ucsc.gameAI.conditions;

import edu.ucsc.gameAI.ICondition;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

/**
 * @author Ed Ramirez
 *
 */
public class GhostLastMove implements ICondition {

	Game game;
	GHOST ghost;
	MOVE move;
	
	public GhostLastMove(Game _game, GHOST _ghost, MOVE _move) {
		game = _game;
		ghost = _ghost;
		move = _move;
	}
	
	public GhostLastMove(GHOST _ghost, MOVE _move) {
		this(null, _ghost, _move);
	}
	
	@Override
	public boolean test(Game _game){
		game = _game;
		return test();
	}
	
	@Override
	public boolean test() {
		
		return move == game.getGhostLastMoveMade(ghost);
	}

}
