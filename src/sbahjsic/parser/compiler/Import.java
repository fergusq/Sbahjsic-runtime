package sbahjsic.parser.compiler;

import sbahjsic.io.StandardLibrary;
import sbahjsic.runtime.ExecutionEnvironment;
import sbahjsic.runtime.RuntimeContext;

public final class Import extends Instruction {
	
	private final String lib;
	
	Import(String lib) {
		this.lib = lib;
	}

	@Override
	public void execute(RuntimeContext runtime) {
		StandardLibrary.load(lib, new ExecutionEnvironment(runtime));
	}

	@Override
	public String toString() {
		return "import '" + lib + "'";
	}

	@Override
	public InstructionType type() {
		return InstructionType.IMPORT;
	}
	
}