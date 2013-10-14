package edu.ucsc.gameAI.hfsm;

import java.util.Collection;
import edu.ucsc.gameAI.IAction;

public interface IHFSMBase {
	Collection<IAction> getActions();
	HResult update();
	Collection<IAction> updateDown(IHState state, int level);
	IHState getCurrentState();
	IHState getInitialState();
}
