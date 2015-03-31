package sbahjsic.parser.compiler;

import sbahjsic.runtime.RuntimeContext;

public final class LineNumber extends Instruction {
	
	private final int line;
	
	public LineNumber(int line) {
		this.line = line;
	}

	@Override
	public void execute(RuntimeContext runtime) {
		runtime.setLineNumber(line);
	}

	@Override
	public String toString() {
		return "line " + line;
	}

	@Override
	public InstructionType type() {
		return InstructionType.LINE_NUMBER;
	}
}