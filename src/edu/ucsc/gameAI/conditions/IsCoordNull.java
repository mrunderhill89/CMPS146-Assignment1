/**
 * 
 */
package edu.ucsc.gameAI.conditions;

import pacman.entries.ghosts.Coords;
import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

/**
 * @author Ed Ramirez
 *
 */
public class IsCoordNull implements ICondition {
	
	Coords point;
	
	public IsCoordNull(Coords _point) {
		point = _point;
	}
	
	public IsCoordNull() {
		this(null);
	}
	
	@Override
	public boolean test(Game game) {
		return test();
	}

	@Override
	public boolean test() {
		return point == null;
	}
	
	public boolean test(Coords _point) {
		point = _point;
		return test();
	}

}
