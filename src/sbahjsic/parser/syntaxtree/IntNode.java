package sbahjsic.parser.syntaxtree;

import sbahjsic.parser.lexer.Token;
import sbahjsic.parser.lexer.TokenType;

/** A node that contains an integer.*/
public final class IntNode extends ValueNode {
	
	private final int integer;
	
	public IntNode(Token token) {
		if(token.type() != TokenType.INT_LITERAL) {
			throw new IllegalArgumentException();
		}
		
		integer = Integer.parseInt(token.string());
	}

	@Override
	public NodeType type() { return NodeType.INT_LITERAL; }
	
	@Override
	public String toString() {
		return "" + integer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + integer;
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
		if (!(obj instanceof IntNode)) {
			return false;
		}
		IntNode other = (IntNode) obj;
		if (integer != other.integer) {
			return false;
		}
		return true;
	}
	
}