package edu.ucsc.gameAI.customConditions;

import pacman.game.Game;
import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import edu.ucsc.gameAI.ICondition;

public class GhostBetweenTiles implements ICondition {
	protected Game game;
	protected int from,to;
	
	public GhostBetweenTiles(int _a, int _b){
		from = _a;
		to = _b;
	}
	@Override
	public boolean test(Game _game) {
		game = _game;
		return test();
	}

	@Override
	public boolean test() {
		int i = from;
		while (i != to){
			i = game.getClosestNodeIndexFromNodeIndex(i, new int[]{to}, DM.PATH);
			for (GHOST g: GHOST.values()){
				if (game.getGhostCurrentNodeIndex(g) == i)
					return true;
			}
		}
		return false;
	}

}
