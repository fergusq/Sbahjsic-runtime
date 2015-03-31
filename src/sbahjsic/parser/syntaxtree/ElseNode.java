package sbahjsic.parser.syntaxtree;

import sbahjsic.parser.compiler.Instruction;

public final class ElseNode extends Node {
	
	public final static ElseNode INSTANCE = new ElseNode();
	
	private ElseNode() {}

	@Override
	public NodeType type() {
		return NodeType.ELSE_NODE;
	}

	@Override
	public Instruction[] toInstructions() {
		return new Instruction[] { Instruction.ifElse() };
	}

	@Override
	public String toString() {
		return "ELSE";
	}

	@Override
	public boolean equals(Object o) {
		return o == this;
	}

	@Override
	public int hashCode() {
		return 65647;
	}
	
}