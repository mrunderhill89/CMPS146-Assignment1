package edu.ucsc.gameAI.hfsm;

import java.util.Collection;

import edu.ucsc.gameAI.IAction;

public interface IHMachine extends IHFSMBase{
	public Collection<IAction> updateDown(IHState state, int level);
	Result update();
}
