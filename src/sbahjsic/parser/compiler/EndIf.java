package sbahjsic.parser.compiler;

final class EndIf extends Instruction {
	
	final static EndIf INSTANCE = new EndIf();

	@Override
	public String toString() {
		return "endif";
	}
	
}