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
		
	}

	@Override
	public boolean test() {
		// TODO Auto-generated method stub
		return false;
	}

}
