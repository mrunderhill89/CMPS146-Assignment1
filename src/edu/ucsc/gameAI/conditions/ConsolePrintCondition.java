package edu.ucsc.gameAI.conditions;

import edu.ucsc.gameAI.ICondition;

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
		boolean v = test.test();
		if (v){
			System.out.println("true");
		} else {
			System.out.println("false");
		}
		return v;
	}

}
