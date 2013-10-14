package edu.ucsc.gameAI;

import pacman.game.Constants.MOVE;

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
		System.out.println(message);
		if (action != null){
			action.doAction();
		}
	}
	
	public MOVE getMove(){
		if (action != null)
			return action.getMove();
		return MOVE.NEUTRAL;
	}
}
