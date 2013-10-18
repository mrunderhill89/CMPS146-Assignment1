package edu.ucsc.gameAI.hfsm;

import pacman.game.Game;
import edu.ucsc.gameAI.IAction;
import edu.ucsc.gameAI.ICondition;
import edu.ucsc.gameAI.fsm.IState;
import edu.ucsc.gameAI.hfsm2.HFSMFull;

public class HTransition implements IHTransition {
	IHState target;
	IAction action;
	ICondition condition;
	int level;
	//Should the previous machine remember its current state?
	boolean memory;
	
	public HTransition(IHState from, IHState to, ICondition c, IAction a, boolean m){
		target = to;
		action = a;
		condition = c;
		memory = m;
		level = 0;
		from.addTransition(this);
	}

	public HTransition(IHState from, IHState to, ICondition c, IAction a){
		this(from, to, c, a, false);
	}

	public HTransition(IHState from, IHState to, ICondition c){
		this(from, to, c, null);
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
	public boolean isTriggered(Game game) {
		if (condition == null)
			return false;
		return condition.test(game);
	}
	
	@Override
	public int getLevel(){
		return level;
	}
	
	@Override
	public IHState getTargetState() {
		return target;
	}
		
	@Override
	public boolean isRememberState() {
		return memory;
	}

	@Override
	public void setTargetState(IHState targetState) {
		target = targetState;
	}

	@Override
	public void setAction(IAction a) {
		action = a;
	}

	@Override
	public void setLevel(int l) {
		level = l;
	}
}
