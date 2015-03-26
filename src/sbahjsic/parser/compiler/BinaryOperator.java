package sbahjsic.parser.compiler;

final class BinaryOperator extends Instruction {
	
	private final String op;
	
	BinaryOperator(String op) {
		this.op = op;
	}

	@Override
	public String toString() {
		return "biop " + op;
	}
	
}