package sbahjsic.parser.compiler;

import sbahjsic.runtime.RuntimeContext;
import sbahjsic.runtime.Scope;
import sbahjsic.runtime.Scope.ScopeBuilder;
import sbahjsic.runtime.type.SUserFunc;

final class Function extends Instruction {
	
	private final int args;
	
	Function(int args) {
		this.args = args;
	}

	@Override
	public void execute(RuntimeContext runtime) {
		Scope top = runtime.scopeStack().top();
		
		if(!top.isExecuted()) {
			runtime.scopeStack().push(new ScopeBuilder("function-indefinite")
					.setPermittedInstructions(CONTROL_FLOW)
					.setIsExecuted(false)
					.build());
		} else {
			String name = runtime.pop().asAddress();
			String[] argNames = new String[args];
			
			for(int i = 0; i < args; i++) {
				argNames[i] = runtime.pop().asAddress();
			}
			
			runtime.scopeStack().push(new ScopeBuilder("function")
					.setIsExecuted(false)
					.setPermittedInstructions(InstructionType.FUNCTION, InstructionType.ENDFUNCTION)
					.setInstructionsConsumer((con, ins) -> {
						con.setMemory(name, new SUserFunc(argNames, ins, name));
					})
					.build());
		}
	}

	@Override
	public String toString() {
		return "func " + args;
	}

	@Override
	public InstructionType type() {
		return InstructionType.FUNCTION;
	}
	
}