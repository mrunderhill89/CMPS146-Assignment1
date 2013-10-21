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
public class PowerPillWasEaten implements ICondition {

	Game game;
	
	public PowerPillWasEaten(Game _game) {
		
	}
	
	public PowerPillWasEaten() {
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
