/**
 * 
 */
package edu.ucsc.gameAI.conditions;

import edu.ucsc.gameAI.ICondition;
import pacman.game.Game;

/**
 * @author Ed Ramirez
 *
 */
public class PacmanWasEaten implements ICondition {

	Game game;
	
	public PacmanWasEaten(Game _game) {
		
	}
	
	@Override
	public boolean test() {
		// TODO Auto-generated method stub
		return false;
	}

}