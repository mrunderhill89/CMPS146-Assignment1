package edu.ucsc.gameAI.customActions;

import pacman.game.Constants.DM;
import pacman.game.Constants.MOVE;
import pacman.game.Game;
import edu.ucsc.gameAI.IAction;

public class GoToNearestPill implements IAction {
	protected MOVE move;
	
	public GoToNearestPill(){
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
		int pacman = game.getPacmanCurrentNodeIndex();
		int closest = getClosestPill(game);
		if (closest >= 0)
			move = game.getNextMoveTowardsTarget(pacman, closest, DM.PATH);
	}
	@Override
	public MOVE getMove(Game game) {
		return move;
	}
	
	public static int getClosestPill(Game game){
		if (game.getNumberOfActivePills() > 0){
			int pacman = game.getPacmanCurrentNodeIndex();
			int[] indices = game.getActivePillsIndices();
			int closest = 0;
			double dist;
			double cdist = -1;
			for (int index: indices){
				dist = game.getDistance(pacman, index, DM.PATH);
				if (cdist < 0 || cdist > dist){
					closest = index;
					cdist = dist;
				}
			}
			return closest;
		}
		return -1;
	}
}
