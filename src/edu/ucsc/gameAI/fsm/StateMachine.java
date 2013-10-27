package edu.ucsc.gameAI.fsm;

import java.util.ArrayList;
import java.util.Collection;

import pacman.game.Game;
import edu.ucsc.gameAI.IAction;

public class StateMachine implements IStateMachine {
	ArrayList<IState> states;
	IState current;
	IState initial;
	
	public StateMachine(){
		states = new ArrayList<IState>();
		current = null;
		initial = null;		
	}
	
	@Override
	public Collection<IAction> update(Game game) {
		ArrayList<IAction> out = new ArrayList<IAction>();
		
		ITransition triggered = null;
		for (ITransition t: current.getTransitions()){
			if (t.isTriggered(game)){
				triggered = t;
				break;
			}
		}
		
		if (triggered != null){
			IState target = triggered.getTargetState();
			if(current.getExitAction() != null)
				out.add(current.getExitAction());
			if(target.getAction() != null)
				out.add(target.getAction());
			if(target.getEntryAction() != null)
				out.add(target.getEntryAction());
			current = target;
		} else {
			if(current.getAction() != null)
				out.add(current.getAction());
		}
		return out;
	}

	@Override
	public IState getCurrentState() {
		return current;
	}

	@Override
	public void setCurrentState(IState state) {
		current = state;
	}
}
