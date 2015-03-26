package sbahjsic.parser.compiler;

final class PushVar extends Instruction {
	
	private final String value;
	
	PushVar(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "pshvar " + value;
	}
}