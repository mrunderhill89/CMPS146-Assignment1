package edu.ucsc.gameAI.hfsm;

import java.util.ArrayList;
import java.util.Collection;

import pacman.game.Game;
import edu.ucsc.gameAI.IAction;

public class HFSM implements IHFSM {
	ArrayList<IHState> states;
	IHState current;
	IHState initial;
	IHState internal;
	String name;
	public HFSM(String n){
		states = new ArrayList<IHState>();
		current = null;
		initial = null;
		internal = new HState(n);
		name = n;
	}
	
	public HFSM() {
		this("HFSM");
	}
	
	public HFSM(String n, HFSM p){
		this(n);
		p.addState(this);
	}

	@Override
	public ArrayList<IAction> updateDown(IHState state, int level, Game game){
		ArrayList<IAction> actions = new ArrayList<IAction>();
		//If we're not at the top level, continue recursing
		if (level > 0){
			actions.addAll(internal.getParent().updateDown(this, level-1, game));
		}
		//If we're in a state, exit it.
		if (current != null){
			actions.add(current.getExitAction());
		}
		current = state;
		actions.add(state.getEntryAction());
		return actions;
	}

	@Override
	public Collection<IAction> getActions(Game game) {
		return update(game).getActions();
	}

	@Override
	public IHState getCurrentState(){
		return current;
	}

	@Override
	public IHState getInitialState(){
		return initial;
	}

	public IHState getInternalState(){
		return internal;
	}
	
	@Override
	public Collection<IHState> getStates(){
		return states;
	}
	
	public IResult update(Game game) {
		IResult result = new HResult();
			//If we're starting from scratch, enter the initial state.
			if (current == null){
				if (initial != null){
					current = initial;
					/*We don't have multiple return types, so load the initial
					/*  state's actions into the result and do nothing else.  */
					result.addAction(current.getEntryAction());
				} else {
					/*Somehow we got a leaf state with no substates, so just tack on our own update function*/
					result.addAction(getAction());
				}
			} else {
				//Try to find a transition in the current state.
				IHTransition triggered = null;
				for (IHTransition trans: current.getTransitions()){
					if (trans.isTriggered(game)){
						triggered = trans;
						break;
					}
				}
				//If we've found one, load it into the result struct.
				if (triggered != null){
					result.setTransition(triggered);
					result.setLevel(triggered.getLevel());
				} else {
					//Otherwise recurse down for a result.
					result = current.update(game);
				}
				//Check if the result contains a transition.
				if (result.getTransition() != null){
					//Act based on its level
					//Note: this is not the same order as in the book.
					if (result.getLevel() > 0){
						//Transition destined for higher level.
						System.out.println("Update: Higher level transition");
						result.addAction(current.getExitAction());
						//Reset the state if the transition calls for it.
						if (!result.getTransition().isRememberState())
							current = null;
						result.setLevel(result.getLevel()-1);
					} else {
						//Both same- and lower-level transitions share some code.
						IHState target = result.getTransition().getTargetState();
						if (result.getLevel() == 0){
							//Transition is on the same level.
							System.out.println("Update: Same level transition");
							result.addAction(current.getExitAction());
							result.addAction(result.getTransition().getAction());
							result.addAction(target.getEntryAction());
							
							current = target;
							result.addAction(target.getAction());
						} else {
							//Transition is on a lower level.
							System.out.println("Update: Lower level transition");
							IHFSM targetMachine = target.getParent();
							result.addAction(result.getTransition().getAction());
							result.addActions(targetMachine.updateDown(target, -result.getLevel(), game));
						}
						result.setTransition(null);
					}
				}
			}
			return result;
		}

	@Override
	public void setStates(Collection<IHState> s) {
		states.clear();
		states.addAll(s);
		current = null;
		if (states.isEmpty()){
			initial = null;
		} else {
			initial = states.get(0);
		}
	}
	
	@Override
	public IAction getAction() {
		return internal.getAction();
	}

	@Override
	public void setAction(IAction action) {
		internal.setAction(action);
	}

	@Override
	public IAction getEntryAction() {
		return internal.getEntryAction();
	}

	@Override
	public void setEntryAction(IAction action) {
		internal.setEntryAction(action);
	}

	@Override
	public IAction getExitAction() {
		return internal.getEntryAction();
	}

	@Override
	public void setExitAction(IAction action) {
		internal.setExitAction(action);
	}

	@Override
	public Collection<IHTransition> getTransitions() {
		return internal.getTransitions();
	}

	@Override
	public void setTransitions(Collection<IHTransition> transitions) {
		internal.setTransitions(transitions);
	}

	@Override
	public void addTransition(IHTransition transition) {
		internal.addTransition(transition);
	}

	@Override
	public void setInitialState(IHState initialState) {
		initial = initialState;
	}

	@Override
	public IHFSM getParent() {
		return internal.getParent();
	}

	@Override
	public void setParent(IHFSM p) {
		internal.setParent(p);
	}
	
	@Override 
	public void addState(IHFSM child) {
		if (states.isEmpty())
			setInitialState(child);
		states.add(child);
	}
	
}
