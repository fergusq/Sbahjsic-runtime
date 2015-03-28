package sbahjsic.parser.compiler;

import sbahjsic.runtime.RuntimeContext;

final class EndIf extends Instruction {
	
	final static EndIf INSTANCE = new EndIf();
	
	@Override
	public void execute(RuntimeContext runtime) {
		throw new AssertionError("NYI");
	}

	@Override
	public String toString() {
		return "endif";
	}
	
}