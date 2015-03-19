package sbahjsic.parser.lexer;

import java.util.ArrayList;
import java.util.List;

public final class Lexer {
	
	private final static TokenMatcher[] MATCHERS = {
		TokenMatcher.BRACKET_OPEN_MATCHER,
		TokenMatcher.BRACKET_CLOSE_MATCHER,
		TokenMatcher.COMMA_MATCHER,
		TokenMatcher.DOLLAR_MATCHER,
		TokenMatcher.OPERATOR_MATCHER,
		TokenMatcher.INT_LITERAL_MATCHER,
		TokenMatcher.STRING_LITERAL_MATCHER,
		TokenMatcher.IDENTIFIER_MATCHER
	};
	
	private String line;
	
	private Lexer(String line) {
		this.line = line.trim();
	}
	
	private Token nextToken() {
		if(line.isEmpty()) { return null; }
		
		for(TokenMatcher matcher : MATCHERS) {
			Token token = matcher.nextToken(line);
			
			if(token != null) {
				line = line.substring(token.string().length()).trim();
				return token;
			}
		}
		
		throw new UnrecognizedTokenException(line);
	}
	
	public static List<Token> toTokens(String line) {
		List<Token> tokens = new ArrayList<>();
		Lexer lexer = new Lexer(line);
		
		for(Token token = lexer.nextToken(); token != null; token = lexer.nextToken()) {
			tokens.add(token);
		}
		
		return tokens;
	}
}