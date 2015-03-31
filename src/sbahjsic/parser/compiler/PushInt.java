package sbahjsic.parser.compiler;

import sbahjsic.runtime.RuntimeContext;
import sbahjsic.runtime.type.SInt;

final class PushInt extends Instruction {
	
	private final int value;
	
	PushInt(int value) {
		this.value = value;
	}
	
	@Override
	public void execute(RuntimeContext runtime) {
		runtime.push(new SInt(value));
	}

	@Override
	public String toString() {
		return "pshint " + value;
	}

	@Override
	public InstructionType type() {
		return InstructionType.PUSH_INT;
	}
}