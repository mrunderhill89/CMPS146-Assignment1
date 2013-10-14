package edu.ucsc.gameAI.hfsm;

import java.util.ArrayList;
import java.util.Collection;

import edu.ucsc.gameAI.IAction;
import edu.ucsc.gameAI.fsm.ITransition;

public class HState implements IHState {
	IAction onEntry;
	IAction onUpdate;
	IAction onExit;
	
	ArrayList<IHTransition> transitions;
	IHFSMBase parent;
	@Override
	public IAction getAction() {
		return onUpdate;
	}

	@Override
	public IAction getEntryAction() {
		return onEntry;
	}

	@Override
	public IAction getExitAction() {
		return onExit;
	}

	@Override
	public Collection<ITransition> getTransitions() {
		return null;
	}

	@Override
	public Collection<IHState> getStates() {
		return null;
	}

	@Override
	public Collection<IHTransition> getHTransitions() {
		return transitions;
	}

	@Override
	public HResult update() {
		return null;
	}

	@Override
	public IHFSMBase getParent() {
		return parent;
	}

	@Override
	public void addTransition(IHTransition t) {
		transitions.add(t);
	}

}
