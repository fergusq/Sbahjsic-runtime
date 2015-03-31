package sbahjsic.parser.compiler;

import sbahjsic.runtime.RuntimeContext;

final class UnaryOperator extends Instruction {
	
	private final String op;
	
	UnaryOperator(String op) {
		this.op = op;
	}
	
	@Override
	public void execute(RuntimeContext runtime) {
		runtime.push(runtime.pop().callOperator(runtime, op));
	}

	@Override
	public String toString() {
		return "uop " + op;
	}

	@Override
	public InstructionType type() {
		return InstructionType.UNARY_OPERATOR;
	}
}