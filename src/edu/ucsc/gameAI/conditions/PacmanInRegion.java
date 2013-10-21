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
public class PacmanInRegion implements ICondition {

	Game game;
	
	public PacmanInRegion(Game _game, int x1, int y1, int x2, int y2) {
		this(x1, y1, x2, y2);
		game = _game;
	}
	
	public PacmanInRegion(int x1, int y1, int x2, int y2){
		game = null;
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
