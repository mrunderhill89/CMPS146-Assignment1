package edu.ucsc.gameAI.customActions;

import pacman.game.Game;
import pacman.game.Constants.MOVE;
import edu.ucsc.gameAI.IAction;

public class PacManGoStraight implements IAction {

	@Override
	public void doAction() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doAction(Game game) {
		// TODO Auto-generated method stub

	}

	@Override
	public MOVE getMove() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MOVE getMove(Game game) {
		return game.getPacmanLastMoveMade();
	}

}
