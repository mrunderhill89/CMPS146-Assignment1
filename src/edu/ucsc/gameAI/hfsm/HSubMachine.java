package edu.ucsc.gameAI.hfsm;

import java.util.Collection;

import edu.ucsc.gameAI.IAction;
import edu.ucsc.gameAI.fsm.ITransition;

public class HSubMachine implements IHState, IHFSMBase {
	IHState state;
	IHFSMBase machine;
	
	public HSubMachine(){
		state = new HState();
		machine = new HFSMBase();
	}
	
	@Override
	public IAction getAction() {
		return state.getAction();
	}

	@Override
	public IAction getEntryAction() {
		return state.getEntryAction();
	}

	@Override
	public IAction getExitAction() {
		return state.getExitAction();
	}

	@Override
	public Collection<ITransition> getTransitions() {
		return state.getTransitions();
	}

	@Override
	public Collection<IAction> getActions() {
		return machine.getActions();
	}

	@Override
	public Collection<IAction> updateDown(IHState state, int level) {
		return machine.updateDown(state, level);
	}

	@Override
	public Collection<IHState> getStates() {
		//SHOULD BE:return machine.getStates();
		return state.getStates();
	}

	@Override
	public Collection<IHTransition> getHTransitions() {
		return state.getHTransitions();
	}

	@Override
	public HResult update() {
		return machine.update();
	}

	@Override
	public IHFSMBase getParent() {
		return state.getParent();
	}

	@Override
	public void addTransition(IHTransition t) {
		state.addTransition(t);
	}

	@Override
	public IHState getCurrentState() {
		return machine.getCurrentState();
	}

	@Override
	public IHState getInitialState() {
		return machine.getInitialState();
	}
}
