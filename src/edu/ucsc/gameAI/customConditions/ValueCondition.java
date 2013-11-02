package edu.ucsc.gameAI.customConditions;

import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class ValueCondition implements ICondition {
	public boolean value;
	public ValueCondition(boolean v){
		value = v;
	}
	
	@Override
	public boolean test(Game game) {
		return value;
	}

	@Override
	public boolean test() {
		return value;
	}

}
