package edu.ucsc.gameAI.customConditions;

import java.util.ArrayList;

import pacman.entries.pacman.MyPacMan;
import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;

public class IsLooping implements ICondition {

	@Override
	public boolean test(Game game) {
		return test();
	}

	@Override
	public boolean test() {
		ArrayList<Integer> lastTurns = new ArrayList<Integer>();
		lastTurns.addAll(MyPacMan.getLastTurns());
		for (int ia = 0; ia < lastTurns.size(); ia++){
			for (int ib = ia; ib < lastTurns.size(); ib++){
				if (lastTurns.get(ia) == lastTurns.get(ib))
					return true;
			}			
		}
		return false;
	}

}
