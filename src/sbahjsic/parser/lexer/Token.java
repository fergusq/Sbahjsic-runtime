package sbahjsic.parser.lexer;

/** Represents a syntactical token.*/
public final class Token {
	
	/** The token "(".*/
	public final static Token BRACKET_OPEN = new Token(TokenType.BRACKET_OPEN, "(");
	
	/** The token ")".*/
	public final static Token BRACKET_CLOSE = new Token(TokenType.BRACKET_CLOSE, ")");
	
	/** The token ",".*/
	public final static Token COMMA = new Token(TokenType.COMMA, ",");
	
	/** The token "$".*/
	public final static Token DOLLAR = new Token(TokenType.DOLLAR, "$");
	
	private final TokenType type;
	private final String string;
	
	private Token(TokenType type, String string) {
		this.type = type;
		this.string = string;
		
		if(this.string.isEmpty()) {
			throw new IllegalArgumentException("Token cannot be empty");
		}
	}
	
	/** Returns the type of this token as defined in {@code TokenType}.
	 * @return the type of this token*/
	public TokenType type() { return type; }
	
	/** Returns the string representation of this token.
	 * @return the string form of this token*/
	public String string() { return string; }
	
	/** Creates an identifier token.
	 * @param id the identifier contained by the token
	 * @return an identifier token*/
	public static Token identifier(String id) {
		return new Token(TokenType.IDENTIFIER, id);
	}
	
	/** Creates an operator token.
	 * @param op the operator contained by the token
	 * @return an operator token*/
	public static Token operator(String op) {
		return new Token(TokenType.OPERATOR, op);
	}
	
	/** Creates an integer literal token.
	 * @param literal the integer literal contained by the token
	 * @return an integer literal token*/
	public static Token intLiteral(String literal) {
		return new Token(TokenType.INT_LITERAL, literal);
	}
	
	/** Creates a string literal token.
	 * @param literal the string literal contained by the token
	 * @return a string literal token*/
	public static Token stringLiteral(String literal) {
		return new Token(TokenType.STRING_LITERAL, literal);
	}
	
	/** Creates a float literal token.
	 * @param literal the float literal contained by the token
	 * @return a float literal token*/
	public static Token floatLiteral(String literal) {
		return new Token(TokenType.FLOAT_LITERAL, literal);
	}
	
	@Override
	public String toString() {
		return "[" + type + ": " + string + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((string == null) ? 0 : string.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		if (!(obj instanceof Token)) {
			return false;
		}
		Token other = (Token) obj;
		if (string == null) {
			if (other.string != null) {
				return false;
			}
		} else if (!string.equals(other.string)) {
			return false;
		}
		if (type != other.type) {
			return false;
		}
		return true;
	}
}