package sbahjsic.parser.compiler;

import sbahjsic.runtime.RuntimeContext;
import sbahjsic.runtime.type.SLong;

final class PushLong extends Instruction {
	
	private final long value;
	
	PushLong(long value) {
		this.value = value;
	}

	@Override
	public void execute(RuntimeContext runtime) {
		runtime.push(new SLong(value));
	}

	@Override
	public String toString() {
		return "pshlng " + value;
	}

	@Override
	public InstructionType type() {
		return InstructionType.PUSH_LONG;
	}
	
}