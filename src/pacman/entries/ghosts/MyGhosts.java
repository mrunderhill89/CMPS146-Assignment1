package pacman.entries.ghosts;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import edu.ucsc.gameAI.*;
import edu.ucsc.gameAI.advancedActions.*;
import edu.ucsc.gameAI.conditions.*;
import edu.ucsc.gameAI.decisionTrees.binary.BinaryDecision;
import edu.ucsc.gameAI.decisionTrees.binary.IBinaryNode;
import pacman.controllers.Controller;
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
	private Coords mazeSize;
	private Coords center;
	private Coords cornerRegion1;
	private Coords cornerRegion2;
	private Coords cornerRegion3;
	private Coords cornerRegion4;
	private PatrolRegion patrolRegion1;
	private PatrolRegion patrolRegion2;
	private PatrolRegion patrolRegion3;
	private PatrolRegion patrolRegion4;
	
//	private static final double PROX_THRESHOLD = 
	
	public MyGhosts() {
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
	}
	
	private BinaryDecision makeSimpleTree(ICondition con, IBinaryNode trueNode, IBinaryNode falseNode) {
		BinaryDecision tree = new BinaryDecision();
		tree.setCondition(con);
		tree.setTrueBranch(trueNode);
		tree.setFalseBranch(falseNode);
		return tree;
	}
	
	private BinaryDecision makeRuntimeTree(Game game, GHOST ghost, Coords patrolPoint) {
		GoToCoords goToCoords = new GoToCoords(ghost, patrolPoint);
		GoToPacman goToPacman = new GoToPacman(ghost);
		GoAwayFromPacman goAway = new GoAwayFromPacman(ghost);
		GoToNearestJunction goToNode = new GoToNearestJunction(ghost);
		GoToNearestPowerPillGhost goToPP = new GoToNearestPowerPillGhost(ghost); 
		
		PowerPillsLeft pp2LeftCon = new PowerPillsLeft(2);
		IsPacmanCloseToPowerPill aboutToEatCon = new IsPacmanCloseToPowerPill(); 
		IsCloseToPacman isCloseCon = new IsCloseToPacman(ghost);
		IsFarFromPacman isFarCon = new IsFarFromPacman(ghost);
		IsCoordNull isPointNullCon = new IsCoordNull(patrolPoint);
		IsPacmanChasing isChasingCon = new IsPacmanChasing(ghost);
		IsEdible edibleCon = new IsEdible(ghost);
		
		BinaryDecision pp2Left = makeSimpleTree(pp2LeftCon, goToPP, goToCoords);
		BinaryDecision isClose = makeSimpleTree(isCloseCon, goToPacman, pp2Left);
		BinaryDecision isFar = makeSimpleTree(isFarCon, goToPacman, goAway);
		BinaryDecision isPointNull = makeSimpleTree(isPointNullCon, goToPacman, isClose);
		BinaryDecision isChasing = makeSimpleTree(isChasingCon, goToNode, isFar);
		BinaryDecision isClose2 = makeSimpleTree(isCloseCon, isPointNull, goAway);
		BinaryDecision aboutToEat = makeSimpleTree(aboutToEatCon, isClose2, isPointNull);
		BinaryDecision isEdible = makeSimpleTree(edibleCon, isChasing, aboutToEat);
//		BinaryDecision isEdible = makeSimpleTree(edibleCon, isChasing, goToPacman);
		
		return isEdible;
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
		PacmanInRegion region1Con = new PacmanInRegion(game, 0, 0, center.x, center.y);
		PacmanInRegion region2Con = new PacmanInRegion(game, center.x, center.y, mazeSize.x, 0);
		PacmanInRegion region3Con = new PacmanInRegion(game, 0, mazeSize.y, center.x, center.y);
		PacmanInRegion region4Con = new PacmanInRegion(game, mazeSize.x, mazeSize.y, center.x, center.y);

		
		PatrolRegion targetRegion = null;
		
		// copying region so that modifying the target one won't modify the predefined ones
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
			
			Coords patrolPoint;
			if (patrolRegion != null)
				patrolPoint = patrolRegion.getPatrolPoint(ghost);
			else
				patrolPoint = null;
			
			BinaryDecision tree = makeRuntimeTree(game, ghost, patrolPoint);
			IAction action = tree.makeDecision(game);
			action.doAction(game);
			myMoves.put(ghost, action.getMove());
			
		}
		
		return myMoves;
	}
}