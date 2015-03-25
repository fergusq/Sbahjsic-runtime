package sbahjsic.parser.syntaxtree;

import sbahjsic.parser.lexer.Token;
import sbahjsic.parser.lexer.TokenType;

/** Represents a string node.*/
public final class StringNode extends Node {
	
	private final String string;
	
	public StringNode(Token literal) {
		if(literal.type() != TokenType.STRING_LITERAL) {
			throw new IllegalArgumentException();
		}
		
		string = literal.string();
	}

	@Override
	public NodeType type() { return NodeType.STRING_LITERAL; }

	@Override
	public String toString() {
		return string;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((string == null) ? 0 : string.hashCode());
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
		if (!(obj instanceof StringNode)) {
			return false;
		}
		StringNode other = (StringNode) obj;
		if (string == null) {
			if (other.string != null) {
				return false;
			}
		} else if (!string.equals(other.string)) {
			return false;
		}
		return true;
	}
	
}