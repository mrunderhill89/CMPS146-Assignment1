package edu.ucsc.gameAI.customActions;

import java.util.ArrayList;
import java.util.concurrent.PriorityBlockingQueue;

import pacman.game.Constants.DM;
import pacman.game.Game;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import edu.ucsc.gameAI.IAction;

public class EvadeGhosts implements IAction {

	protected MOVE move = MOVE.NEUTRAL;
	protected Game game = null;
	protected PriorityBlockingQueue<MoveRank> moveRanks;
	
	public EvadeGhosts(){
		moveRanks = new PriorityBlockingQueue<MoveRank>();
	}
	
	@Override
	public void doAction(Game _game) {
		game = _game;
		doAction();
	}

	@Override
	public void doAction() {
		int pacmanTile = game.getPacmanCurrentNodeIndex();
		int pmX = game.getNodeXCood(pacmanTile);
		int pmY = game.getNodeYCood(pacmanTile);
		ArrayList<Double> angles = new ArrayList<Double>();
		ArrayList<Double> magnitudes = new ArrayList<Double>();
		int ghostTile, gX, gY; double angle, mag;
		MOVE ghostDir;
		for (GHOST ghost: GHOST.values()){
			ghostTile = game.getGhostCurrentNodeIndex(ghost);
			ghostDir = game.getGhostLastMoveMade(ghost);
			gX = game.getNodeXCood(ghostTile);
			gY = game.getNodeYCood(ghostTile);
			angle = Math.atan2(gY-pmY, gX-pmX);
			mag = game.getDistance(ghostTile, pacmanTile, ghostDir, DM.PATH);
			angles.add(angle);
			magnitudes.add(mag*Math.max(mag, 1.0));
		}
		double finalX = 0.0f, finalY = 0.0f;
		for (int i = 0; i < angles.size(); i++){
			finalX += Math.sin(angles.get(i)) / magnitudes.get(i);
			finalY += Math.cos(angles.get(i)) / magnitudes.get(i);
		}
		moveRanks.clear();
		moveRanks.add(new MoveRank(MOVE.UP, -finalY));
		moveRanks.add(new MoveRank(MOVE.DOWN, finalY));
		moveRanks.add(new MoveRank(MOVE.RIGHT, finalX));
		moveRanks.add(new MoveRank(MOVE.LEFT, -finalX));
		moveRanks.add(new MoveRank(MOVE.NEUTRAL, 0.0));
	}

	@Override
	public MOVE getMove(Game _game) {
		game = _game;
		return getMove();
	}

	@Override
	public MOVE getMove() {
		int pacmanTile = game.getPacmanCurrentNodeIndex();
		if (moveRanks.size() > 0){
			move = moveRanks.poll().move;
			while(game.getNeighbour(pacmanTile, move) < 0 && moveRanks.size() > 0){
				move = moveRanks.poll().move;
			}
		}
		return move;
	}

	protected class MoveRank implements Comparable<MoveRank>{
		MOVE move;
		double priority;
		
		public MoveRank(MOVE m, double p){
			move = m;
			priority = p;
		}
		@Override
		public int compareTo(MoveRank that){
			if (this.priority < that.priority)
				return -1;
			return 1;
		}
		
	}
}
