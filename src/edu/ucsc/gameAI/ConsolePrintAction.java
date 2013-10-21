package edu.ucsc.gameAI;

import pacman.game.Constants.MOVE;
import pacman.game.Game;

/**
 * 
 * @author Kevin Cameron
 *
 */
public class ConsolePrintAction implements IAction {
	protected String message;
	protected IAction action;
	
	public ConsolePrintAction(String m, IAction a){
		message = m;
		action = a;
	}
	
	public ConsolePrintAction(String m){
		this(m, null);
	}
	
	@Override
	public void doAction() {
		doAction(null);
	}
	
	@Override
	public void doAction(Game game) {
		System.out.println(message);
		if (action != null){
			if (game != null)
				action.doAction(game);
			else
				action.doAction();
		}
	}
	
	public MOVE getMove(Game game){
		if (action != null){
			if (game != null){
				return action.getMove(game);
			} else {
				return action.getMove();
			}
		}
		return MOVE.NEUTRAL;
	}

	public MOVE getMove(){
		return getMove(null);
	}
}
