package pacman.entries.ghosts;

import java.util.Dictionary;
import java.util.EnumMap;

import edu.ucsc.gameAI.*;
import edu.ucsc.gameAI.conditions.*;
import edu.ucsc.gameAI.decisionTrees.binary.BinaryDecision;
import pacman.controllers.Controller;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;
/*
 * This is the class you need to modify for your entry. In particular, you need to
 * fill in the getActions() method. Any additional classes you write should either
 * be placed in this package or sub-packages (e.g., game.entries.ghosts.mypackage).
 */
public class MyGhosts extends Controller<EnumMap<GHOST,MOVE>>
{
	private EnumMap<GHOST, MOVE> myMoves=new EnumMap<GHOST, MOVE>(GHOST.class);
	private Dictionary<GHOST, BinaryDecision> ghostDecisionTrees;
	
	public MyGhosts() {
		ghostDecisionTrees.put(GHOST.BLINKY, initBlinky());
		ghostDecisionTrees.put(GHOST.PINKY, initPinky());
		ghostDecisionTrees.put(GHOST.INKY, initInky());
		ghostDecisionTrees.put(GHOST.SUE, initSue());
	}
	
	private BinaryDecision initBlinky() {
		BinaryDecision tree = new BinaryDecision();
		
		return tree;
	}
	
	private BinaryDecision initPinky() {
		BinaryDecision tree = new BinaryDecision();
		
		return tree;
	}
	
	private BinaryDecision initInky() {
		BinaryDecision tree = new BinaryDecision();
		
		return tree;
	}
	
	private BinaryDecision initSue() {
		BinaryDecision tree = new BinaryDecision();
		
		return tree;
	}
	
	public EnumMap<GHOST, MOVE> getMove(Game game, long timeDue)
	{
		myMoves.clear();
		
		for (GHOST ghost : GHOST.values()) {
//			BinaryDecision decision = new BinaryDecision();
//			decision.setCondition(new IsEdible(game, ghost));
//			decision.setTrueBranch(new GoLeftAction());
//			decision.setFalseBranch(new GoUpAction());
//			
//			IAction action = decision.makeDecision(game);
//			myMoves.put(ghost, action.getMove());
			
			BinaryDecision tree = ghostDecisionTrees.get(ghost); 
			IAction action = tree.makeDecision(game);
			myMoves.put(ghost, action.getMove());
			
		}
		
		return myMoves;
	}
}