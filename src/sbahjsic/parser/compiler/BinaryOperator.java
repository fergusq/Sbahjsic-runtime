package sbahjsic.parser.compiler;

import sbahjsic.runtime.RuntimeContext;
import sbahjsic.runtime.SValue;

final class BinaryOperator extends Instruction {
	
	private final String op;
	
	BinaryOperator(String op) {
		this.op = op;
	}
	
	@Override
	public void execute(RuntimeContext runtime) {
		SValue called = runtime.pop();
		SValue arg = runtime.pop();
		
		runtime.push(called.callOperator(runtime, op, arg));
	}

	@Override
	public String toString() {
		return "biop " + op;
	}
}