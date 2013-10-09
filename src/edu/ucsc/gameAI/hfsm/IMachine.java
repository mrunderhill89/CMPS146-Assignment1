package edu.ucsc.gameAI.hfsm;

import java.util.Collection;

import edu.ucsc.gameAI.IAction;

public interface IMachine {
	Result update();
	public Collection<IAction> updateDown(IHState state, int level);
}
