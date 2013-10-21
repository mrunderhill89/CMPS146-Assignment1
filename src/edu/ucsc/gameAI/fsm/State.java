package edu.ucsc.gameAI.fsm;

import java.util.ArrayList;
import java.util.Collection;

import edu.ucsc.gameAI.IAction;

public class State implements IState {
	IAction onUpdate;
	IAction onEntry;
	IAction onExit;
	
	ArrayList<ITransition> transitions;
	
	public State(){
		onEntry = null;
		onUpdate = null;
		onExit = null;
		transitions = new ArrayList<ITransition>();
	}
	
	@Override
	public IAction getAction() {
		return onUpdate;
	}

	@Override
	public void setAction(IAction action) {
		onUpdate = action;
	}

	@Override
	public IAction getEntryAction() {
		return onEntry;
	}

	@Override
	public void setEntryAction(IAction action) {
		onEntry = action;
	}

	@Override
	public IAction getExitAction() {
		return onExit;
	}

	@Override
	public void setExitAction(IAction action) {
		onExit = action;
	}

	@Override
	public Collection<ITransition> getTransitions() {
		return transitions;
	}

	@Override
	public void setTransitions(Collection<ITransition> trans) {
		transitions.clear();
		transitions.addAll(trans);
	}

}
