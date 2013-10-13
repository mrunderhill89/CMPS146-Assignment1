package edu.ucsc.gameAI;

public class ConsolePrintAction implements IAction {
	protected String message;
	protected IAction action;
	
	public ConsolePrintAction(String m, IAction a){
		message = m;
		action = a;
	}
	
	public ConsolePrintAction(String m){
		this(m, null);
	}
	
	@Override
	public void doAction() {
		System.out.println(message);
		if (action != null){
			action.doAction();
		}
	}

}
