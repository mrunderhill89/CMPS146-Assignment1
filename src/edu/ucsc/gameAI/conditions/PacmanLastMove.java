/**
 * 
 */
package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import pacman.game.Constants.MOVE;
import edu.ucsc.gameAI.ICondition;

/**
 * @author Ed Ramirez
 *
 */
public class PacmanLastMove implements ICondition {

	Game game;
	MOVE move;
	
	public PacmanLastMove(Game _game, MOVE _move) {
		game = _game;
		move = _move;
	}
	
	public PacmanLastMove(MOVE _move){
		this(null,_move);
	}
	
	@Override
	public boolean test(Game _game){
		game = _game;
		return test();
	}
		
	@Override
	public boolean test() {
		return game.getPacmanLastMoveMade() == move;
	}

}
