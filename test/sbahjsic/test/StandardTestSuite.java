package sbahjsic.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	TestLexer.class,
	TestToken.class,
	TestParser.class,
	TestCompiler.class,
	TestExecutor.class
})
public class StandardTestSuite {}