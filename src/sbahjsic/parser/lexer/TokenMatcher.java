package sbahjsic.parser.lexer;

@FunctionalInterface
interface TokenMatcher {
	
	Token nextToken(String line);
	
	final static TokenMatcher BRACKET_OPEN_MATCHER = LexerUtils.targetStringMatcher("(", Token.BRACKET_OPEN);
	final static TokenMatcher BRACKET_CLOSE_MATCHER = LexerUtils.targetStringMatcher(")", Token.BRACKET_CLOSE);
	final static TokenMatcher COMMA_MATCHER = LexerUtils.targetStringMatcher(",", Token.COMMA);
	final static TokenMatcher DOLLAR_MATCHER = LexerUtils.targetStringMatcher("$", Token.DOLLAR);
	
	final static TokenMatcher STRING_LITERAL_MATCHER = string -> {
		if(string.length() < 2) { return null; }
		if(!string.startsWith("\"")) { return null; }
		int endIndex = -1;
		
		for(int i = 1; i < string.length(); i++) {
			char character = string.charAt(i);
			
			if(string.charAt(i-1) != '\\' && character == '"') {
				endIndex = i;
				break;
			}
		}
		
		if(endIndex > 0) {
			return Token.stringLiteral(string.substring(0, endIndex+1));
		}
		
		return null;
	};
	
	final static TokenMatcher FLOAT_LITERAL_MATCHER = string -> {
		if(string.length() == 0) { return null; }
		if(!LexerUtils.isNumeric(string.charAt(0))) { return null; }
		boolean hasPoint = false;
		int endIndex = string.length()-1;
		
		for(int i = 1; i < string.length(); i++) {
			char character = string.charAt(i);
			if(LexerUtils.isNumeric(character)) { continue; }
			if(character != '.') { endIndex = i-1; break; }
			if(hasPoint == true) { endIndex = i-1; break; }
			hasPoint = true;
		}
		
		if(!hasPoint) { return null; }
		
		return Token.floatLiteral(string.substring(0, endIndex+1));
	};
	
	final static TokenMatcher OPERATOR_MATCHER = 
			LexerUtils.matchUntilIllegalFound(LexerUtils::isOperatorChar, Token::operator);
	
	final static TokenMatcher INT_LITERAL_MATCHER = 
			LexerUtils.matchUntilIllegalFound(LexerUtils::isNumeric, Token::intLiteral);
	
	final static TokenMatcher IDENTIFIER_MATCHER = 
			LexerUtils.matchUntilIllegalFound(LexerUtils::isValidIdentifierChar, Token::identifier);
}