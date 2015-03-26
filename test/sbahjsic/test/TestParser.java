package sbahjsic.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import sbahjsic.parser.lexer.Lexer;
import sbahjsic.parser.syntaxtree.SyntaxTree;

public class TestParser {
	
	String parse(String line) {
		return new SyntaxTree(Lexer.toTokens(line)).toString();
	}
	
	@Test
	public void testParsingIntLiterals() {
		assertEquals(parse("467"), "467");
	}
	
	@Test
	public void testParsingIdentifiers() {
		assertEquals(parse("x"), "x");
	}
	
	@Test
	public void testParsingStringLiterals() {
		assertEquals(parse("\"foo\""), "\"foo\"");
	}
	
	@Test
	public void testParsingUnaryOperators() {
		assertEquals(parse("-5"), "-{5}");
	}
	
	@Test
	public void testParsingNestedUnaryOperators() {
		assertEquals(parse("+ *** -9"), "+{***{-{9}}}");
	}
	
	@Test
	public void testParsingEndif() {
		assertEquals(parse("endif"), "ENDIF");
	}
	
	@Test
	public void testParsingIf() {
		assertEquals(parse("if 6"), "IF{6}");
	}
	
	@Test
	public void testImplictMultiplication() {
		assertEquals(parse("5 x"), "*{5, x}");
	}
	
	@Test
	public void testParsingBinaryOperators() {
		assertEquals(parse("6+4"), "+{6, 4}");
	}
	
	@Test
	public void testParsingNestedBinaryOperators() {
		assertEquals(parse("1+x-3"), "+{1, -{x, 3}}");
	}
	
	@Test
	public void testRemovingExtraBrackets() {
		assertEquals(parse("((61))"), "61");
	}
	
	@Test
	public void testNotRemovingValidConfusingBrackets() {
		assertEquals(parse("(5) + (2)"), "+{5, 2}");
	}
	
	@Test
	public void testRemovingInnerExtraBrackets() {
		assertEquals(parse("+((5))"), "+{5}");
	}
	
	@Test
	public void testParsingEmptyFunctionCalls() {
		assertEquals(parse("f()"), "f{}");
	}
	
	@Test
	public void testParsingFunctionCallsWithSingleArgument() {
		assertEquals(parse("g(5)"), "g{5}");
	}
	
	@Test
	public void testParsingFunctionCallsWithManyArguments() {
		assertEquals(parse("f(1, 2, 3)"), "f{1, 2, 3}");
	}
	
	@Test
	public void testParsingFunctionCallsWithMultipleTokenArguments() {
		assertEquals(parse("f(3 + 6)"), "f{+{3, 6}}");
	}
	
	@Test
	public void testParsingInnerFunctionCalls() {
		assertEquals(parse("f(g(x), h(y))"), "f{g{x}, h{y}}");
	}
	
	@Test
	public void testAdjacentFunctionCalls() {
		assertEquals(parse("f(x) + g(y, z)"), "+{f{x}, g{y, z}}");
	}
}