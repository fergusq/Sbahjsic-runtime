package sbahjsic.parser.syntaxtree;

import sbahjsic.parser.lexer.Token;

/** Represents an identifier node.*/
public class IdentifierNode extends ValueNode {
	
	private final String identifier;
	
	public IdentifierNode(Token identifier) {
		this.identifier = identifier.string();
	}

	@Override
	public NodeType type() { return NodeType.IDENTIFIER; }

	@Override
	public String toString() {
		return identifier;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((identifier == null) ? 0 : identifier.hashCode());
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
		if (!(obj instanceof IdentifierNode)) {
			return false;
		}
		IdentifierNode other = (IdentifierNode) obj;
		if (identifier == null) {
			if (other.identifier != null) {
				return false;
			}
		} else if (!identifier.equals(other.identifier)) {
			return false;
		}
		return true;
	}

	
}