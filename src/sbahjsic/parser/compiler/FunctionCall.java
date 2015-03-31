package sbahjsic.parser.compiler;

import sbahjsic.runtime.RuntimeContext;
import sbahjsic.runtime.SValue;

final class FunctionCall extends Instruction {
	
	private final int args;
	
	FunctionCall(int args) {
		this.args = args;
	}
	
	@Override
	public void execute(RuntimeContext runtime) {
		SValue[] arguments = new SValue[args];
		SValue called = runtime.pop();
		
		for(int i = 0; i < args; i++) {
			arguments[i] = runtime.pop();
		}
		
		runtime.push(called.call(runtime, arguments));
	}

	@Override
	public String toString() {
		return "fcall " + args;
	}

	@Override
	public InstructionType type() {
		return InstructionType.FUNCTION_CALL;
	}
}