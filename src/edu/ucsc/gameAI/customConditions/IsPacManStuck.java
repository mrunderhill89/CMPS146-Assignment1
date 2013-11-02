package edu.ucsc.gameAI.customConditions;

import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class IsPacManStuck implements ICondition {
	protected static int lastTile = -1;
	@Override
	public boolean test(Game game) {
		int pacmanTile = game.getPacmanCurrentNodeIndex();
		if (pacmanTile == lastTile){
			System.out.println("I'm stuck!");
			return true;
		}
		lastTile = pacmanTile;
		return false;
	}

	@Override
	public boolean test() {
		// TODO Auto-generated method stub
		return false;
	}

}
