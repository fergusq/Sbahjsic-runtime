package sbahjsic.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import sbahjsic.io.MockSource;
import sbahjsic.runtime.ExecutionPlan;
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
}