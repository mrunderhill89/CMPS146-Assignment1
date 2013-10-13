package edu.ucsc.gameAI.conditions;

import java.util.Random;

import edu.ucsc.gameAI.ICondition;

public class RandomCondition implements ICondition {
	Random r;
	public RandomCondition(){
		r = new Random();
	}
	@Override
	public boolean test() {
		return r.nextBoolean();
	}
}
