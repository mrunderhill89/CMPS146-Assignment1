package edu.ucsc.gameAI.customActions;

import pacman.game.Game;
import pacman.game.Constants.MOVE;
import edu.ucsc.gameAI.IAction;

public class GoBackAndForth implements IAction {
	protected Game game;
	protected MOVE move = MOVE.LEFT;
	
	@Override
	public void doAction() {
		move = game.getPacmanLastMoveMade().opposite();
	}

	@Override
	public void doAction(Game _game) {
		game = _game;
		move = game.getPacmanLastMoveMade().opposite();
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
