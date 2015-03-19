package sbahjsic.test;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

import sbahjsic.parser.lexer.Token;

public class TestToken {
	
	@Test
	public void testTokensWithDifferentTypesNotEqual() {
		assertFalse(Token.identifier("a").equals(Token.stringLiteral("a")));
	}
	
	@Test
	public void testTokensWithDifferentStringsNotEqual() {
		assertFalse(Token.identifier("a").equals(Token.identifier("b")));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testEmptyTokensNotAllowed() {
		Token.identifier("");
	}
}