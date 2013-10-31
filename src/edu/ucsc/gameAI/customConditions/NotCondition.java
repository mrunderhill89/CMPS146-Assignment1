package edu.ucsc.gameAI.customConditions;

import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class NotCondition implements ICondition {

	ICondition a;
	
	public NotCondition(ICondition _a){
		a = _a;
	}
	
	@Override
	public boolean test(Game game) {
		return !a.test(game);
	}

	@Override
	public boolean test() {
		return !a.test();
	}

}
