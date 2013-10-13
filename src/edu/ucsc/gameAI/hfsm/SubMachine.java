package edu.ucsc.gameAI.hfsm;

import java.util.ArrayList;
import java.util.Collection;

import edu.ucsc.gameAI.IAction;
import edu.ucsc.gameAI.fsm.ITransition;

public class SubMachine implements IHMachine, IHState {
	IHState state;
	IHMachine machine;
	
	@Override
	public Collection<IAction> getActions() {
		return machine.getActions();
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
	public Collection<IHState> getStates() {
		return state.getStates();
	}

	@Override
	public Collection<IHTransition> getHTransitions() {
		return state.getHTransitions();
	}

	@Override
	public IHMachine getParent() {
		return state.getParent();
	}

	@Override
	public Result update() {
		return machine.update();
	}

	@Override
	public Collection<IAction> updateDown(IHState state, int level) {
		Collection<IAction> actions = new ArrayList<IAction>();
		//If we're at the top level, continue recursing
		if (level > 0){
			actions = state.getParent().updateDown(this, level-1);
		}
		actions.addAll(machine.updateDown(state, level)); 
		return actions;
	}
}
