package sbahjsic.parser.compiler;

import sbahjsic.runtime.RuntimeContext;

final class EndIf extends Instruction {
	
	final static EndIf INSTANCE = new EndIf();
	
	@Override
	public void execute(RuntimeContext runtime) {
		runtime.scopeStack().removeTop();
	}

	@Override
	public String toString() {
		return "endif";
	}

	@Override
	public InstructionType type() {
		return InstructionType.ENDIF;
	}
}