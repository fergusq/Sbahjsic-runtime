package sbahjsic.parser.compiler;

import sbahjsic.runtime.RuntimeContext;
import sbahjsic.runtime.Scope;
import sbahjsic.runtime.Scope.ScopeBuilder;

final class While extends Instruction {
	
	private final int conditions;
	
	While(int conditionInstructions) {
		this.conditions = conditionInstructions;
	}

	@Override
	public void execute(RuntimeContext runtime) {
		Scope top = runtime.scopeStack().top();
		
		if(!top.isExecuted()) {
			runtime.scopeStack().push(new ScopeBuilder("while-indefinite")
					.setIsExecuted(false)
					.setLoop(false, 0)
					.setPermittedInstructions(CONTROL_FLOW)
					.build());
			return;
		}
		
		boolean whileValue = runtime.pop().asBool();
		
		runtime.scopeStack().push(new ScopeBuilder("while-" + whileValue)
				.setLoop(whileValue, conditions)
				.setIsExecuted(whileValue)
				.setPermittedInstructions(CONTROL_FLOW)
				.build());
	}

	@Override
	public String toString() {
		return "while " + conditions;
	}

	@Override
	public InstructionType type() {
		return InstructionType.WHILE;
	}
	
}