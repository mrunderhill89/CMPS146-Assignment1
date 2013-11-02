/**
 * 
 */
package edu.ucsc.gameAI.conditions;

import pacman.game.Constants.GHOST;
import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

/**
 * @author Ed Ramirez
 *
 */
public class IsCloseToPacman implements ICondition {
	
	Game game;
	GHOST ghost;
	double maxDist = 108 / 4; 
	
	public IsCloseToPacman (Game _game, GHOST _ghost) {
		game = _game;
		ghost = _ghost;
	}
	
	public IsCloseToPacman(GHOST _ghost) {
		this(null, _ghost);
	}

	@Override
	public boolean test(Game _game) {
		game = _game;
		return test();
	}

	@Override
	public boolean test() {
		double dist = game.getEuclideanDistance(game.getGhostCurrentNodeIndex(ghost), game.getPacmanCurrentNodeIndex());
		
		return dist < maxDist;
	}

}
