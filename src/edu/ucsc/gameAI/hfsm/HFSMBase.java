package edu.ucsc.gameAI.hfsm;

import java.util.ArrayList;
import java.util.Collection;

import edu.ucsc.gameAI.IAction;
import edu.ucsc.gameAI.fsm.IState;
import edu.ucsc.gameAI.fsm.ITransition;
import edu.ucsc.gameAI.hfsm2.HFSMFull;
import edu.ucsc.gameAI.hfsm2.HTransition;
import edu.ucsc.gameAI.hfsm2.Result;

public class HFSMBase implements IHFSMBase {
	IHState current;
	IHState initial;
	
	@Override
	public ArrayList<IAction> updateDown(IHState state, int level){
		ArrayList<IAction> actions = new ArrayList<IAction>();
		//If we're in a state, exit it.
		if (current != null){
			actions.add(current.getExitAction());
		}
		current = state;
		actions.add(state.getEntryAction());
		return actions;
	}

	@Override
	public Collection<IAction> getActions() {
		return update().actions;
	}

	public IHState getCurrentState(){
		return current;
	}

	public IHState getInitialState(){
		return initial;
	}
	
	@Override
	public HResult update() {
		HResult result = new HResult();
			//If we're starting from scratch, enter the initial state.
			if (current == null){
				current = initial;
				/*We don't have multiple return types, so load the initial
				/*  state's actions into the result and do nothing else.  */
				result.addAction(current.getEntryAction());
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
						System.out.println("Update: Higher level transition");
						result.addAction(current.getExitAction());
						//Reset the state if the transition calls for it.
						if (!result.trans.isRememberState())
							current = null;
						result.level -= 1;
					} else {
						//Both same- and lower-level transitions share some code.
						IHState target = result.trans.getTargetHState();
						if (result.level == 0){
							//Transition is on the same level.
							System.out.println("Update: Same level transition");
							result.addAction(current.getExitAction());
							result.addAction(result.trans.getAction());
							result.addAction(target.getEntryAction());
							
							current = target;
							result.addAction(target.getAction());
						} else {
							//Transition is on lower level.
							System.out.println("Update: Lower level transition");
							IHFSMBase targetMachine = target.getParent();
							result.addAction(result.trans.getAction());
							result.addActions(targetMachine.updateDown(target, -result.level));
						}
						result.trans = null;
					}
				}
			}
			return result;
		}
}
