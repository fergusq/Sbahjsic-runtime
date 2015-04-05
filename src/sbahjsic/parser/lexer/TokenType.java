package sbahjsic.parser.lexer;

/** The types of tokens.*/
public enum TokenType {
	IDENTIFIER(true),
	OPERATOR,
	INT_LITERAL(true),
	STRING_LITERAL(true),
	FLOAT_LITERAL(true),
	BRACKET_OPEN,
	BRACKET_CLOSE,
	COMMA,
	DOLLAR;
	
	private final boolean isValue;
	
	TokenType(boolean isValue) {
		this.isValue = isValue;
	}
	
	TokenType() {
		this.isValue = false;
	}
	
	/** Returns whether this token type represents a value.
	 * @return whether this token type represents a value*/
	public boolean isValue() {
		return isValue;
	}
}