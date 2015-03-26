package sbahjsic.parser.syntaxtree;

import sbahjsic.parser.compiler.Instruction;
import sbahjsic.parser.lexer.Token;
import sbahjsic.parser.lexer.TokenType;

/** Represents a binary operator.*/
public final class BinaryOperatorNode extends ValueNode {
	
	private final String operator;
	private final Node firstSubnode, secondSubnode;
	
	public BinaryOperatorNode(Token op, ValueNode first, ValueNode second) {
		if(op.type() != TokenType.OPERATOR) {
			throw new IllegalArgumentException();
		}
		
		this.operator = op.string();
		this.firstSubnode = first;
		this.secondSubnode = second;
	}

	@Override
	public NodeType type() { return NodeType.BINARY_OPERATOR; }
	
	@Override
	public Instruction[] toInstructions() {
		Instruction[] firstArgInstructions = firstSubnode.toInstructions();
		Instruction[] secondArgInstructions = secondSubnode.toInstructions();
		Instruction[] instructions = new Instruction[firstArgInstructions.length + secondArgInstructions.length + 1];
		
		System.arraycopy(secondArgInstructions, 0, instructions, 0, secondArgInstructions.length);
		
		int firstIndex = secondArgInstructions.length;
		System.arraycopy(firstArgInstructions, 0, instructions, firstIndex, firstArgInstructions.length);
		
		instructions[instructions.length-1] = Instruction.binaryOperator(operator);
		
		return instructions;
	}

	@Override
	public String toString() {
		return operator + "{" + firstSubnode + ", " + secondSubnode + "}";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((firstSubnode == null) ? 0 : firstSubnode.hashCode());
		result = prime * result
				+ ((operator == null) ? 0 : operator.hashCode());
		result = prime * result
				+ ((secondSubnode == null) ? 0 : secondSubnode.hashCode());
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
		if (!(obj instanceof BinaryOperatorNode)) {
			return false;
		}
		BinaryOperatorNode other = (BinaryOperatorNode) obj;
		if (firstSubnode == null) {
			if (other.firstSubnode != null) {
				return false;
			}
		} else if (!firstSubnode.equals(other.firstSubnode)) {
			return false;
		}
		if (operator == null) {
			if (other.operator != null) {
				return false;
			}
		} else if (!operator.equals(other.operator)) {
			return false;
		}
		if (secondSubnode == null) {
			if (other.secondSubnode != null) {
				return false;
			}
		} else if (!secondSubnode.equals(other.secondSubnode)) {
			return false;
		}
		return true;
	}
	
}