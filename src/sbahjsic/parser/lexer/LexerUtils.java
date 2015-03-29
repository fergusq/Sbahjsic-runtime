package sbahjsic.parser.lexer;

import java.util.function.Function;
import java.util.function.IntPredicate;

final class LexerUtils {

	final static char[] OP_CHARS = { '+', '-', '*', '/', '&', '|', '>', '<', '=', '!', '?', '~' };
	
	final static char[] RESERVED_CHARS = { '(', ')', ',', '"', '\\', '$', ' ', '\t' };
	
	// These functions use int for use with IntPredicates
	
	static boolean isOperatorChar(int chr) {
		for(char opChar : OP_CHARS) {
			if(chr == opChar) { return true; }
		}
		return false;
	}
	
	static boolean isReservedChar(int chr) {
		for(char reservedChar : RESERVED_CHARS) {
			if(chr == reservedChar) { return true; }
		}
		return false;
	}
	
	static boolean isNumeric(int chr) {
		return chr >= '0' && chr <= '9';
	}
	
	static boolean isValidIdentifierChar(int chr) {
		return !isOperatorChar(chr) && !isReservedChar(chr);
	}

	static TokenMatcher targetStringMatcher(String target, Token token) {
		return string -> {
			if(string.startsWith(target)) {
				return token;
			}
			return null;
		};
	}
	
	static TokenMatcher matchUntilIllegalFound(IntPredicate validityPredicate,
			Function<String, Token> tokenCreator) {
		return string -> {
			int firstIllegal = LexerUtils.firstIndexMatching(string, validityPredicate.negate());
			
			if(firstIllegal == -1) { return tokenCreator.apply(string); }
			
			if(firstIllegal == 0) { return null; }
			
			return tokenCreator.apply(string.substring(0, firstIllegal));
		};
	}
	
	static int firstIndexMatching(String string, IntPredicate predicate) {
		for(int i = 0; i < string.length(); i++) {
			if(predicate.test(string.charAt(i))) {
				return i;
			}
		}
		
		return -1;
	}
}