package edu.ucsc.gameAI.conditions;

import edu.ucsc.gameAI.ICondition;
import pacman.game.Constants.GHOST;
import pacman.game.Game;

public class IsEdible implements ICondition {
	
	Game game;
	GHOST ghost;
	public IsEdible(Game _game, GHOST _ghost)
	{
		game=_game;
		ghost=_ghost;
	}
	
	public IsEdible(GHOST _ghost){
		this(null,_ghost);
	}
	
	@Override
	public boolean test(Game _game){
		game = _game;
		return test();
	}
		
	public boolean test() 
	{
		return game.isGhostEdible(ghost);
	}
}
