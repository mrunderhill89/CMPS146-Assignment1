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
		
	}
	
	@Override
	public boolean test() {
		// TODO Auto-generated method stub
		return false;
	}

}
