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
public class GoToPacman implements IAction {

	protected MOVE move;
	protected GHOST ghost;
	
	public GoToPacman(GHOST _ghost) {
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
		
		move = game.getNextMoveTowardsTarget(gNode, pNode, game.getGhostLastMoveMade(ghost), DM.PATH);
	}

	@Override
	public MOVE getMove() {
		return move;
	}

	@Override
	public MOVE getMove(Game game) {
		return move;
	}

}
