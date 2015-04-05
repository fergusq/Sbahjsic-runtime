package sbahjsic.test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import sbahjsic.parser.lexer.Lexer;
import sbahjsic.parser.lexer.Token;
import sbahjsic.parser.lexer.TokenType;
import sbahjsic.parser.lexer.UnrecognizedTokenException;

public class TestLexer {
	
	@Test
	public void testEmptyLineLexingIntoNothing() {
		List<Token> tokens = Lexer.toTokens("");
		assertEquals(tokens.size(), 0);
	}
	
	@Test
	public void testIgnoringExtraWhitespace() {
		List<Token> tokens = Lexer.toTokens("   (  ");
		assertEquals(tokens, Arrays.asList(Token.BRACKET_OPEN));
	}
	
	@Test
	public void testLexingStringLiteral() {
		List<Token> tokens = Lexer.toTokens("\"Hi!\" \"Hey.\"");
		
		assertEquals(tokens, Arrays.asList(
				Token.stringLiteral("\"Hi!\""),
				Token.stringLiteral("\"Hey.\"")));
	}
	
	@Test
	public void testEscapingQuotesInStringLiterals() {
		List<Token> tokens = Lexer.toTokens("\"There is a \\\" single extra quote.\"");
		assertEquals(tokens, Arrays.asList(
				Token.stringLiteral("\"There is a \\\" single extra quote.\"")));
	}
	
	@Test(expected=UnrecognizedTokenException.class)
	public void testFailureOnUnclosedQuote() {
		Lexer.toTokens("\"This string literal never ends.");
	}
	
	@Test
	public void testEmptyStringLiteral() {
		List<Token> tokens = Lexer.toTokens("\"\""); // ""
		assertEquals(tokens, Arrays.asList(Token.stringLiteral("\"\"")));
	}
	
	@Test
	public void testStringLiteralWithSingleEscapedQuote() {
		List<Token> tokens = Lexer.toTokens("\"\\\"\""); // "\""
		assertEquals(tokens, Arrays.asList(Token.stringLiteral("\"\\\"\"")));
	}
	
	@Test
	public void testLexingOperators() {
		List<Token> tokens = Lexer.toTokens("+ -- >>* !!");
		assertEquals(tokens, Arrays.asList(
				Token.operator("+"),
				Token.operator("--"),
				Token.operator(">>*"),
				Token.operator("!!")));
	}
	
	@Test
	public void testAllOperatorCharsAllowed() {
		List<Token> tokens = Lexer.toTokens("+ - * / & | > < = ! ?");
		for(Token token : tokens)
			assertEquals(token.type(), TokenType.OPERATOR);
	}
	
	@Test
	public void testLexingBrackets() {
		List<Token> tokens = Lexer.toTokens("(()(");
		assertEquals(tokens, Arrays.asList(
				Token.BRACKET_OPEN,
				Token.BRACKET_OPEN,
				Token.BRACKET_CLOSE,
				Token.BRACKET_OPEN));
	}
	
	@Test
	public void testLexingComma() {
		List<Token> tokens = Lexer.toTokens(", ,,");
		assertEquals(tokens, Arrays.asList(
				Token.COMMA, Token.COMMA, Token.COMMA));
	}
	
	@Test
	public void testParsingIntLiterals() {
		List<Token> tokens = Lexer.toTokens("0 5677 2 87367");
		assertEquals(tokens, Arrays.asList(
				Token.intLiteral("0"),
				Token.intLiteral("5677"),
				Token.intLiteral("2"),
				Token.intLiteral("87367")));
	}
	
	@Test(expected=UnrecognizedTokenException.class)
	public void testBackslashNotAllowedInIdentifiers() {
		Lexer.toTokens("\\");
	}
	
	@Test
	public void testLexingDollars() {
		List<Token> tokens = Lexer.toTokens("$x$ $");
		assertEquals(tokens, Arrays.asList(
				Token.DOLLAR,
				Token.identifier("x"),
				Token.DOLLAR,
				Token.DOLLAR));
	}
	
	@Test
	public void testIdentifiersSeparatedByWhitespaceBeingOwnTokens() {
		List<Token> tokens = Lexer.toTokens("x 6 t");
		assertEquals(tokens, Arrays.asList(
				Token.identifier("x"),
				Token.intLiteral("6"),
				Token.identifier("t")));
	}
	
	@Test
	public void testFloatLiterals() {
		assertEquals(Lexer.toTokens("5.24"), Arrays.asList(Token.floatLiteral("5.24")));
	}
	
	@Test
	public void testProblematicFloatLiterals() {
		assertEquals(Lexer.toTokens("4.6)"), Arrays.asList(Token.floatLiteral("4.6"), Token.BRACKET_CLOSE));
	}
	
	@Test(expected=UnrecognizedTokenException.class)
	public void testCorrectFailureOnMultiplePointsInFloats() {
		Lexer.toTokens("4..");
	}
}