/**
 * 
 */
package edu.ucsc.gameAI.advancedActions;

import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;
import pacman.game.internal.Maze;
import edu.ucsc.gameAI.IAction;

/**
 * @author Ed Ramirez
 *
 */
public class GoToNearestNode implements IAction {

	protected MOVE move;
	protected GHOST ghost;
	
	public GoToNearestNode(GHOST _ghost) {
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
		int[] neighbors = game.getNeighbouringNodes(gNode, game.getGhostLastMoveMade(ghost));
		
		int targetNode = game.getClosestNodeIndexFromNodeIndex(gNode, neighbors, DM.PATH);
		move = game.getNextMoveTowardsTarget(gNode, targetNode, DM.PATH);
	}

	/* (non-Javadoc)
	 * @see edu.ucsc.gameAI.IAction#getMove()
	 */
	@Override
	public MOVE getMove() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.ucsc.gameAI.IAction#getMove(pacman.game.Game)
	 */
	@Override
	public MOVE getMove(Game game) {
		// TODO Auto-generated method stub
		return null;
	}

}
