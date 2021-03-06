package sbahjsic.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import sbahjsic.io.MockSource;
import sbahjsic.runtime.ExecutionEnvironment;
import sbahjsic.runtime.SValue;
import sbahjsic.runtime.UnknownNativeException;
import sbahjsic.runtime.type.SFloat;
import sbahjsic.runtime.type.SInt;
import sbahjsic.runtime.type.SString;

public class TestExecutor {
	
	private SValue lastValue = null;
	
	@SuppressWarnings("resource")
	private SValue lastValue(String... lines) {
		new ExecutionEnvironment()
				.setSaveLineNumbers(false)
				.setRunCode(true)
				.forLastValue(s -> { lastValue = s; })
				.execute(new MockSource(lines));
		
		return lastValue;
	}
	
	@Test
	public void testBinaryOperator() {
		assertEquals(new SInt(5), lastValue("3 + 2"));
	}
	
	@Test
	public void testInts() {
		assertEquals(new SInt(2), lastValue("2"));
	}
	
	@Test
	public void testLongs() {
		assertEquals(Long.MAX_VALUE, lastValue(""+Long.MAX_VALUE).asLong());
	}
	
	@Test
	public void testStrings() {
		assertEquals(new SString("cat"), lastValue("\"cat\""));
	}
	
	@Test
	public void testEscapingNewline() {
		assertEquals("\n", lastValue("\"\\n\"").asString());
	}
	
	@Test
	public void testFloats() {
		assertEquals(new SFloat(4.23f), lastValue("4.23"));
	}
	
	@Test
	public void testAssigningVariables() {
		assertEquals(4, lastValue("x = 4", "x").asInt());
	}
	
	@Test
	public void testCallingOperatorsOnReferences() {
		assertEquals(6, lastValue("x = 4", "y = 2", "x + y").asInt());
	}
	
	@Test
	public void testBracketsControllingOrderOfOperations() {
		assertEquals(20, lastValue("(2*6)+8").asInt());
	}
	
	@Test
	public void testFunctionCalls() {
		assertEquals("int", lastValue("typeof(5)").asString());
	}
	
	@Test
	public void testNestedFunctionCalls() {
		assertEquals("bool", lastValue("typeof(typeof(4) == \"int\")").asString());
	}
	
	@Test
	public void testTrueIfStatements() {
		assertEquals(5, lastValue("if true",
				"x = 5",
				"endif",
				"x").asInt());
	}
	
	@Test
	public void testFalseIfStatements() {
		assertEquals(6, lastValue("g = 6",
				"if false",
				"g = 35",
				"endif",
				"g").asInt());
	}
	
	@Test
	public void testNestedTrueIfStatements() {
		assertEquals(2, lastValue("if true",
				"if true",
				"u = 2",
				"endif",
				"endif",
				"u").asInt());
	}
	
	@Test
	public void testNestedFalseIfStatements() {
		assertEquals(4, lastValue("z = 4",
				"if false",
				"if true",
				"z = 6",
				"endif",
				"endif",
				"z").asInt());
	}
	
	@Test
	public void testElse() {
		assertEquals(3, lastValue("x = 4",
				"if false",
				"x = 7",
				"else",
				"x = 3",
				"endif",
				"x").asInt());
	}
	
	@Test
	public void testMultipleElse() {
		assertEquals(4, lastValue("x = 7",
				"if true",
				"else",
				"else",
				"x = 4",
				"endif",
				"x").asInt());
	}
	
	@Test
	public void testInternalElse() {
		assertEquals(2, lastValue("x = 2",
				"if false",
				"if false",
				"else",
				"x = 3",
				"endif",
				"endif",
				"x").asInt());
	}
	
	@Test(expected=UnknownNativeException.class)
	public void testLoadingUnknownNative() {
		lastValue("native doesnotexist");
	}
	
	@Test
	public void testLoadingRealNativeDeclarations() {
		assertTrue(lastValue("native ms", "ms").typeName().equals("func"));
	}
	
	@Test
	public void testWhile() {
		assertEquals(10, lastValue("x = 0",
				"while x != 10",
				"x = x + 1",
				"endwhile",
				"x").asInt());
	}
	
	@Test
	public void testInternalWhile() {
		assertEquals(2, lastValue("a=0",
				"while a != 2",
				"a = a + 1",
				"x = 0",
				"while x != 3",
				"x = x + 1",
				"endwhile",
				"endwhile",
				"a").asInt());
	}
	
	@Test
	public void testFalseWhile() {
		assertEquals(3, lastValue("t = 3",
				"while false",
				"do some bullshit",
				"endwhile",
				"t").asInt());
	}
	
	@Test
	public void testDefiningFunctions() {
		assertEquals("func", lastValue("function f()",
				"endfunction",
				"f").typeName());
	}
	
	@Test
	public void testCallingFunctions() {
		assertEquals(4, lastValue("function set(x)",
				"value = x",
				"endfunction",
				"set(4)",
				"value").asInt());
	}
	
	@Test
	public void testReturningValueFromFunction() {
		assertEquals(3, lastValue("function f()",
				"return 3",
				"endfunction",
				"f()").asInt());
	}
	
	@Test
	public void testDefaultReturnValueIsVoid() {
		assertEquals("void", lastValue("function f()",
				"endfunction",
				"x = f()",
				"x").typeName());
	}
	
	@Test
	public void testReturnExitsFunction() {
		assertEquals(2, lastValue("x = 2",
				"function g()",
				"return 3",
				"x = 4",
				"endfunction",
				"g()",
				"x").asInt());
	}
}