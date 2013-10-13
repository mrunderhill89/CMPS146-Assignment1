package edu.ucsc.gameAI.hfsm;

import edu.ucsc.gameAI.IAction;
import edu.ucsc.gameAI.ICondition;
import edu.ucsc.gameAI.fsm.IState;

public class Transition implements IHTransition {
	IHState targetState;
	IAction action;
	ICondition condition;
	int level;
	
	public Transition(IHState ts, IAction a, ICondition c){
		targetState = ts;
		action = a;
		condition = c;
		level = 0;
	}
	
	@Override
	public IState getTargetState() {
		return getTargetHState();
	}

	@Override
	public IHState getTargetHState() {
		return targetState;
	}

	@Override
	public IAction getAction() {
		return action;
	}

	@Override
	public void setCondition(ICondition c) {
		this.condition = c;
	}

	@Override
	public boolean isTriggered() {
		if (condition == null)
			return false;
		return condition.test();
	}

	@Override
	public int getLevel() {
		return level;
	}

}
