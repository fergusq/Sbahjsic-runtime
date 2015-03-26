package sbahjsic.parser.syntaxtree;

import sbahjsic.parser.compiler.Instruction;

/** An endif node.*/
public final class EndIfNode extends Node {

	@Override
	public NodeType type() { return NodeType.ENDIF_NODE; }
	
	@Override
	public Instruction[] toInstructions() {
		return new Instruction[] { Instruction.endIf() };
	}

	@Override
	public String toString() { return "ENDIF"; }

	@Override
	public boolean equals(Object o) {
		return o instanceof EndIfNode;
	}
	
	@Override
	public int hashCode() {
		return 346566;
	}
	
}