package pacman.entries.pacman;

import java.util.LinkedList;
import java.util.ArrayList;

import edu.ucsc.gameAI.*;
import edu.ucsc.gameAI.conditions.*;
import edu.ucsc.gameAI.customActions.*;
import edu.ucsc.gameAI.customConditions.*;
import edu.ucsc.gameAI.hfsm.HFSM;
import edu.ucsc.gameAI.hfsm.HTransition;
import pacman.controllers.Controller;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

/*
 * This is the class you need to modify for your entry. In particular, you need to
 * fill in the getAction() method. Any additional classes you write should either
 * be placed in this package or sub-packages (e.g., game.entries.pacman.mypackage).
 */
public class MyPacMan extends Controller<MOVE>
{
	private MOVE myMove=MOVE.NEUTRAL;
	private HFSM root;	
	public MyPacMan(){
		super();
		generateFSM();
	}
	protected static final int STORED_MOVES = 8;
	
	protected static LinkedList<Integer> lastTurns;
	public static LinkedList<Integer> getLastTurns(){
		return lastTurns;
	}
	
	protected static LinkedList<MOVE> lastMoves;
	public static LinkedList<MOVE> getLastMoves(){
		return lastMoves;
	}
	
	static{
		lastTurns = new LinkedList<Integer>();
		lastMoves = new LinkedList<MOVE>();
	}
	@SuppressWarnings("unused")
	protected void generateFSM(){
		root = new HFSM("root");
		
		//Collection of all of Pac-Man's moves when not dealing with ghosts.
		HFSM active = new HFSM("active", root);
		
		//Allows Pac-Man to chase down the nearest pills
		HFSM gather = new HFSM("gather", active);
		//Find the shortest path to the nearest pill
		HFSM chasePill = new HFSM("chasePill", gather);
		chasePill.setAction(new GoToNearestPill());
		
		//Stand next to the nearest power pill and wait for a ghost to come by.
		HFSM campPowerPill = new HFSM("campPowerPill", gather);
		campPowerPill.setAction(new GoBackAndForth());
		HTransition pillToCamp = new HTransition(chasePill, campPowerPill, new CampCondition(10.0, 5.0, 1.0));
		HTransition campGhostClose = new HTransition(campPowerPill, chasePill, new GhostNearby(20.0));
		HTransition campTimeUp = new HTransition(campPowerPill, chasePill, new Timer(30));
		
		//Chase down any convenient edible ghosts as you find them.
		HFSM rampage = new HFSM("rampage", active);
		rampage.setAction(new GoToNearestEdibleGhost());
		HTransition startRampage = new HTransition(gather, rampage, new EdibleGhostInRange(50,20));
		HTransition endRampageEarly = new HTransition(rampage, gather, new NotCondition(new EdibleGhostInRange(60,20)));
		
		//Collection of all of Pac-Man's moves when dealing with ghosts
		HFSM reactive = new HFSM("reactive", root);
		HFSM avoid = new HFSM("avoid", reactive);
		avoid.setAction(new EvadeGhosts());
		HFSM chasePowerPill = new HFSM("chasePowerPill", reactive);
		chasePowerPill.setAction(new GoToNearestPowerPill());		
		HTransition activeToReactive = new HTransition(active, reactive, new AndCondition(
																			new GhostNearby(25.0,10), 
																			new NotCondition(new IsLooping())
																		 		), null, true);
		HTransition reactiveToActive = new HTransition(reactive, active, new NotCondition(new GhostNearby(40.0,10)));

		HTransition avoidToPowerPill = new HTransition(avoid, chasePowerPill, new PowerPillNearby(30.0));
		HTransition resetPowerPill = new HTransition(chasePowerPill, avoid, new NotCondition(new PowerPillNearby(50.0)));
		
		HTransition breakLoop = new HTransition(avoid, active, new IsLooping());
		HTransition resetOnDeath = new HTransition(root, active, new PacmanWasEaten());
	}
	
	protected void generateTrees(){
		
	}
	
	public MOVE getMove(Game game, long timeDue) 
	{
		myMove = MOVE.NEUTRAL;
		//Place your game logic here to play the game as Ms Pac-Man
		ArrayList<IAction> actions = new ArrayList<IAction>();
		actions.addAll(root.update(game).getActions());
		if (!actions.isEmpty()){
			for (IAction a: actions){
				a.doAction(game);
				if (a.getMove() != MOVE.NEUTRAL){
					myMove = a.getMove(game);
				}
			}
		}
		if (myMove != game.getPacmanLastMoveMade()){
			lastMoves.push(myMove);
			lastTurns.push(game.getPacmanCurrentNodeIndex());
			if (lastMoves.size() > STORED_MOVES){
				lastMoves.removeLast();
				lastTurns.removeLast();
			}
		}
		if (game.wasPacManEaten()){
			lastMoves.clear();
			lastTurns.clear();
		}
		return myMove;
	}
}