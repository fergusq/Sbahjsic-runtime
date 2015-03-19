package sbahjsic.parser.lexer;


@FunctionalInterface
interface TokenMatcher {
	
	final static TokenMatcher BRACKET_OPEN_MATCHER = LexerUtils.targetStringMatcher("(", Token.BRACKET_OPEN);
	final static TokenMatcher BRACKET_CLOSE_MATCHER = LexerUtils.targetStringMatcher(")", Token.BRACKET_CLOSE);
	final static TokenMatcher COMMA_MATCHER = LexerUtils.targetStringMatcher(",", Token.COMMA);
	
	final static TokenMatcher STRING_LITERAL_MATCHER = string -> {
		if(string.length() < 2) { return null; }
		if(!string.startsWith("\"")) { return null; }
		int index = -1;
		
		for(int i = 1; i < string.length(); i++) {
			char character = string.charAt(i);
			
			if(string.charAt(i-1) != '\\' && character == '"') {
				index = i;
				break;
			}
		}
		
		if(index > 0) {
			return Token.stringLiteral(string.substring(0, index+1));
		}
		
		return null;
	};
	
	final static TokenMatcher OPERATOR_MATCHER = string -> {
		int firstNonOpChar = string.length();
		
		for(int i = 0; i < string.length(); i++) {
			char character = string.charAt(i);
			if(!LexerUtils.isOperatorChar(character)) {
				firstNonOpChar = i;
				break;
			}
		}
		
		if(firstNonOpChar > 0) {
			return Token.operator(string.substring(0, firstNonOpChar));
		}
		
		return null;
	};
	
	Token nextToken(String line);
}