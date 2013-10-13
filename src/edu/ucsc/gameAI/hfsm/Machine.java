package edu.ucsc.gameAI.hfsm;

import java.util.ArrayList;
import java.util.Collection;

import edu.ucsc.gameAI.IAction;
import edu.ucsc.gameAI.fsm.IState;
import edu.ucsc.gameAI.fsm.ITransition;

public class Machine implements IHFSMBase, IHMachine {

	protected ArrayList<IHState> states; 
	protected IHState initial, current;
	
	//Base constructor
	public Machine(){
		states = new ArrayList<IHState>();
		initial = null;
		current = null;
	}
	
	//Gets the current state stack.
	public Collection<IHState> getStates(){
		if (current != null){
			return current.getStates();
		}
		return new ArrayList<IHState>();
	}
	
	//Returns an empty list, as per the HSMBase example in the book.
	@Override
	public Collection<IAction> getActions() {
		return new ArrayList<IAction>();
	}

	//Recursively updates the machine
	@Override
	public Result update() {
		Result result = new Result();
		//If we're starting from scratch, enter the initial state.
		if (current == null){
			if (initial != null){
				current = initial;
				/*We don't have multiple return types, so load the initial
				/*  state's actions into the result and do nothing else.  */
				result.addAction(current.getEntryAction());
			}
		} else {
			//Try to find a transition in the current state.
			IHTransition triggered = null;
			for (IHTransition trans: current.getHTransitions()){
				if (trans.isTriggered()){
					triggered = trans;
					break;
				}
			}
			//If we've found one, load it into the result struct.
			if (triggered != null){
				result.trans = triggered;
				result.level = triggered.getLevel();
			} else {
				//Otherwise recurse down for a result.
				result = current.update();
			}
			//Check if the result contains a transition.
			if (result.trans != null){
				//Act based on its level
				//Note: this is not the same order as in the book.
				if (result.level > 0){
					//Transition destined for higher level.
					result.addAction(current.getExitAction());
					current = null;
					result.level -= 1;
				} else {
					//Both same- and lower-level transitions share some code.
					IHState target = result.trans.getTargetHState();
					if (result.level == 0){
						//Transition is on the same level.
						result.addAction(current.getExitAction());
						result.addAction(result.trans.getAction());
						result.addAction(target.getEntryAction());
						
						current = target;
						result.addActions(getActions());
					} else {
						//Transition is on lower level.
						IHMachine targetMachine = target.getParent();
						result.addAction(result.trans.getAction());
						result.addActions(targetMachine.updateDown(target, -result.level));
					}
					result.trans = null;
				}
			}
		}
		return result;
	}

	@Override
	public Collection<IAction> updateDown(IHState state, int level){
		ArrayList<IAction> actions = new ArrayList<IAction>();
		//If we're at the top level, continue recursing
		/*
		if (level > 0){
			actions = parent.updateDown(this, level-1);
		}
		*/
		//If we're in a state, exit it.
		if (current != null){
			actions.add(current.getExitAction());
		}
		current = state;
		actions.add(state.getEntryAction());
		return actions;
	}
		
	@Override
	public String toString(){
		String out = "Machine:";
		//Print states
		out += "\n States:";
		for (IHState state: states){
			out += "\n *";
			if (state == initial){
				out += "i>";
			}
			if (state == current){
				out += "c>";
			}
			out += state.toString();
		}		
		return out;
	}
}
