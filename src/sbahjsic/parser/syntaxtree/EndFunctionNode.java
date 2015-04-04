package sbahjsic.parser.syntaxtree;

import sbahjsic.parser.compiler.Instruction;

public final class EndFunctionNode extends Node {
	
	public final static EndFunctionNode INSTANCE = new EndFunctionNode();
	
	private EndFunctionNode() {}

	@Override
	public NodeType type() {
		return NodeType.ENDFUNCTION_NODE;
	}

	@Override
	public Instruction[] toInstructions() {
		return new Instruction[] { Instruction.endFunction() };
	}

	@Override
	public String toString() {
		return "ENDFUNCTION";
	}
	
	@Override
	public boolean equals(Object o) {
		return o == INSTANCE;
	}
	
	@Override
	public int hashCode() {
		return -53646511;
	}
}