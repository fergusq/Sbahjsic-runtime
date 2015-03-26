package sbahjsic.parser.compiler;

final class If extends Instruction {
	
	final static If INSTANCE = new If();

	@Override
	public String toString() {
		return "if";
	}
	
}