package pacman.entries.pacman;

import java.util.ArrayList;

import edu.ucsc.gameAI.*;
import edu.ucsc.gameAI.conditions.*;
import edu.ucsc.gameAI.hfsm2.HFSMFull;
import edu.ucsc.gameAI.hfsm2.HTransition;
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
	private HFSMFull root;	
	public MyPacMan(){
		super();
		generateFSM();
	}

	protected void generateFSM(){
		root = new HFSMFull("root");
		
		//Find the shortest path to the nearest pill
		HFSMFull gather = new HFSMFull("gather", root);
		gather.setEntryAction(new ConsolePrintAction("begin gather state"));
		gather.setUpdateAction(new ConsolePrintAction("gather state", new GoRightAction()));
				
		//There is a ghost in Pac-Man's way to the next pill. Try to get him out of the way.
		HFSMFull lure = new HFSMFull("lure", root);
		lure.setEntryAction(new ConsolePrintAction("begin lure state"));
		lure.setUpdateAction(new ConsolePrintAction("lure state", new GoLeftAction()));
		
		//There is a ghost following Pac-Man. Try to shake it off.
		HFSMFull retreat = new HFSMFull("retreat", root);
		retreat.setEntryAction(new ConsolePrintAction("begin retreat state"));
		retreat.setUpdateAction(new ConsolePrintAction("retreat state"));
		
		//Pac-Man has just eaten a power pill. Eat all the ghosts!
		HFSMFull rampage = new HFSMFull("rampage", root);
		rampage.setEntryAction(new ConsolePrintAction("begin rampage state"));
		rampage.setUpdateAction(new ConsolePrintAction("rampage state"));
	}
	
	protected void generateTrees(){
		
	}
	
	public MOVE getMove(Game game, long timeDue) 
	{
		myMove = MOVE.NEUTRAL;
		//Place your game logic here to play the game as Ms Pac-Man
		ArrayList<IAction> actions = root.getActions();
		if (!actions.isEmpty()){
			for (IAction a: actions){
				a.doAction();
				if (a.getMove() != MOVE.NEUTRAL){
					myMove = a.getMove();
				}
			}
		}
		return myMove;
	}
}