package pacman.entries.pacman;

import java.util.ArrayList;

import edu.ucsc.gameAI.*;
import edu.ucsc.gameAI.conditions.*;
import edu.ucsc.gameAI.customActions.*;
import edu.ucsc.gameAI.customConditions.*;
import edu.ucsc.gameAI.hfsm.HFSM;
import edu.ucsc.gameAI.hfsm.HTransition;
import pacman.controllers.Controller;
import pacman.game.Constants.GHOST;
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

	protected void generateFSM(){
		root = new HFSM("root");
		
		//Collection of all of Pac-Man's moves when not dealing with ghosts.
		HFSM active = new HFSM("active", root);
		
		//Allows Pac-Man to chase down the nearest pills
		HFSM gather = new HFSM("gather", active);
		//Find the shortest path to the nearest pill
		HFSM chasePill = new HFSM("chasePill", gather);
		chasePill.setEntryAction(new ConsolePrintAction("begin gather state"));
		chasePill.setAction(new GoToNearestPill());
		
		//Stand next to the nearest power pill and wait for a ghost to come by.
		HFSM campPowerPill = new HFSM("campPowerPill", gather);
		
		HFSM rampage = new HFSM("rampage", active);
		rampage.setEntryAction(new ConsolePrintAction("begin rampage state"));
		rampage.setAction(new GoToNearestEdibleGhost());

//		@SuppressWarnings("unused")
//		HTransition fromRampage = new HTransition(chasePill, rampage, new EdibleGhostProximity(20));
		@SuppressWarnings("unused")
		HTransition fromRampage = new HTransition(rampage, gather, new StopRampage());

		HFSM reactive = new HFSM("reactive", root);
		
		HFSM avoid = new HFSM("avoid", reactive);
		
		HFSM hide = new HFSM("hide", reactive);
		
		HFSM dodge = new HFSM("dodge", reactive);
		
		HFSM race = new HFSM("race", reactive);
		
		HFSM chasePowerPill = new HFSM("chasePowerPill", reactive);
		chasePill.setAction(new GoToNearestPowerPill());
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
		return myMove;
	}
}