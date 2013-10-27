package edu.ucsc.gameAI.fsm;

import pacman.game.Game;
import edu.ucsc.gameAI.IAction;
import edu.ucsc.gameAI.ICondition;

public class Transition implements ITransition {
	protected IState target;
	protected IAction action;
	protected ICondition condition;
	
	public Transition(){
		target = null;
		action = null;
		condition = null;
	}
	
	@Override
	public IState getTargetState() {
		return target;
	}

	@Override
	public void setTargetState(IState targetState) {
		this.target = targetState;
	}

	@Override
	public IAction getAction() {
		return action;
	}

	@Override
	public void setAction(IAction action) {
		this.action = action;
	}

	@Override
	public void setCondition(ICondition condition) {
		this.condition = condition;
	}

	@Override
	public boolean isTriggered(Game game) {
		if (condition != null)
			return condition.test(game);
		return false;
	}

}
