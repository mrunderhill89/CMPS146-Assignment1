package edu.ucsc.gameAI.customConditions;

import pacman.game.Game;
import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import edu.ucsc.gameAI.ICondition;

public class GhostChasingPacMan implements ICondition {
	protected int maxDist = 100;
	protected Game game = null;
	public GhostChasingPacMan(int d){
		maxDist = d;
	}
	@Override
	public boolean test(Game _game) {
		game = _game;
		return test();
	}

	@Override
	public boolean test() {
		int pacmanTile = game.getPacmanCurrentNodeIndex();
		int ghostTile;
		MOVE ghostDir, idealDir;
		for (GHOST g: GHOST.values()){
			ghostTile = game.getGhostCurrentNodeIndex(g);
			ghostDir = game.getGhostLastMoveMade(g);
			if (game.getDistance(ghostTile, pacmanTile, ghostDir, DM.PATH) < maxDist){
				idealDir = game.getNextMoveTowardsTarget(ghostTile, pacmanTile, DM.PATH);
				if (ghostDir == idealDir)
					return true;
			}
		}
		return false;
	}

}
