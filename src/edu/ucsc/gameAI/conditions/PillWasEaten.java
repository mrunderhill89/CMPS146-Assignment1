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
public class PillWasEaten implements ICondition {

	Game game;
	
	public PillWasEaten(Game _game) {
		
	}
	
	public PillWasEaten() {
		this(null);
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
