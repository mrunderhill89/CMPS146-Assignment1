package edu.ucsc.gameAI;

import pacman.game.Constants.MOVE;
import pacman.game.Game;
import edu.ucsc.gameAI.decisionTrees.binary.IBinaryNode;

public class NeutralAction implements IAction, IBinaryNode {
	public IAction makeDecision() {return this;}
	public IAction makeDecision(Game game) {return this;}
	@Override
	public MOVE getMove(){return MOVE.NEUTRAL;}
	@Override
	public MOVE getMove(Game game) {return MOVE.NEUTRAL;}
	@Override
	public void doAction(Game game) {;}
	@Override
	public void doAction() {;}
}
