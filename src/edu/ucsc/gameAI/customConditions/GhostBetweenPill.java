package edu.ucsc.gameAI.customConditions;

import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;
import edu.ucsc.gameAI.conditions.GhostInRegion;
import edu.ucsc.gameAI.customActions.GoToNearestPill;

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

	/*
	 * Returns true if there is a ghost in the region defined by
	 * Pac-Man's position and the nearest pill.
	 */
	@Override
	public boolean test() {
		int pacmanTile = game.getPacmanCurrentNodeIndex();
		int pillTile = GoToNearestPill.getClosestPill(game);
		int pmX = game.getNodeXCood(pacmanTile);
		int pmY = game.getNodeYCood(pacmanTile);
		int pX = game.getNodeXCood(pillTile);
		int pY = game.getNodeYCood(pillTile);
		return new GhostInRegion(pmX,pmY,pX,pY).test(game);
	}

}
