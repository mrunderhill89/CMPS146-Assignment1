package pacman.entries.ghosts;

import java.util.Dictionary;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import edu.ucsc.gameAI.*;
import edu.ucsc.gameAI.conditions.*;
import edu.ucsc.gameAI.decisionTrees.binary.BinaryDecision;
import edu.ucsc.gameAI.decisionTrees.binary.IBinaryNode;
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
	private Map<GHOST, BinaryDecision> ghostDecisionTrees = new HashMap<GHOST, BinaryDecision>();
	
	public MyGhosts() {
		ghostDecisionTrees.put(GHOST.BLINKY, initBlinky());
		ghostDecisionTrees.put(GHOST.PINKY, initPinky());
		ghostDecisionTrees.put(GHOST.INKY, initInky());
		ghostDecisionTrees.put(GHOST.SUE, initSue());
	}
	
	private BinaryDecision initBlinky() {
		BinaryDecision tree = new BinaryDecision();
		tree = makeDummyTree(new IsEdible(GHOST.BLINKY), new GoUpAction(), new GoRightAction());
		return tree;
	}
	
	private BinaryDecision initPinky() {
		BinaryDecision tree = new BinaryDecision();
		tree = makeDummyTree(new IsEdible(GHOST.PINKY), new GoRightAction(), new GoDownAction());
		return tree;
	}
	
	private BinaryDecision initInky() {
		BinaryDecision tree = new BinaryDecision();
		tree = makeDummyTree(new IsEdible(GHOST.INKY), new GoDownAction(), new GoLeftAction());
		return tree;
	}
	
	private BinaryDecision initSue() {
		BinaryDecision tree = new BinaryDecision();
		tree = makeDummyTree(new IsEdible(GHOST.BLINKY), new GoLeftAction(), new GoUpAction());
		return tree;
	}
	
	private BinaryDecision makeDummyTree(ICondition con, IBinaryNode act1, IBinaryNode act2) {
		BinaryDecision tree = new BinaryDecision();
		tree.setCondition(con);
		tree.setTrueBranch(act1);
		tree.setFalseBranch(act2);
		return tree;
	}
	
	public EnumMap<GHOST, MOVE> getMove(Game game, long timeDue)
	{
		myMoves.clear();
		
		for (GHOST ghost : GHOST.values()) {
			
			BinaryDecision tree = ghostDecisionTrees.get(ghost); 
			IAction action = tree.makeDecision(game);
			myMoves.put(ghost, action.getMove());
			
		}
		
		return myMoves;
	}
}