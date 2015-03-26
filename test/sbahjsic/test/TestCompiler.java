package sbahjsic.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import sbahjsic.parser.compiler.Instruction;
import sbahjsic.parser.lexer.Lexer;
import sbahjsic.parser.syntaxtree.SyntaxTree;

public class TestCompiler {
	
	String compile(String line) {
		Instruction[] ins = new SyntaxTree(Lexer.toTokens(line)).mainNode().toInstructions();
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < ins.length; i++) {
			Instruction instruction = ins[i];
			
			sb.append(instruction);
			
			if(i == ins.length -1) { return sb.toString(); }
			
			sb.append('\n');
		}
		
		throw new AssertionError();
	}
	
	@Test
	public void testCompilingSingleInteger() {
		assertEquals(compile("6"), "pshint 6");
	}
	
	@Test
	public void testCompilingSingleIdentifier() {
		assertEquals(compile("x"), "pshvar x");
	}
	
	@Test
	public void testCompilingSingleString() {
		assertEquals(compile("\"foo\""), "pshstr foo");
	}
	
	@Test
	public void testCompilingUnaryOperator() {
		assertEquals(compile("+1"), "pshint 1"
				+ "\nuop +");
	}
	
	@Test
	public void testCompilingMultipleUnaryOperators() {
		assertEquals(compile("+ - * 2"), "pshint 2"
				+ "\nuop *"
				+ "\nuop -"
				+ "\nuop +");
	}
	
	@Test
	public void testCompilingBinaryOperators() {
		assertEquals(compile("1 + 2"), "pshint 2"
				+ "\npshint 1"
				+ "\nbiop +");
	}
	
	@Test
	public void testCompilingFunctionCalls() {
		assertEquals(compile("f(x, y)"), "pshvar y"
				+ "\npshvar x"
				+ "\npshvar f"
				+ "\nfcall 2");
	}
	
	@Test
	public void testCompilingIfStatement() {
		assertEquals(compile("if 2"), "pshint 2"
				+ "\nif");
	}
	
	@Test
	public void testCompilingEndif() {
		assertEquals(compile("endif"), "endif");
	}
}