package edu.ucsc.gameAI.hfsm;

import java.util.ArrayList;
import java.util.Collection;

import edu.ucsc.gameAI.IAction;
import edu.ucsc.gameAI.fsm.ITransition;

public class SubMachine implements IMachine, IHState {
	IHState state;
	IMachine machine;
	@Override
	public IAction getAction() {
		// TODO Auto-generated method stub
		return state.getAction();
	}

	@Override
	public IAction getEntryAction() {
		// TODO Auto-generated method stub
		return state.getEntryAction();
	}

	@Override
	public IAction getExitAction() {
		// TODO Auto-generated method stub
		return state.getExitAction();
	}

	@Override
	public Collection<ITransition> getTransitions() {
		// TODO Auto-generated method stub
		return state.getTransitions();
	}

	@Override
	public Collection<IHState> getStates() {
		return state.getStates();
	}

	@Override
	public Collection<IHTransition> getHTransitions() {
		// TODO Auto-generated method stub
		return state.getHTransitions();
	}

	@Override
	public ISubMachineState getParent() {
		// TODO Auto-generated method stub
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
