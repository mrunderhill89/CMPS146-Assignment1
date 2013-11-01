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
	double minRange = 1.0;
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
		for (GHOST g: GHOST.values()){
     		ghostTiles.put(g, game.getGhostCurrentNodeIndex(g));
			ghostDists.put(g, game.getDistance(pacmanTile, ghostTiles.get(g), DM.PATH));
			//If there's a ghost this close, don't bother averaging.
			if (ghostDists.get(g) < minRange){
				move = game.getNextMoveAwayFromTarget(pacmanTile, ghostTiles.get(g), DM.PATH);
				return move;
			}
		}
		PriorityQueue<MoveRank> ranks = new PriorityQueue<MoveRank>();
		for (int nT : game.getNeighbouringNodes(pacmanTile)){
			MOVE m = game.getMoveToMakeToReachDirectNeighbour(pacmanTile, nT);
			if (m != MOVE.NEUTRAL){
				MoveRank rank = new MoveRank();
				rank.move = m;
				for (GHOST g: GHOST.values()){
					rank.value += game.getDistance(ghostTiles.get(g), nT, DM.PATH)/ghostDists.get(g);
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
