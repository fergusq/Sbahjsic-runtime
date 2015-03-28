package sbahjsic.parser.compiler;

import sbahjsic.runtime.RuntimeContext;

final class PushVar extends Instruction {
	
	private final String value;
	
	PushVar(String value) {
		this.value = value;
	}

	@Override
	public void execute(RuntimeContext runtime) {
		runtime.push(runtime.getMemory(value));
	}

	@Override
	public String toString() {
		return "pshvar " + value;
	}
}