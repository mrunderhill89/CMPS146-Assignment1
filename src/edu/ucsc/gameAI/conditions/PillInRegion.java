/**
 * 
 */
package edu.ucsc.gameAI.conditions;

import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

/**
 * @author Ed Ramirez
 *
 */
public class PillInRegion implements ICondition {

	Game game;
	int xMin;
	int yMin;
	int xMax;
	int yMax;
	public PillInRegion(Game _game, int x1, int y1, int x2, int y2) {
		game = _game;
		xMin = Math.min(x1, x2);
		xMax = Math.max(x1, x2);
		yMin = Math.min(y1, y2);
		yMax = Math.max(y1, y2);
	}
	
	public PillInRegion(int x1, int y1, int x2, int y2) {
		this(null,x1,y1,x2,y2);
	}
	
	@Override
	public boolean test(Game _game){
		game = _game;
		return test();
	}
		
	@Override
	public boolean test() {
		int[] activePills = game.getActivePillsIndices();
		for (int p : activePills){
			int x = game.getNodeXCood(p);
			if (x >= xMin && x <= xMax){
				int y = game.getNodeYCood(p);
				if (y >= yMin && y <= yMax)
					return true;
			}
		}
		return false;
	}

}
