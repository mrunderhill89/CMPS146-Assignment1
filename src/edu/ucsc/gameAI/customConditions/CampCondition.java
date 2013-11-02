package edu.ucsc.gameAI.customConditions;

import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class CampCondition implements ICondition {
	protected double minPillDist;
	protected double minGhostDist;//At this point, just go for the pill.
	protected double maxGhostDist;//At this point, we don't care about the ghosts.
	protected Game game;
	
	public CampCondition(double _minPill, double _maxGhost, double _minGhost){
		minPillDist = _minPill;
		maxGhostDist = _maxGhost;
		minGhostDist = _minGhost;
	}
	
	@Override
	public boolean test(Game _game) {
		game = _game;
		return test();
	}

	@Override
	public boolean test() {
		if (new PowerPillNearby(minPillDist).test(game)){
			if (new GhostNearby(maxGhostDist, minGhostDist).test(game)){
				return true;
			}
			if (!(new GhostNearby(minGhostDist).test(game)) && new IsGhostInLair().test(game)){
				return true;
			}
		}
		return false;
	}

}
