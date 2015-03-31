package sbahjsic.parser.compiler;

import sbahjsic.runtime.RuntimeContext;
import sbahjsic.runtime.Scope;
import sbahjsic.runtime.Scope.ScopeBuilder;

final class If extends Instruction {
	
	final static If INSTANCE = new If();
	
	@Override
	public void execute(RuntimeContext runtime) {
		Scope top = runtime.scopeStack().top();
		
		if(!top.isExecuted()) {
			runtime.scopeStack().push(new ScopeBuilder("if-indefinite")
					.setIsExecuted(false)
					.setPermittedInstructions(CONTROL_FLOW)
					.build());
			return;
		}
		
		boolean ifValue = runtime.pop().asBool();
		
		runtime.scopeStack().push(new ScopeBuilder("if-" + ifValue)
				.setIsExecuted(ifValue)
				.setPermittedInstructions(CONTROL_FLOW)
				.build());
	}

	@Override
	public String toString() {
		return "if";
	}
	
	@Override
	public InstructionType type() {
		return InstructionType.IF;
	}
}