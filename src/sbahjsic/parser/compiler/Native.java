package sbahjsic.parser.compiler;

import sbahjsic.runtime.Natives;
import sbahjsic.runtime.RuntimeContext;

final class Native extends Instruction {
	
	private final String resource;
	
	Native(String resource) {
		this.resource = resource;
	}

	@Override
	public void execute(RuntimeContext runtime) {
		runtime.setMemory(resource, Natives.load(resource));
	}

	@Override
	public String toString() {
		return "natdec '" + resource +"'";
	}

	@Override
	public InstructionType type() {
		return InstructionType.NATIVE;
	}
	
}