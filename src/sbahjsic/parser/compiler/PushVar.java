package sbahjsic.parser.compiler;

import sbahjsic.runtime.RuntimeContext;
import sbahjsic.runtime.type.SRef;

final class PushVar extends Instruction {
	
	private final String value;
	
	PushVar(String value) {
		this.value = value;
	}

	@Override
	public void execute(RuntimeContext runtime) {
		runtime.push(new SRef(value, runtime.getMemory(value)));
	}

	@Override
	public String toString() {
		return "pshvar " + value;
	}
}