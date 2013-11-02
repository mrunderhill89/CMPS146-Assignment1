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
public class PowerPillsLeft implements ICondition {

	Game game;
	int maxPills;
	
	public PowerPillsLeft(int num) {
		this(null, num);
	}
	
	public PowerPillsLeft(Game _game, int num) {
		game = _game;
		maxPills = num;
	}
	
	@Override
	public boolean test(Game _game) {
		game = _game;
		return test();
	}

	@Override
	public boolean test() {
		
		return game.getActivePowerPillsIndices().length < maxPills;
	}

}
