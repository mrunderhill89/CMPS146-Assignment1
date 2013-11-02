package edu.ucsc.gameAI.advancedActions;

import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;
import edu.ucsc.gameAI.IAction;
import edu.ucsc.gameAI.decisionTrees.binary.IBinaryNode;

public class GoToNearestPowerPillGhost implements IAction, IBinaryNode {

	protected MOVE move;
	protected GHOST ghost;
	
	public GoToNearestPowerPillGhost(GHOST _ghost) {
		ghost = _ghost;
		move = MOVE.NEUTRAL;
	}
	
	@Override
	public IAction makeDecision(Game game) {
		return this;
	}
	
	public IAction makeDecision() {
		return this;
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
		MOVE gMove = game.getGhostLastMoveMade(ghost);
		
		int[] powerPills = game.getPowerPillIndices();
		if (powerPills.length == 0) {
			move = MOVE.NEUTRAL;
			return;
		}
		
		int ppNode = game.getClosestNodeIndexFromNodeIndex(gNode, powerPills, DM.EUCLID);
		move = game.getNextMoveAwayFromTarget(gNode, ppNode, gMove, DM.EUCLID);
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
