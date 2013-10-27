package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class GhostBetweenPill implements ICondition {
	Game game;
	
	public GhostBetweenPill(){
		game = null;
	}
	
	@Override
	public boolean test(Game _game) {
		game = _game;
		return test();
	}

	@Override
	public boolean test() {
		return false;
	}

}
