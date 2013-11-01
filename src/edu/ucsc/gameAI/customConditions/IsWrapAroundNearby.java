package edu.ucsc.gameAI.customConditions;

import java.util.ArrayList;

import pacman.game.Constants.DM;
import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class IsWrapAroundNearby implements ICondition {
	protected Game game;
	protected double range = 0.1;
	
	public IsWrapAroundNearby(double r){
		range = r;
	}
	
	@Override
	public boolean test(Game _game) {
		game = _game;
		return test();
	}

	@Override
	public boolean test() {
		ArrayList<Integer> wrapAroundPoints = new ArrayList<Integer>();
		for (int i = 0; i < game.getNumberOfNodes(); i++){
			if (tileWrapAround(game, i)){
				wrapAroundPoints.add(i);
			}
		}
		if (wrapAroundPoints.size() > 0){
			double dist;
			int pacmanTile = game.getPacmanCurrentNodeIndex();
			for (Integer wa: wrapAroundPoints){
				dist = game.getDistance(pacmanTile, wa.intValue(), DM.PATH);
				if (dist <= range)
					return true;
			}
		}
		return false;
	}

	public static final int TILE_SIZE = 1;
	public static boolean tileWrapAround(Game game, int tile){
		int tx = game.getNodeXCood(tile);
		int ty = game.getNodeYCood(tile);
		int x,y;
		for (int n: game.getNeighbouringNodes(tile)){
			x = game.getNodeXCood(n);
			y = game.getNodeYCood(n);
			if (Math.abs(tx - x) > TILE_SIZE || Math.abs(ty - y) > TILE_SIZE){
				return true;
			}
		}
		return false;
	}
}
