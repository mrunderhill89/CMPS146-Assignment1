package edu.ucsc.gameAI.hfsm;

import java.util.ArrayList;
import java.util.Collection;

import edu.ucsc.gameAI.IAction;
import edu.ucsc.gameAI.fsm.ITransition;

public class Result {
	public ArrayList<IAction> actions;
	public ITransition trans;
	public int level;
	
	public Result(){
		actions = new ArrayList<IAction>();
		initialize();
	}
	
	public void initialize(){
		actions.clear();
		trans = null;
	}
	
	public Collection<IAction> getActions() {
		return actions;
	}

	public void addAction(IAction a){
		actions.add(a);
	}
	
	public void addActions(Collection<IAction> a){
		actions.addAll(a);
	}

}
