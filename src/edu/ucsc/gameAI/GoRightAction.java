package edu.ucsc.gameAI;

import pacman.game.Constants.MOVE;
import pacman.game.Game;
import edu.ucsc.gameAI.decisionTrees.binary.IBinaryNode;

public class GoRightAction implements IAction, IBinaryNode {

	public void doAction() {
		// TODO Auto-generated method stub
		
	}
	
	public IAction makeDecision() {return this;}
	public IAction makeDecision(Game game) {return this;}

	@Override
	public MOVE getMove(){return MOVE.RIGHT;}
}
