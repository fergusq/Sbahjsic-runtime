package sbahjsic.parser.syntaxtree;

import sbahjsic.parser.compiler.Instruction;

public final class ReturnNode extends Node {
	
	private final ValueNode value;
	
	public ReturnNode(ValueNode node) {
		this.value = node;
	}

	@Override
	public NodeType type() {
		return NodeType.RETURN_NODE;
	}

	@Override
	public Instruction[] toInstructions() {
		Instruction[] valInstructions = value.toInstructions();
		
		Instruction[] instructions = new Instruction[valInstructions.length + 1];
		System.arraycopy(valInstructions, 0, instructions, 0, valInstructions.length);
		instructions[instructions.length-1] = Instruction.returnStatement();
		
		return instructions;
	}

	@Override
	public String toString() {
		return "RETURN{" + value + "}";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ReturnNode)) {
			return false;
		}
		ReturnNode other = (ReturnNode) obj;
		if (value == null) {
			if (other.value != null) {
				return false;
			}
		} else if (!value.equals(other.value)) {
			return false;
		}
		return true;
	}
}