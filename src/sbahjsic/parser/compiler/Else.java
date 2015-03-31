package sbahjsic.parser.compiler;

import sbahjsic.runtime.RuntimeContext;

public final class Else extends Instruction {
	
	public final static Else INSTANCE = new Else();
	
	private Else() {}

	@Override
	public void execute(RuntimeContext runtime) {
		runtime.scopeStack().top().switchExecution();
	}

	@Override
	public String toString() {
		return "else";
	}

	@Override
	public InstructionType type() {
		return InstructionType.ELSE;
	}
	
}