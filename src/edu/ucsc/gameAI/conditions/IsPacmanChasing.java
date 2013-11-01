/**
 * 
 */
package edu.ucsc.gameAI.conditions;

import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

/**
 * @author Ed Ramirez
 *
 */
public class IsPacmanChasing implements ICondition {

	Game game;
	GHOST ghost;
	
	public IsPacmanChasing(Game _game, GHOST _ghost) {
		game = _game;
		ghost = _ghost;
	}
	
	public IsPacmanChasing(GHOST _ghost) {
		this(null, _ghost);
	}
	
	@Override
	public boolean test(Game _game) {
		game = _game;
		return test();
	}
	
	@Override
	public boolean test() {
		MOVE ghostLastMove = game.getGhostLastMoveMade(ghost); 
		if ( ghostLastMove != game.getPacmanLastMoveMade())
			return false;
		else {
			int pNode = game.getPacmanCurrentNodeIndex();
			int gNode = game.getGhostCurrentNodeIndex(ghost);
			
			if (ghostLastMove == MOVE.LEFT || ghostLastMove == MOVE.RIGHT) {
				if (game.getNodeYCood(gNode) == game.getNodeYCood(pNode))
					return true;
			}
			else {
				if (game.getNodeXCood(gNode) == game.getNodeXCood(pNode))
					return true;
			}
		}
		
		return false;
	}

}
