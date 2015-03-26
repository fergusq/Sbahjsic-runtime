package sbahjsic.parser.compiler;

final class UnaryOperator extends Instruction {
	
	private final String op;
	
	UnaryOperator(String op) {
		this.op = op;
	}

	@Override
	public String toString() {
		return "uop " + op;
	}
}