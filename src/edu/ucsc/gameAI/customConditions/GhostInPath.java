package edu.ucsc.gameAI.customConditions;

import pacman.game.Constants.DM;
import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class GhostInPath implements ICondition {
	protected Game game = null;
	protected int bufferTime;
	protected boolean checkBehind;
	
	public GhostInPath(int bt, boolean bh){
		bufferTime = bt;
		checkBehind = bh;
	}
	
	@Override
	public boolean test(Game _game) {
		game = _game;
		return test();
	}

	@Override
	public boolean test() {
		/*
		 * Is there a ghost between Pac-Man and the nearest
		 * junction?
		 */
		int pacmanTile = game.getPacmanCurrentNodeIndex();
		int[] junctions = game.getJunctionIndices();
		int closestJunction = -1;
		double cdist = -1.0, dist;
		for (int j = 0; j < junctions.length; j++){
			if (checkBehind)
				dist = game.getDistance(pacmanTile, junctions[j], DM.PATH);
			else
				dist = game.getDistance(pacmanTile, junctions[j], game.getPacmanLastMoveMade(), DM.PATH);			
			if (closestJunction < 0 || dist < cdist){
				cdist = dist;
				closestJunction = j;
			}
		}
		if (closestJunction < 0)
			return false;
		return new GhostBetweenTiles(pacmanTile, closestJunction).test(game);
	}

}
