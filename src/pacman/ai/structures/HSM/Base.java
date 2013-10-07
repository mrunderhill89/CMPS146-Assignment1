package pacman.ai.structures.HSM;

import java.util.ArrayList;

import pacman.ai.structures.Action;

public interface Base {
	public class UpdateResult{
		ArrayList<Action> actions = new ArrayList<Action>();
		Transition trans = null;
		int level = 0;
	}
	
	Action getAction();
	UpdateResult update();
	/*
	{
		UpdateResult result = new UpdateResult();
		result.actions.add(getAction());
		return result;
	}
	*/
	ArrayList<State> getStates();
}
