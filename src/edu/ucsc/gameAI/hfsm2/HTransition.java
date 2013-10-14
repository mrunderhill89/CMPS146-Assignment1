package edu.ucsc.gameAI.hfsm2;

import edu.ucsc.gameAI.IAction;
import edu.ucsc.gameAI.ICondition;

public class HTransition {
	HFSMFull target;
	IAction action;
	ICondition condition;
	int level;
	//Should the previous machine remember its current state?
	boolean memory;
	
	public HTransition(HFSMFull from, HFSMFull to, ICondition c, IAction a, boolean m){
		target = to;
		action = a;
		condition = c;
		memory = m;
		level = from.getLevel() - to.getLevel();
		from.transitions.add(this);
	}
	public HTransition(HFSMFull from, HFSMFull to, ICondition c, IAction a){
		this(from, to, c, a, false);
	}

	public HTransition(HFSMFull from, HFSMFull to, ICondition c){
		this(from, to, c, null);
	}

	public HFSMFull getTarget() {
		return target;
	}

	public IAction getAction() {
		return action;
	}

	public void setCondition(ICondition c) {
		this.condition = c;
	}

	public boolean isTriggered() {
		if (condition == null)
			return false;
		return condition.test();
	}
	
	public int getLevel(){
		return level;
	}
}
