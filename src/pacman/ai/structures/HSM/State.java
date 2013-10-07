package pacman.ai.structures.HSM;

import java.util.ArrayList;

import pacman.ai.structures.Action;

public class State implements Base{

	@Override
	public Action getAction() {
		return null;
	}

	public Action getEntryAction() {
		return null;
	}

	public Action getExitAction() {
		return null;
	}

	public ArrayList<Transition> getTransitions() {
		return null;
	}
	
	@Override
	public UpdateResult update() {
		return null;
	}

	@Override
	public ArrayList<State> getStates() {
		ArrayList<State> listOfThis = new ArrayList<State>();
		listOfThis.add(this);
		return listOfThis;
	}
	
}
