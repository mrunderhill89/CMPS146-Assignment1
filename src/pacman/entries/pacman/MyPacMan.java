package pacman.entries.pacman;

import java.util.ArrayList;

import edu.ucsc.gameAI.*;
import edu.ucsc.gameAI.conditions.*;
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

	protected void generateFSM(){
		root = new HFSM("root");
		
		//Find the shortest path to the nearest pill
		HFSM gather = new HFSM("gather", root);
		gather.setEntryAction(new ConsolePrintAction("begin gather state"));
		gather.setAction(new GoRightAction());
		
		//There is a ghost in Pac-Man's way to the next pill. Try to get him out of the way.
		HFSM lure = new HFSM("lure", root);
		lure.setEntryAction(new ConsolePrintAction("begin lure state"));
		lure.setAction(new GoLeftAction());
		
		//There is a ghost following Pac-Man. Try to shake it off.
		HFSM retreat = new HFSM("retreat", root);
		retreat.setEntryAction(new ConsolePrintAction("begin retreat state"));
		retreat.setAction(new GoUpAction());
		
		//Pac-Man has just eaten a power pill. Eat all the ghosts!
		HFSM rampage = new HFSM("rampage", root);
		rampage.setEntryAction(new ConsolePrintAction("begin rampage state"));
		rampage.setAction(new GoDownAction());
		
		HTransition onPillEatenL = new HTransition(gather, lure, new PillWasEaten());
		HTransition onPillEatenR = new HTransition(lure, gather, new PillWasEaten());
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