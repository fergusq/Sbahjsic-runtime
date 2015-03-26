package sbahjsic.parser.compiler;

final class FunctionCall extends Instruction {
	
	private final int args;
	
	FunctionCall(int args) {
		this.args = args;
	}

	@Override
	public String toString() {
		return "fcall " + args;
	}
}