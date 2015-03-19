package sbahjsic.parser.lexer;

final class LexerUtils {

	final static char[] OP_CHARS = { '+', '-', '*', '/', '&', '|', '>', '<', '=', '!', '?'};

	static boolean isOperatorChar(char c) {
		for(char opChar : OP_CHARS) {
			if(c == opChar) { return true; }
		}
		return false;
	}

	static TokenMatcher targetStringMatcher(String target, Token token) {
		return string -> {
			if(string.startsWith(target)) {
				return token;
			}
			return null;
		};
	}
	
}