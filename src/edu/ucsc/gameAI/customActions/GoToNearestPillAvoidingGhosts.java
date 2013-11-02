package edu.ucsc.gameAI.customActions;

import pacman.game.Constants.DM;
import pacman.game.Constants.MOVE;
import pacman.game.Game;
import edu.ucsc.gameAI.IAction;

public class GoToNearestPillAvoidingGhosts implements IAction {
	protected MOVE move;
	
	public GoToNearestPillAvoidingGhosts(){
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
		int closest = getClosestPillAvoidingGhosts(game);
		if (closest >= 0)
			move = game.getNextMoveTowardsTarget(pacman, closest, DM.PATH);
	}
	@Override
	public MOVE getMove(Game game) {
		return move;
	}
	
	public static int getClosestPillAvoidingGhosts(Game game){
		if (game.getNumberOfActivePills() > 0){
			/* Perform an A* search with the heuristic that tiles containing
			 * ghosts are inaccessible.
			 */
		}
		return -1;
	}
}
