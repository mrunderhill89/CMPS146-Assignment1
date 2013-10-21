package edu.ucsc.gameAI;

import pacman.game.Constants.MOVE;
import pacman.game.Game;
import edu.ucsc.gameAI.decisionTrees.binary.IBinaryNode;

public class GoLeftAction implements IAction, IBinaryNode {

	public void doAction() {
		// TODO Auto-generated method stub
		
	}
	
	public IAction makeDecision() {return this;}
	
	public MOVE getMove(){return MOVE.LEFT;}

	@Override
	public IAction makeDecision(Game game) {
		return this;
	}
}
