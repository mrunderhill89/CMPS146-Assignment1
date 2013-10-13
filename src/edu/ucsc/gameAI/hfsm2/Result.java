package edu.ucsc.gameAI.hfsm2;

import java.util.ArrayList;
import java.util.Collection;

import edu.ucsc.gameAI.IAction;

public class Result {
	public ArrayList<IAction> actions;
	public HTransition trans;
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
		if (a != null){
			actions.add(a);
		}
	}
	
	public void addActions(Collection<IAction> as){
		for (IAction a: as){
			addAction(a);
		}
	}

}
