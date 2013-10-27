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
public class GhostInRegion implements ICondition {
	Game game;
	int xMin;
	int yMin;
	int xMax;
	int yMax;
	public GhostInRegion(Game _game, int x1, int y1, int x2, int y2) {
		game = _game;
		xMin = Math.min(x1, x2);
		xMax = Math.max(x1, x2);
		yMin = Math.min(y1, y2);
		yMax = Math.max(y1, y2);
	}
	
	public GhostInRegion(int x1, int y1, int x2, int y2) {
		this(null, x1, y1, x2, y2);
	}
	
	@Override
	public boolean test(Game _game){
		game = _game;
		return test();
	}
	
	@Override
	public boolean test() {
		int x,y;
		for (GHOST g : GHOST.values()){
			int p = game.getGhostCurrentNodeIndex(g);
			x = game.getNodeXCood(p);
			y = game.getNodeYCood(p);
			if (x >= xMin && x <= xMax){
				if (y >= yMin && y <= yMax){
					return true;
				}
			}
		}		
		return false;
	}

}
