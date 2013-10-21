package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class AndCondition implements ICondition {

	ICondition a,b;
	
	public AndCondition(ICondition _a, ICondition _b){
		a = _a;
		b = _b;
	}
	
	@Override
	public boolean test(Game game) {
		return a.test(game) && b.test(game);
	}

	@Override
	public boolean test() {
		return a.test() && b.test();
	}

}
