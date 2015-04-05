package sbahjsic.parser.compiler;

import sbahjsic.runtime.RuntimeContext;
import sbahjsic.runtime.type.SFloat;

final class PushFloat extends Instruction {
	
	private final float value;
	
	PushFloat(float value) {
		this.value = value;
	}

	@Override
	public void execute(RuntimeContext runtime) {
		runtime.push(new SFloat(value));
	}

	@Override
	public String toString() {
		return "pshfl " + value;
	}

	@Override
	public InstructionType type() {
		return InstructionType.PUSH_FLOAT;
	}
	
}