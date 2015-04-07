package sbahjsic.parser.syntaxtree;

import sbahjsic.parser.compiler.Instruction;
import sbahjsic.parser.lexer.Token;
import sbahjsic.parser.lexer.TokenType;

/** A node that contains an integer.*/
public final class IntNode extends ValueNode {
	
	private final Token token;
	
	public IntNode(Token token) {
		if(token.type() != TokenType.INT_LITERAL) {
			throw new IllegalArgumentException();
		}
		
		this.token = token;
	}

	@Override
	public NodeType type() { return NodeType.INT_LITERAL; }
	
	@Override
	public Instruction[] toInstructions() {
		try {
			return new Instruction[] { Instruction.pushInt(Integer.parseInt(token.string())) };
		} catch(Exception e) {
			return new Instruction[] { Instruction.pushLong(Long.parseLong(token.string())) };
		}
	}
	
	@Override
	public String toString() {
		return "" + token.string();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((token == null) ? 0 : token.hashCode());
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
		if (token == null) {
			if (other.token != null) {
				return false;
			}
		} else if (!token.equals(other.token)) {
			return false;
		}
		return true;
	}
}