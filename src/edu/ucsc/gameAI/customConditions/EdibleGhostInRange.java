package edu.ucsc.gameAI.customConditions;

import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class EdibleGhostInRange implements ICondition {
	protected Game game = null;
	protected int range, time;
	
	public EdibleGhostInRange(int r, int t){
		range = r;
		time = t;
	}
	
	@Override
	public boolean test(Game _game) {
		game = _game;
		return test();
	}

	@Override
	public boolean test() {
		int pacmanTile = game.getPacmanCurrentNodeIndex();
		int ghostTile; double ghostDist;
		for (GHOST g: GHOST.values()){
			ghostTile = game.getGhostCurrentNodeIndex(g);
			ghostDist = game.getDistance(pacmanTile, ghostTile, DM.PATH);
			if (ghostDist < range){
				if (game.getGhostEdibleTime(g) > time){
						return true;
				}
			}
		}
		return false;
	}

}
