package pacman.ai.structures.HSM;

import java.util.ArrayList;
import pacman.ai.structures.Action;

public class Machine extends State {	
	Machine(){
		states = new ArrayList<State>();
		currentState = null;
	}
	
	public void addState(State nState){
		
	}
	
	public void setInitialState(State nState){
		if (nState == null || states.contains(nState)){
			currentState = nState;
		}
	}
	
	public ArrayList<State> getStates(){
		return states;
	}
	
	@Override
	public UpdateResult update() {
		UpdateResult result = new UpdateResult();
		return result;
	}
	
	public ArrayList<Action> updateDown(State state, int level){
		return null;
	}
	
	protected ArrayList<State> states;
	protected State currentState;
}
