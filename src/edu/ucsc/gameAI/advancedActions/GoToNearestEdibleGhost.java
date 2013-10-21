package edu.ucsc.gameAI.advancedActions;

import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;
import edu.ucsc.gameAI.IAction;

public class GoToNearestEdibleGhost implements IAction {
	protected MOVE move;
	
	public GoToNearestEdibleGhost(){
		move = MOVE.NEUTRAL;
	}
	@Override
	public void doAction() {
		
	}

	@Override
	public MOVE getMove() {
		return move;
	}

	@Override
	public void doAction(Game game) {
		int numPills = game.getNumberOfActivePowerPills();
		if (numPills > 0){
			int pacman = game.getPacmanCurrentNodeIndex();
			int closest = 0;
			double dist;
			double cdist = -1;
			for (GHOST ghost: GHOST.values()){
				int index = game.getGhostCurrentNodeIndex(ghost);
				dist = game.getDistance(pacman, index, DM.PATH);
				if (game.isGhostEdible(ghost) && (cdist < 0 || cdist > dist)){
					closest = index;
					cdist = dist;
				}
			}
			if (cdist > 0)
				move = game.getNextMoveTowardsTarget(pacman, closest, DM.PATH);
		}
	}
	@Override
	public MOVE getMove(Game game) {
		return move;
	}

}
