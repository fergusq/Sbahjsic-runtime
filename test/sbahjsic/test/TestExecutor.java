package sbahjsic.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import sbahjsic.io.MockSource;
import sbahjsic.runtime.ExecutionPlan;
import sbahjsic.runtime.OperatorCallException;
import sbahjsic.runtime.SValue;
import sbahjsic.runtime.type.SInt;
import sbahjsic.runtime.type.SString;

public class TestExecutor {
	
	private SValue lastValue = null;
	
	private SValue lastValue(String... lines) {
		new ExecutionPlan()
				.setRunCode(true)
				.forLineValues(s -> { lastValue = s; })
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
	public void testStrings() {
		assertEquals(new SString("cat"), lastValue("\"cat\""));
	}
	
	@Test
	public void testAssigningVariables() {
		assertEquals(4, lastValue("x = 4", "x").asInt());
	}
	
	@Test(expected=OperatorCallException.class)
	public void testCallWithWrongNumberOfArguments() {
		lastValue("+5");
	}
	
	@Test
	public void testCallingOperatorsOnReferences() {
		assertEquals(6, lastValue("x = 4", "y = 2", "x + y").asInt());
	}
	
	@Test
	public void testBracketsControllingOrderOfOperations() {
		assertEquals(20, lastValue("(2*6)+8").asInt());
	}
}