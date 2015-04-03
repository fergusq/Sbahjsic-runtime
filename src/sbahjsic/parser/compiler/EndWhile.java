package sbahjsic.parser.compiler;

import sbahjsic.runtime.RuntimeContext;

final class EndWhile extends Instruction {
	
	public final static EndWhile INSTANCE = new EndWhile();

	@Override
	public void execute(RuntimeContext runtime) {
		runtime.scopeStack().removeTop();
	}

	@Override
	public String toString() {
		return "endwhile";
	}

	@Override
	public InstructionType type() {
		return InstructionType.ENDWHILE;
	}
	
}