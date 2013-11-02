package edu.ucsc.gameAI.customConditions;

import pacman.game.Constants.DM;
import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class PowerPillNearby implements ICondition {
	protected double range;
	protected Game game;
	public PowerPillNearby(double r){
		range = r;
	}
	
	@Override
	public boolean test(Game _game) {
		game = _game;
		return test();
	}

	@Override
	public boolean test() {
		int pacmanTile = game.getPacmanCurrentNodeIndex();
		for(int powerTile: game.getActivePowerPillsIndices()){
			if (game.getDistance(pacmanTile, powerTile, DM.PATH) < range)
				return true;
		}
		return false;
	}

}
