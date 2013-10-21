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
public class GhostInRegion implements ICondition {
	Game game;
	public GhostInRegion(Game _game, int x1, int y1, int x2, int y2) {
		game = _game;
	}
	
	public GhostInRegion(int x1, int y1, int x2, int y2) {
		this(null, x1, x2, y1, y2);
	}
	
	@Override
	public boolean test(Game _game){
		game = _game;
		return test();
	}
	
	@Override
	public boolean test() {
		// TODO Auto-generated method stub
		return false;
	}

}
