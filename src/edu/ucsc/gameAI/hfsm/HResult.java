package edu.ucsc.gameAI.hfsm;

import java.util.ArrayList;
import java.util.Collection;

import edu.ucsc.gameAI.IAction;
import edu.ucsc.gameAI.hfsm.IHTransition;

public class HResult implements IResult {
	public ArrayList<IAction> actions;
	public IHTransition trans;
	public int level;
	
	public HResult(){
		actions = new ArrayList<IAction>();
		initialize();
	}
	
	public void initialize(){
		actions.clear();
		trans = null;
	}
	
	@Override
	public Collection<IAction> getActions() {
		return actions;
	}

	@Override
	public void addAction(IAction a){
		if (a != null)
			actions.add(a);
	}
	
	@Override
	public void addActions(Collection<IAction> a){
		actions.addAll(a);
	}

	@Override
	public void setActions(Collection<IAction> as) {
		actions.clear();
		actions.addAll(as);
	}

	@Override
	public IHTransition getTransition() {
		return trans;
	}

	@Override
	public void setTransition(IHTransition transition) {
		trans = transition;
	}

	@Override
	public int getLevel() {
		return level;
	}

	@Override
	public void setLevel(int l) {
		level = l;
	}

}
