package edu.ucsc.gameAI.customActions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import pacman.game.Game;
import pacman.game.Constants.MOVE;
import edu.ucsc.gameAI.IAction;
import edu.ucsc.gameAI.decisionTrees.binary.IBinaryNode;

public class GoCounterClockwise implements IAction, IBinaryNode{
	public static final Map<MOVE, MOVE> DIRECTIONS;
	static {
	    Map<MOVE, MOVE> map = new HashMap<MOVE, MOVE>();
	    map.put(MOVE.UP, MOVE.LEFT);
	    map.put(MOVE.LEFT, MOVE.DOWN);
	    map.put(MOVE.DOWN, MOVE.RIGHT);
	    map.put(MOVE.RIGHT, MOVE.UP);
	    DIRECTIONS = Collections.unmodifiableMap(map);
	}
	protected MOVE move = MOVE.UP;
	
	
	public IAction makeDecision() {return this;}
	public IAction makeDecision(Game game) {return this;}
	@Override
	public MOVE getMove(){return getMove(move);}
	@Override
	public MOVE getMove(Game game) {return getMove(move);}

	public MOVE getMove(MOVE lastMove){
		move = DIRECTIONS.get(lastMove);
		return move;
	}
	
	@Override
	public void doAction(Game game) {;}
	@Override
	public void doAction() {;}

}
