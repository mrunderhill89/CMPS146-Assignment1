package edu.ucsc.gameAI.hfsm;

import java.util.ArrayList;
import java.util.Collection;

import pacman.game.Game;
import edu.ucsc.gameAI.IAction;
import edu.ucsc.gameAI.fsm.ITransition;

public class HState implements IHState {
	IAction onEntry;
	IAction onUpdate;
	IAction onExit;
	String name;
	
	ArrayList<IHTransition> transitions;
	IHFSM parent;
	
	public HState(){
		onEntry = null;
		onUpdate = null;
		onExit = null;
		name = "HFSM";
		transitions = new ArrayList<IHTransition>();
	}
	
	public HState(String n){
		this();
		name = n;
	}
	
	public String getName(){
		return name;
	}
	
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
	public Collection<IHTransition> getTransitions() {
		return transitions;
	}

	@Override
	public Collection<IHState> getStates() {
		ArrayList<IHState> out = new ArrayList<IHState>();
		out.add(this);
		return out;
	}

	@Override
	public IResult update(Game game) {
		IResult out = new HResult();
		out.addAction(getAction());
		return out;
	}

	@Override
	public IHFSM getParent() {
		return parent;
	}

	@Override
	public void addTransition(IHTransition t) {
		transitions.add(t);
	}
	
	@Override
	public Collection<IAction> updateDown(IHState state, int level, Game game) {
		ArrayList<IAction> actions = new ArrayList<IAction>();
		//If we're not at the top level, continue recursing
		if (level > 0){
			actions.addAll(parent.updateDown(this, level-1, game));
		}
		return actions;
	}

	@Override
	public void setStates(Collection<IHState> s) {
		//Doesn't do anything since this isn't a state machine.
	}

	@Override
	public void setAction(IAction a) {
		onUpdate = a;
	}

	@Override
	public void setEntryAction(IAction a) {
		onEntry = a;
	}

	@Override
	public void setExitAction(IAction a) {
		onExit = a;
	}

	@Override
	public void setTransitions(Collection<IHTransition> ts) {
		transitions.clear();
		transitions.addAll(ts);
	}

	@Override
	public void setParent(IHFSM p) {
		parent = p;
	}

}
