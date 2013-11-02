package edu.ucsc.gameAI.customConditions;

import pacman.game.Game;
import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import edu.ucsc.gameAI.ICondition;

public class GhostNearby implements ICondition {
	protected Game game = null;
	protected double min, max;
	protected int time;
	
	public GhostNearby(double _max, double _min, int t){
		min = _min;
		max = _max;
		time = t;
	}
	
	public GhostNearby(double _max, double _min){
		this(_max, _min, 0);
	}
	
	public GhostNearby(double _max){
		this(_max, 0.0);
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
			ghostDist = game.getDistance(pacmanTile, ghostTile, DM.EUCLID);
			if (ghostDist < max && ghostDist > min){
				//Only worry about inedible ghosts
				if (game.getGhostEdibleTime(g) <= time){
						return true;
				}
			}
		}
		return false;
	}

}
