package sbahjsic.parser.syntaxtree;

import sbahjsic.parser.compiler.Instruction;

/** An if node.*/
public final class IfNode extends Node {
	
	private final ValueNode value;
	
	public IfNode(ValueNode value) {
		this.value = value;
	}
	
	@Override
	public NodeType type() { return NodeType.IF_NODE; }
	
	@Override
	public Instruction[] toInstructions() {
		Instruction[] argInstructions = value.toInstructions();
		
		Instruction[] instructions = new Instruction[argInstructions.length + 1];
		System.arraycopy(argInstructions, 0, instructions, 0, argInstructions.length);
		
		instructions[instructions.length - 1] = Instruction.ifStart();
		
		return instructions;
	}
	
	@Override
	public String toString() {
		return "IF{" + value + "}";
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
		if (!(obj instanceof IfNode)) {
			return false;
		}
		IfNode other = (IfNode) obj;
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