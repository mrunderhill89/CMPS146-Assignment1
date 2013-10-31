package edu.ucsc.gameAI.customConditions;

import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;
import edu.ucsc.gameAI.customActions.GoToNearestEdibleGhost;

public class StopRampage implements ICondition {
	Game game = null;
	
	@Override
	public boolean test(Game _game) {
		game = _game;
		return test();
	}

	@Override
	public boolean test() {
		/* 
		 * Return true if the closest inedible ghost
		 * is closer than the closest edible ghost. 
		 */
		int closestEdible = GoToNearestEdibleGhost.getClosestEdibleGhost(game);
		int closest = GoToNearestEdibleGhost.getClosestGhost(game);
		return closest != closestEdible;
	}

}
