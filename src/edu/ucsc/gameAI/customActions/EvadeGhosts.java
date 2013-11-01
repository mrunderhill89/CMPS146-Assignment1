package edu.ucsc.gameAI.customActions;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.concurrent.PriorityBlockingQueue;

import pacman.game.Constants.DM;
import pacman.game.Game;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import edu.ucsc.gameAI.IAction;
import edu.ucsc.gameAI.customConditions.IsPacManStuck;

public class EvadeGhosts implements IAction {
	MOVE move = MOVE.NEUTRAL;
	public class MoveRank implements Comparable<MoveRank>
	{
		MOVE move;
		double value = 0.0;
		@Override
		public int compareTo(MoveRank that) {
			if (this.value > that.value){
				return -1;
			} else {
				return 1;
			}
		}
		
	}
	@Override
	public void doAction() {
	}

	@Override
	public void doAction(Game game) {
		/*
		 * Check each of Pac-Man's available moves, and whether or not
		 * they go closer or further away from each of the ghosts. All
		 * ghosts are considered obstacles, but closer ones are prioritized.
		 */
		int pacmanTile = game.getPacmanCurrentNodeIndex();
		Integer nextTile;
		EnumMap<GHOST,Integer> ghostTiles = new EnumMap<GHOST, Integer>(GHOST.class);
		EnumMap<GHOST,Double> ghostDists = new EnumMap<GHOST, Double>(GHOST.class);
		for (GHOST g: GHOST.values()){
			ghostTiles.put(g, game.getGhostCurrentNodeIndex(g));
			ghostDists.put(g, game.getDistance(pacmanTile, ghostTiles.get(g), DM.MANHATTAN));
		}
		PriorityQueue<MoveRank> ranks = new PriorityQueue<MoveRank>();
		/*
		for (MOVE m: MOVE.values()){
			if (m != MOVE.NEUTRAL){
				nextTile = game.getNeighbour(pacmanTile, m);
				if (nextTile != null && nextTile > -1){
					MoveRank rank = new MoveRank();
					rank.move = m;
					for (GHOST g: GHOST.values()){
						rank.value += game.getDistance(ghostTiles.get(g), nextTile, DM.MANHATTAN)/ghostDists.get(g);
					}
					ranks.add(rank);
				} else {
					System.out.println("Direction "+m.name()+" goes nowhere.");
				}
			}
		}*/
		for (int nT : game.getNeighbouringNodes(pacmanTile)){
			MOVE m = game.getMoveToMakeToReachDirectNeighbour(pacmanTile, nT);
			MoveRank rank = new MoveRank();
			rank.move = m;
			for (GHOST g: GHOST.values()){
				rank.value += game.getDistance(ghostTiles.get(g), nT, DM.PATH)/ghostDists.get(g);
			}
			ranks.add(rank);
		}
		if (ranks.size() > 0 ){
			move = ranks.poll().move;
		}
	}

	@Override
	public MOVE getMove() {
		return move;
	}

	@Override
	public MOVE getMove(Game game) {
		return getMove();
	}

}
