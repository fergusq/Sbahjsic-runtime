package sbahjsic.parser.syntaxtree;

import sbahjsic.parser.compiler.Instruction;

/** An empty node.*/
public final class EmptyNode extends Node {

	@Override
	public NodeType type() {
		return NodeType.EMPTY;
	}

	@Override
	public Instruction[] toInstructions() {
		return new Instruction[0];
	}

	@Override
	public String toString() {
		return "";
	}

	@Override
	public boolean equals(Object o) {
		return o instanceof EmptyNode;
	}

	@Override
	public int hashCode() {
		return 112336;
	}
	
}