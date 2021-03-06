package edu.ucsc.gameAI;

import pacman.game.Constants.MOVE;
import pacman.game.Game;


/**
 * The Action interface for decision tree action nodes and FSM actions. 
 * 
 * @author Josh McCoy
 */
public interface IAction {
	
	/**
	 * Performs the action associated with this Action node.
	 */
	public void doAction();
	public void doAction(Game game);
	
	public MOVE getMove();
	public MOVE getMove(Game game);
}
