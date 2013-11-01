package edu.ucsc.gameAI.customConditions;

import pacman.game.Constants.GHOST;
import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class IsGhostInLair implements ICondition {

	@Override
	public boolean test(Game game) {
		for (GHOST g: GHOST.values()){
			if (game.getGhostLairTime(g) > 0){
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean test() {
		// TODO Auto-generated method stub
		return false;
	}

}
