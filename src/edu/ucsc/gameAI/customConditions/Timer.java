package edu.ucsc.gameAI.customConditions;

import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class Timer implements ICondition {
	protected int activateTime = -1;
	protected int extension;
	
	public Timer(int ext){
		extension = ext;
	}
	
	@Override
	public boolean test(Game game) {
		int time = game.getCurrentLevelTime();
		if (activateTime < 0){
			activateTime = time + extension;
		} else if (time > activateTime){
			activateTime = time + extension;
			return true;
		}
		return false;
	}

	@Override
	public boolean test() {
		// TODO Auto-generated method stub
		return false;
	}

}
