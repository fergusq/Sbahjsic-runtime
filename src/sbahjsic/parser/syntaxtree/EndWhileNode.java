package sbahjsic.parser.syntaxtree;

import sbahjsic.parser.compiler.Instruction;

public final class EndWhileNode extends Node {
	
	public final static EndWhileNode INSTANCE = new EndWhileNode();
	
	private EndWhileNode() {}

	@Override
	public NodeType type() {
		return NodeType.END_WHILE_NODE;
	}

	@Override
	public Instruction[] toInstructions() {
		return new Instruction[] { Instruction.endWhileStatement() };
	}

	@Override
	public String toString() {
		return "ENDWHILE";
	}

	@Override
	public boolean equals(Object o) {
		return o == INSTANCE;
	}

	@Override
	public int hashCode() {
		return -7878;
	}
	
}