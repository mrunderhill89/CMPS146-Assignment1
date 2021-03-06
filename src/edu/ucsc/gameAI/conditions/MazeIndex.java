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
public class MazeIndex implements ICondition {

	Game game;
	int index;
	
	public MazeIndex(Game _game, int _index)
	{
		game = _game;
		index = _index;
	}
	
	public MazeIndex(int _index) {
		this(null, _index);
	}

	@Override
	public boolean test(Game _game){
		game = _game;
		return test();
	}
	
	@Override
	public boolean test() {
		if (index == game.getMazeIndex()){
			return true;
		}
		return false;
	}

}
