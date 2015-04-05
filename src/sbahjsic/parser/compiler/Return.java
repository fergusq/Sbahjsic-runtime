package sbahjsic.parser.compiler;

import sbahjsic.runtime.RuntimeContext;
import sbahjsic.runtime.Scope.ScopeBuilder;

final class Return extends Instruction {
	
	final static Return INSTANCE = new Return();
	
	private Return() {}

	@Override
	public void execute(RuntimeContext runtime) {
		runtime.scopeStack().push(new ScopeBuilder("return")
				.setIsExecuted(false)
				.build());
	}

	@Override
	public String toString() {
		return "ret";
	}

	@Override
	public InstructionType type() {
		return InstructionType.RETURN;
	}
	
}