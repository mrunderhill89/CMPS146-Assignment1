/**
 * 
 */
package edu.ucsc.gameAI.advancedActions;

import pacman.entries.ghosts.Coords;
import pacman.game.Constants.DM;
import pacman.game.Constants.GHOST;
import pacman.game.Constants.MOVE;
import pacman.game.Game;
import pacman.game.internal.Maze;
import pacman.game.internal.Node;
import edu.ucsc.gameAI.IAction;
import edu.ucsc.gameAI.decisionTrees.binary.IBinaryNode;

/**
 * @author Ed Ramirez
 *
 */
public class GoToCoords implements IAction, IBinaryNode {

	protected Coords coords;
	protected MOVE move;
	protected GHOST ghost;
	
	public GoToCoords(GHOST _ghost, Coords _coords) {
		ghost = _ghost;
		move = MOVE.NEUTRAL;
		coords = _coords;
	}
	
	@Override
	public void doAction() {
		
	}

	@Override
	public void doAction(Game game) {
		int gNode = game.getGhostCurrentNodeIndex(ghost);
		Maze maze = game.getCurrentMaze();
		
		Node node = maze.graph[0];
		for (Node index : maze.graph) {
			if (distance(coords.x, coords.y, index.x, index.y) < 
					distance(coords.x, coords.y, node.x, node.y)) {
				node = index;
			}
		}
		
		int targetNode = node.nodeIndex;		
		move = game.getNextMoveTowardsTarget(gNode, targetNode, game.getGhostLastMoveMade(ghost), DM.EUCLID);
	}
	
	private double distance(int x1, int y1, int x2, int y2) {
		double dist = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
		
		return dist;
	}
	
	public void doAction(Game game, GHOST _ghost) {
		ghost = _ghost;
		doAction(game);
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
