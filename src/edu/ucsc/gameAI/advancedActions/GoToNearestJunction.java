/**
 * 
 */
package edu.ucsc.gameAI.advancedActions;

import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;
import edu.ucsc.gameAI.IAction;
import edu.ucsc.gameAI.decisionTrees.binary.IBinaryNode;

/**
 * @author Ed Ramirez
 *
 */
public class GoToNearestJunction implements IAction, IBinaryNode {

	protected MOVE move;
	protected GHOST ghost;
	
	public GoToNearestJunction(GHOST _ghost) {
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
		int[] junctions = game.getJunctionIndices();
		
		int targetNode = game.getClosestNodeIndexFromNodeIndex(gNode, junctions, DM.EUCLID);
		
		move = game.getNextMoveTowardsTarget(gNode, targetNode, DM.PATH);
	}

	@Override
	public MOVE getMove() {
		return move;
	}

	@Override
	public MOVE getMove(Game game) {
		return move;
	}

	@Override
	public IAction makeDecision(Game game) {
		return this;
	}
	
	public IAction makeDecision() {
		return this;
	}

}
