package sbahjsic.parser.syntaxtree;

import sbahjsic.parser.compiler.Instruction;
import sbahjsic.parser.lexer.Token;
import sbahjsic.parser.lexer.TokenType;

public final class FloatNode extends ValueNode {
	
	private final Token value;
	
	public FloatNode(Token value) {
		if(value.type() != TokenType.FLOAT_LITERAL) {
			throw new IllegalArgumentException();
		}
		this.value = value;
	}

	@Override
	public NodeType type() {
		return NodeType.FLOAT_LITERAL;
	}

	@Override
	public Instruction[] toInstructions() {
		return new Instruction[] { Instruction.pushFloat(Float.parseFloat(value.string())) };
	}

	@Override
	public String toString() {
		return "" + value.string();
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
		if (!(obj instanceof FloatNode)) {
			return false;
		}
		FloatNode other = (FloatNode) obj;
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