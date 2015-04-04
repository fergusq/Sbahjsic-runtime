package sbahjsic.parser.compiler;

import sbahjsic.runtime.RuntimeContext;

final class EndFunction extends Instruction {
	
	final static EndFunction INSTANCE = new EndFunction();
	
	private EndFunction() {}

	@Override
	public void execute(RuntimeContext runtime) {
		runtime.scopeStack().removeTop();
	}

	@Override
	public String toString() {
		return "endfunc";
	}

	@Override
	public InstructionType type() {
		return InstructionType.ENDFUNCTION;
	}
	
}