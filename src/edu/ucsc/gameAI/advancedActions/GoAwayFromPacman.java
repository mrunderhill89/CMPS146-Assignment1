/**
 * 
 */
package edu.ucsc.gameAI.advancedActions;

import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;
import edu.ucsc.gameAI.IAction;

/**
 * @author Ed Ramirez
 *
 */
public class GoAwayFromPacman implements IAction {

	protected MOVE move;
	protected GHOST ghost;
	
	public GoAwayFromPacman(GHOST _ghost) {
		ghost = _ghost;
		move = MOVE.NEUTRAL;
	}
	
	@Override
	public void doAction() {

	}

	public void doAction(Game game, GHOST _ghost) {
		ghost = _ghost;
		doAction(game);
	}
	
	@Override
	public void doAction(Game game) {
		int gNode = game.getGhostCurrentNodeIndex(ghost);
		int pNode = game.getPacmanCurrentNodeIndex();
		
		move = game.getNextMoveAwayFromTarget(gNode, pNode, game.getGhostLastMoveMade(ghost), DM.PATH);

	}

	/* (non-Javadoc)
	 * @see edu.ucsc.gameAI.IAction#getMove()
	 */
	@Override
	public MOVE getMove() {
		return move;
	}

	/* (non-Javadoc)
	 * @see edu.ucsc.gameAI.IAction#getMove(pacman.game.Game)
	 */
	@Override
	public MOVE getMove(Game game) {
		return move;
	}

}
