package edu.ucsc.gameAI.customConditions;

import pacman.game.Game;
import edu.ucsc.gameAI.ICondition;
/**
 * 
 * @author Kevin Cameron
 *
 */
public class ConsolePrintCondition implements ICondition {
	protected String label;
	protected ICondition test;
	
	public ConsolePrintCondition(String l, ICondition t){
		label = l;
		test = t;
	}
	
	@Override
	public boolean test() {
		System.out.print(label);
		boolean subTest = test.test();
		if (subTest){
			System.out.println(":true");
		} else {
			System.out.println(":false");
		}
		return subTest;
	}
	
	@Override
	public boolean test(Game game){
		System.out.print(label);
		boolean subTest = test.test(game);
		if (subTest){
			System.out.println(":true");
		} else {
			System.out.println(":false");
		}
		return subTest;
	}

}
