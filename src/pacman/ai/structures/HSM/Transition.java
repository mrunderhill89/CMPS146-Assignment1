package pacman.ai.structures.HSM;

import pacman.ai.structures.Action;

public class Transition {
	public int getLevel(){
		return 0;
	}
	public boolean isTriggered(){
		return false;
	}
	public State getTargetState(){
		return null;
	}
	public Action getAction(){
		return null;
	}
}
