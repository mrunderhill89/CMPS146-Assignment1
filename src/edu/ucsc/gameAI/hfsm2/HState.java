package edu.ucsc.gameAI.hfsm2;

import java.util.ArrayList;
import java.util.HashMap;

import edu.ucsc.gameAI.IAction;

public class HState {
	//State attributes
	protected IAction onEntry;
	protected IAction onUpdate;
	protected IAction onExit;
	
	//Machine attributes
	protected HashMap<String, HState> subStates;
	protected ArrayList<HTransition> transitions;
	protected HState current;
	protected HState initial;
	protected HState parent;
	protected int level;
	
	//Creates a new root state.
	public HState(){
		subStates = new HashMap<String, HState>();
		transitions = new ArrayList<HTransition>();
		onEntry = null;
		onUpdate = null;
		onExit = null;
		current = null;
		initial = null;
		parent = null;
		level = 0;
	}
	
	//Creates a sub-machine using another HState as a parent
	public HState(String name, HState p, boolean makeInitial){
		this();
		//Set parent and level
		parent = p;
		level = p.level + 1;
		//Add this state to the parent's children.
		p.subStates.put(name, this);
		//If specified or there are no other states, make this one the initial state. 
		if (makeInitial || p.subStates.isEmpty()){
			p.initial = this;
		}
	}
	
	//Simplification of the above constructor.
	public HState(String name, HState p){
		this(name, p, false);
	}
	
	//Returns a child state by name
	public HState getChild(String name){
		return subStates.get(name);
	}
	
	public Result update(){
		Result result = new Result();
		//If we're starting from scratch, enter the initial state.
		if (current == null){
			if (initial != null){
				current = initial;
				/*We don't have multiple return types, so load the initial
				/*  state's actions into the result and do nothing else.  */
				result.addAction(current.onEntry);
			} else { 
				//This is a leaf state and has no children, so just return its own update action.
				result.addAction(onUpdate);
			}
		} else {
			//Try to find a transition in the current state.
			HTransition triggered = null;
			for (HTransition trans: current.transitions){
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
					result.addAction(current.onExit);
					current = null;
					result.level -= 1;
				} else {
					//Both same- and lower-level transitions share some code.
					HState target = result.trans.getTarget();
					if (result.level == 0){
						//Transition is on the same level.
						result.addAction(current.onExit);
						result.addAction(result.trans.getAction());
						result.addAction(target.onExit);
						
						current = target;
						//result.addActions(getActions());
					} else {
						//Transition is on lower level.
						HState targetMachine = target.parent;
						result.addAction(result.trans.getAction());
						result.addActions(targetMachine.updateDown(target, -result.level));
					}
					result.trans = null;
				}
			}
		}
		return result;
	}
	
	public ArrayList<IAction> getActions(){
		return update().actions;
	}
	
	public ArrayList<IAction> updateDown(HState state, int level){
		ArrayList<IAction> actions = new ArrayList<IAction>();
		//If we're at the top level, continue recursing
		if (level > 0){
			actions.addAll(parent.updateDown(this, level-1));
		}
		//If we're in a state, exit it.
		if (current != null){
			actions.add(current.onExit);
		}
		current = state;
		actions.add(state.onEntry);
		return actions;
	}
	
	public int getLevel(){
		return level;
	}
}
