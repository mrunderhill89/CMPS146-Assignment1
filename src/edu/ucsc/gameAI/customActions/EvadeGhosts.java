package edu.ucsc.gameAI.customActions;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

import pacman.game.Constants.DM;
import pacman.game.Game;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import edu.ucsc.gameAI.IAction;
import edu.ucsc.gameAI.customConditions.IsPacManStuck;

public class EvadeGhosts implements IAction {
	MOVE move = MOVE.NEUTRAL;
	Random r = new Random();
	protected Game game = null;
	//If there's a ghost this close, ignore the others.
	double minRange = 5.0;
	//If the ghost is out of commission for more than this, ignore it.
	double maxTime = 10.0;
	
	public class MoveRank implements Comparable<MoveRank>
	{
		MOVE move;
		double value = 0.0;
		@Override
		public int compareTo(MoveRank that) {
			if (this.value > that.value){
				return -1;
			} else if (this.value < that.value){
				return 1;
			}
			return 0;
		}
		
	}
	@Override
	public void doAction() {
	}

	@Override
	public void doAction(Game _game) {
		game = _game;
	}

	@Override
	public MOVE getMove() {
		/*
		 * Check each of Pac-Man's available moves, and whether or not
		 * they go closer or further away from each of the ghosts. All
		 * ghosts are considered obstacles, but closer ones are prioritized.
		 */
		int pacmanTile = game.getPacmanCurrentNodeIndex();
		EnumMap<GHOST,Integer> ghostTiles = new EnumMap<GHOST, Integer>(GHOST.class);
		EnumMap<GHOST,Double> ghostDists = new EnumMap<GHOST, Double>(GHOST.class);
		EnumMap<GHOST,Integer> ghostThreatTime = new EnumMap<GHOST, Integer>(GHOST.class);
		GHOST closest = null; double close = -1.0;
		double dist; int ghostTile;
		for (GHOST g: GHOST.values()){
			ghostTile = game.getGhostCurrentNodeIndex(g);
			dist = game.getDistance(pacmanTile, ghostTile, DM.PATH);
     		ghostTiles.put(g, ghostTile);
			ghostDists.put(g, dist);
			ghostThreatTime.put(g, Math.max(game.getGhostEdibleTime(g), game.getGhostLairTime(g)));
			if (ghostThreatTime.get(g) < maxTime){
				if (closest == null || dist < close){
					closest = g;
					close = dist;
				}
			}
		}
		if (closest != null && ghostDists.get(closest) < minRange){
			move = game.getNextMoveAwayFromTarget(ghostTiles.get(closest), pacmanTile, DM.PATH);
			return move;
		}
		PriorityQueue<MoveRank> ranks = new PriorityQueue<MoveRank>();
		for (int nT : game.getNeighbouringNodes(pacmanTile)){
			MOVE m = game.getMoveToMakeToReachDirectNeighbour(pacmanTile, nT);
			if (m != MOVE.NEUTRAL){
				MoveRank rank = new MoveRank();
				rank.move = m;
				for (GHOST g: GHOST.values()){
					if (ghostThreatTime.get(g) < maxTime){
						rank.value += game.getDistance(ghostTiles.get(g), nT, DM.PATH)/(Math.pow(ghostDists.get(g), 2.0));
					}
				}
				ranks.add(rank);
			}
		}
		if (ranks.size() > 0 ){
			move = ranks.poll().move;
		}
		return move;
	}

	@Override
	public MOVE getMove(Game _game) {
		game = _game;
		return getMove();
	}

}
