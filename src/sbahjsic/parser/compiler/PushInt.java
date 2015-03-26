package sbahjsic.parser.compiler;

final class PushInt extends Instruction {
	
	private final int value;
	
	PushInt(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "pshint " + value;
	}
	
}