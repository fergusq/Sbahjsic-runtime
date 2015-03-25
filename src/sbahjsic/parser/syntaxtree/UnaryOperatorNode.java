package sbahjsic.parser.syntaxtree;

import sbahjsic.parser.lexer.Token;
import sbahjsic.parser.lexer.TokenType;

/** Represents an unary operator.*/
public final class UnaryOperatorNode extends ValueNode {
	
	private final String operator;
	private final Node subnode;
	
	public UnaryOperatorNode(Token op, ValueNode subnode) {
		if(op.type() != TokenType.OPERATOR) {
			throw new IllegalArgumentException();
		}
		
		operator = op.string();
		this.subnode = subnode;
	}

	@Override
	public NodeType type() { return NodeType.UNARY_OPERATOR; }

	@Override
	public String toString() {
		return operator + "(" + subnode + ")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((operator == null) ? 0 : operator.hashCode());
		result = prime * result + ((subnode == null) ? 0 : subnode.hashCode());
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
		if (!(obj instanceof UnaryOperatorNode)) {
			return false;
		}
		UnaryOperatorNode other = (UnaryOperatorNode) obj;
		if (operator == null) {
			if (other.operator != null) {
				return false;
			}
		} else if (!operator.equals(other.operator)) {
			return false;
		}
		if (subnode == null) {
			if (other.subnode != null) {
				return false;
			}
		} else if (!subnode.equals(other.subnode)) {
			return false;
		}
		return true;
	}
	
}