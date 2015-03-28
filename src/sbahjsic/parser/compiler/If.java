package sbahjsic.parser.compiler;

import sbahjsic.runtime.RuntimeContext;

final class If extends Instruction {
	
	final static If INSTANCE = new If();
	
	@Override
	public void execute(RuntimeContext runtime) {
		throw new AssertionError("NYI");
	}

	@Override
	public String toString() {
		return "if";
	}
}