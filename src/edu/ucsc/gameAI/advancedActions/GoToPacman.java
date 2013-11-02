/**
 * 
 */
package edu.ucsc.gameAI.advancedActions;

import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;
import pacman.game.internal.Maze;
import edu.ucsc.gameAI.IAction;
import edu.ucsc.gameAI.decisionTrees.binary.IBinaryNode;

/**
 * @author Ed Ramirez
 *
 */
public class GoToPacman implements IAction, IBinaryNode {

	protected MOVE move;
	protected GHOST ghost;
	
	public GoToPacman(GHOST _ghost) {
		ghost = _ghost;
		move = MOVE.NEUTRAL;
	}
	
	@Override
	public void doAction() {

	}

	public void doAction(Game game, GHOST _ghost) {
		ghost = _ghost;
		doAction(game);
	}
	
	@Override
	public void doAction(Game game) {
		int gNode = game.getGhostCurrentNodeIndex(ghost);
		int pNode = game.getPacmanCurrentNodeIndex();
		MOVE gMove = game.getGhostLastMoveMade(ghost);
		MOVE pMove = game.getPacmanLastMoveMade();
		Maze maze = game.getCurrentMaze();
		int lairNode = maze.lairNodeIndex;
		
		// this approximates a previous move based on orientation from the lair
		if (gMove == MOVE.NEUTRAL) {
			int lairX = game.getNodeXCood(lairNode);
			int lairY = game.getNodeYCood(lairNode);
			
			int gX = game.getNodeXCood(pNode);
			int gY = game.getNodeYCood(gNode);
			
			if (Math.abs(gX - lairX) > Math.abs(gY - lairY)) {
				if (gX > lairX)
					gMove = MOVE.RIGHT;
				else
					gMove = MOVE.LEFT;
			}
			else {
				if (gY > lairY)
					gMove = MOVE.UP;
				else
					gMove = MOVE.DOWN;
			}
		}
		
		int targetNode = pNode;
		int nextNode = -1;
		int interceptNode = pNode;
		nextNode = game.getNeighbour(pNode, pMove);
		
		while (nextNode > -1) {
			if (game.isJunction(nextNode)) {
				interceptNode = nextNode;
				break;
			}
			
			nextNode = game.getNeighbour(nextNode, pMove);
		}
		
		double distToPac = game.getDistance(gNode, pNode, DM.EUCLID);
		double distToIntercept= game.getDistance(gNode, interceptNode, gMove, DM.EUCLID);
		
		if (distToPac < distToIntercept || 
				game.getNodeXCood(gNode) == game.getNodeXCood(pNode) ||
				game.getNodeYCood(gNode) == game.getNodeYCood(pNode)) {
			targetNode = pNode;
//			System.out.println(ghost + " chasing Pacman");
		}
		else {
			targetNode = interceptNode;
//			System.out.println(ghost + " found junction");
		}
		
//		System.out.println(ghost + " on " + gNode);
//		System.out.println("Pacman on " + pNode);
//		System.out.println("Move " + gMove);
		
		for (int node : game.getNeighbouringNodes(gNode))
		{
			if (node == lairNode)
			{
				gMove = MOVE.NEUTRAL;
				break;
			}
		}
		
		if (lairNode != gNode) {
			try {
				move = game.getNextMoveTowardsTarget(gNode, targetNode, gMove, DM.EUCLID);
			} catch (Exception e) {
//				System.out.println(ghost + " " + gNode);
//				System.out.println("Pacman " + pNode);
//				System.out.println("Move " + gMove);
			}
			
		}
		else
			move = game.getNextMoveTowardsTarget(gNode, targetNode, MOVE.NEUTRAL, DM.EUCLID);
		
//		System.out.println("This move:" + move);
//		System.out.println("Last move:" + gMove);
	}

	@Override
	public MOVE getMove() {
		return move;
	}

	@Override
	public MOVE getMove(Game game) {
		return move;
	}

	@Override
	public IAction makeDecision(Game game) {
		return this;
	}
	
	public IAction makeDecision() {
		return this;
	}

}
