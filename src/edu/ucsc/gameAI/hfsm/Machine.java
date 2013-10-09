package edu.ucsc.gameAI.hfsm;

import java.util.ArrayList;
import java.util.Collection;

import edu.ucsc.gameAI.IAction;
import edu.ucsc.gameAI.fsm.IState;
import edu.ucsc.gameAI.fsm.ITransition;

public class Machine implements IHFSMBase, IMachine {

	protected ArrayList<IHState> states; 
	protected IHState initial, current;
	
	public Collection<IHState> getStates(){
		if (current != null){
			return current.getStates();
		}
		return new ArrayList<IHState>();
	}
	
	@Override
	public Collection<IAction> getAction() {
		return new ArrayList<IAction>();
	}

	@Override
	public Result update() {
		Result result = new Result();
		if (current == null){
			current = initial;
			result.addAction(current.getEntryAction());
		} else {
			IHTransition triggered = null;
			for (IHTransition trans: current.getHTransitions()){
				if (trans.isTriggered()){
					triggered = trans;
					break;
				}
			}
			
			if (triggered != null){
				result.trans = triggered;
				result.level = triggered.getLevel();
			} else {
				result = current.update();
			}
			
			if (result.trans != null){
				if (result.level > 0){
					//Transition destined for higher level.
					result.addAction(current.getExitAction());
					current = null;
					result.level -= 1;
				} else {
					IHState target = result.trans.getTargetHState();
					if (result.level == 0){
						//Transition is on the same level.
						result.addAction(current.getExitAction());
						result.addAction(result.trans.getAction());
						result.addAction(target.getEntryAction());
						
						current = target;
						result.addActions(getAction());
					} else {
						//Transition is on lower level.
						ISubMachineState targetMachine = target.getParent();
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
		return null;
	}
}
