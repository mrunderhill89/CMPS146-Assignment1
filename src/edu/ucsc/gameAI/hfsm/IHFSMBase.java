package edu.ucsc.gameAI.hfsm;

import java.util.Collection;

import pacman.game.Game;
import edu.ucsc.gameAI.IAction;

public interface IHFSMBase {

	public IAction getAction();
	
	public IResult update(Game game);
	
	public Collection<IAction> updateDown();
	
}
