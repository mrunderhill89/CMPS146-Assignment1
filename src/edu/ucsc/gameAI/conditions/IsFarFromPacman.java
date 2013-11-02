/**
 * 
 */
package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import pacman.game.Constants.GHOST;
import edu.ucsc.gameAI.ICondition;

/**
 * @author Ed Ramirez
 *
 */
public class IsFarFromPacman implements ICondition {

	Game game;
	GHOST ghost;
	double minDist = 120 / 3; 
	
	public IsFarFromPacman (Game _game, GHOST _ghost) {
		game = _game;
		ghost = _ghost;
	}
	
	public IsFarFromPacman (GHOST _ghost) {
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
		
		return dist > minDist;
	}

}
