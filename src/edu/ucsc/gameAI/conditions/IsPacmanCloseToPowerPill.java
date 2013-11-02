/**
 * 
 */
package edu.ucsc.gameAI.conditions;

import pacman.game.Constants.DM;
import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

/**
 * @author Ed Ramirez
 *
 */
public class IsPacmanCloseToPowerPill implements ICondition {

	Game game;
	double maxDist = 108 / 4;
	
	public IsPacmanCloseToPowerPill (Game _game) {
		game = _game;
	}

	public IsPacmanCloseToPowerPill() {
		this(null);
	}
	
	@Override
	public boolean test(Game _game) {
		game = _game;
		return test();
	}

	@Override
	public boolean test() {
		int pNode = game.getPacmanCurrentNodeIndex();
		
		int[] powerPills = game.getActivePowerPillsIndices();
		
		if (powerPills.length == 0)
			return false;
		
		int closestPP = game.getClosestNodeIndexFromNodeIndex(pNode, powerPills, DM.EUCLID);
		
		double dist = game.getEuclideanDistance(pNode, closestPP);
		
		return dist < maxDist;
	}

}
