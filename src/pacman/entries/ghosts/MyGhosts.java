package pacman.entries.ghosts;

import java.util.Dictionary;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import edu.ucsc.gameAI.*;
import edu.ucsc.gameAI.conditions.*;
import edu.ucsc.gameAI.decisionTrees.binary.BinaryDecision;
import edu.ucsc.gameAI.decisionTrees.binary.IBinaryNode;
import pacman.controllers.Controller;
import pacman.game.Constants;
import pacman.game.Constants.DM;
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
	private Coords mazeSize;
	private Coords center;
	private Coords cornerRegion1;
	private Coords cornerRegion2;
	private Coords cornerRegion3;
	private Coords cornerRegion4;
	PatrolRegion patrolRegion1;
	PatrolRegion patrolRegion2;
	PatrolRegion patrolRegion3;
	PatrolRegion patrolRegion4;
	private PatrolRegion[] regions;
	
	// roles
	// -1 = other
	// 0 = chase pac man
	// [a][b] = patrol region [a] at point [b], e.g. 12 = patrol region 1 at the third patrol point
	private Map<GHOST, Integer> ghostRoles = new HashMap<GHOST, Integer>();
	
	public MyGhosts() {
		ghostRoles.put(GHOST.BLINKY, 0);
		ghostRoles.put(GHOST.PINKY, 0);
		ghostRoles.put(GHOST.INKY, 0);
		ghostRoles.put(GHOST.SUE, 0);
		
		mazeSize = new Coords(108, 120);
		center = new Coords(mazeSize.x / 2, mazeSize.y / 2);
		cornerRegion1 = new Coords(center.x / 2, center.y / 2);
		cornerRegion2 = new Coords(center.x + center.x / 2, center.y / 2);
		cornerRegion3 = new Coords(center.x / 2, center.y + center.y / 2);
		cornerRegion4 = new Coords(center.x + center.x / 2, center.y + center.y / 2);
		
		Coords[] corner1Patrols = new Coords[3];
		corner1Patrols[0] = new Coords(cornerRegion1);
		corner1Patrols[1] = new Coords(cornerRegion1.x + cornerRegion1.x / 2, cornerRegion1.y / 2);
		corner1Patrols[2] = new Coords(cornerRegion1.x / 2, cornerRegion1.y + cornerRegion1.y / 2);

		Coords[] corner2Patrols = new Coords[3];
		corner2Patrols[0] = new Coords(cornerRegion2);
		corner2Patrols[1] = new Coords(cornerRegion2.x - center.x / 4, center.y / 4);
		corner2Patrols[2] = new Coords(cornerRegion2.x + center.x / 4, cornerRegion2.y + center.y / 4);
		
		Coords[] corner3Patrols = new Coords[3]; 
		corner3Patrols[0] = new Coords(cornerRegion3);
		corner3Patrols[1] = new Coords(cornerRegion3.x + center.x / 4, cornerRegion3.y + center.y / 4);
		corner3Patrols[2] = new Coords(cornerRegion3.x - center.x / 4, center.y / 4);
		
		Coords[] corner4Patrols = new Coords[3]; 
		corner4Patrols[0] = new Coords(cornerRegion4);
		corner4Patrols[1] = new Coords(cornerRegion4.x - center.x / 4, cornerRegion4.y + center.y / 4);
		corner4Patrols[2] = new Coords(cornerRegion4.x + center.x / 4, cornerRegion4.y - center.y / 4);
		
		patrolRegion1 = new PatrolRegion(1, corner1Patrols);
		patrolRegion2 = new PatrolRegion(2, corner2Patrols);
		patrolRegion3 = new PatrolRegion(3, corner3Patrols);
		patrolRegion4 = new PatrolRegion(4, corner4Patrols);
		
		ghostDecisionTrees.put(GHOST.BLINKY, initBlinky());
		ghostDecisionTrees.put(GHOST.PINKY, initPinky());
		ghostDecisionTrees.put(GHOST.INKY, initInky());
		ghostDecisionTrees.put(GHOST.SUE, initSue());
	}
	
	private BinaryDecision initBlinky() {
		BinaryDecision tree = new BinaryDecision();
		tree = makeSimpleTree(new IsEdible(GHOST.BLINKY), new GoUpAction(), new GoRightAction());
		return tree;
	}
	
	private BinaryDecision initPinky() {
		BinaryDecision tree = new BinaryDecision();
		tree = makeSimpleTree(new IsEdible(GHOST.PINKY), new GoRightAction(), new GoDownAction());
		return tree;
	}
	
	private BinaryDecision initInky() {
		BinaryDecision tree = new BinaryDecision();
		tree = makeSimpleTree(new IsEdible(GHOST.INKY), new GoDownAction(), new GoLeftAction());
		return tree;
	}
	
	private BinaryDecision initSue() {
		BinaryDecision tree = new BinaryDecision();
		tree = makeSimpleTree(new IsEdible(GHOST.BLINKY), new GoLeftAction(), new GoUpAction());
		return tree;
	}
	
	private BinaryDecision makeSimpleTree(ICondition con, IBinaryNode act1, IBinaryNode act2) {
		BinaryDecision tree = new BinaryDecision();
		tree.setCondition(con);
		tree.setTrueBranch(act1);
		tree.setFalseBranch(act2);
		return tree;
	}
	
	private GHOST[] sortGhosts(Game game) {
		// sort ghosts by their distance to pac man
		
		TreeMap<Double, GHOST> distGhostTree = new TreeMap<Double, GHOST>();
		
		for (GHOST ghost : GHOST.values()) {
			double dist = 0;
			int gNode = game.getGhostCurrentNodeIndex(ghost);
			int pNode = game.getPacmanCurrentNodeIndex();
			dist = game.getDistance(gNode, pNode, game.getGhostLastMoveMade(ghost), DM.PATH);
			
			distGhostTree.put(dist, ghost);
		}
		
		return distGhostTree.values().toArray(new GHOST[distGhostTree.size()]);
	}
	
	private PatrolRegion getPatrolRegion(Game game) {
		PacmanInRegion region1Con = new PacmanInRegion(game, 0, 0, cornerRegion1.x, cornerRegion1.y);
		PacmanInRegion region2Con = new PacmanInRegion(game, cornerRegion2.x, cornerRegion2.y, mazeSize.x, 0);
		PacmanInRegion region3Con = new PacmanInRegion(game, 0, mazeSize.y, cornerRegion3.x, cornerRegion3.y);
		PacmanInRegion region4Con = new PacmanInRegion(game, mazeSize.x, mazeSize.y, cornerRegion1.x, cornerRegion1.y);
		
		PatrolRegion targetRegion = null;
		
		// copying region so that modifying the target one won't modify the predfined ones
		if (region1Con.test())
			targetRegion = new PatrolRegion(patrolRegion1);
		else if (region2Con.test())
			targetRegion = new PatrolRegion(patrolRegion2);
		else if (region3Con.test())
			targetRegion = new PatrolRegion(patrolRegion3);
		else if (region4Con.test())
			targetRegion = new PatrolRegion(patrolRegion4);
		else
			return null;
		
		GHOST[] ghosts = sortGhosts(game);
		
		boolean foundClosest = false;
		for (GHOST ghost : ghosts) {
			
			if (game.isGhostEdible(ghost))
				continue;
			if (!foundClosest) {
				foundClosest = true;
				continue;
			}
			
			targetRegion.assign(ghost);
		}
		
		return targetRegion;
	}
	
	public EnumMap<GHOST, MOVE> getMove(Game game, long timeDue)
	{
		myMoves.clear();
		
		PatrolRegion patrolRegion = getPatrolRegion(game);
		
		for (GHOST ghost : GHOST.values()) {
			
			Coords patrolPoint = patrolRegion.getPatrolPoint(ghost);
			
			BinaryDecision tree = ghostDecisionTrees.get(ghost); 
			IAction action = tree.makeDecision(game);
			myMoves.put(ghost, action.getMove());
			
		}
		
		return myMoves;
	}
}